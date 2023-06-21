package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.Tile.ColourTile;
import it.polimi.ingsw.model.Tile.Tile;

import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents the implementation of the RemoteController interface. It provides the methods
 * to manage the remote operations of the game.
 */
public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController, Serializable {

    private final MasterController masterController;
    private final HashMap<Long, Socket> tempTcp;
    private final HashMap<Long, CommunicationProtocol> tempRmi;
    private final HashMap<Integer, List<Connection> > clients;
    private int currentGameID;




    /**
     * Constructor for RemoteControllerImpl class.
     * Creates a new instance of MasterController and sets the currentGameID to 0.
     */
    public RemoteControllerImpl() throws RemoteException {
        super();
        tempTcp = new HashMap<>();
        tempRmi = new HashMap<>();
        clients = new HashMap<>();
        masterController = new MasterController();
        currentGameID = -1;
    }




    @Override
    public Message onMessage(Message message) throws RemoteException {

        if(message.typeMessage().equals( "LoginMessage")){
            return registerPlayer(message);
        }
        if(message.typeMessage().equals( "SetMessage")){
            return setMaxPlayers(message);
        }
        if(message.typeMessage().equals( "InitMessage")){
            return initGame(message.getGameID());
        }
        if(message.typeMessage().equals( "RemoveMessage")){
            return remove(message.getGameID(), message.getPositions());
        }
        if(message.typeMessage().equals( "TurnMessage")){
            return turn(message.getGameID(), message.getColours(),message.getColumn(), message.getNickname());
        }

        return null;
    }



    public void addRmiCl(long UID, CommunicationProtocol protocol) throws RemoteException{
        tempRmi.put(UID, protocol);
    }

    public void addTcpCl(long UID, Socket socket) throws RemoteException{
        tempTcp.put(UID, socket);
    }


    @Override
    public int startGame() throws RemoteException {
        currentGameID++;
        return masterController.newGameController();
    }








    @Override
    public void registerPlayer(Message message) throws RemoteException {
        String nickname = message.getNickname();
        long UID = message.getUID();
        try {
            masterController.getGameController(currentGameID).login(nickname);


            if(message.getProtocol().equals("TCP")){
                clients.get(currentGameID).add(new TCPConnect(tempTcp.get(UID), nickname));
            }

            if(message.getProtocol().equals("RMI")){
                clients.get(currentGameID).add(new RMIConnect(tempRmi.get(UID), nickname));
            }

            if (masterController.getGameController(currentGameID).getMaxPlayers() == masterController.getGameController(currentGameID).getNumOfPlayers()){
                return new LoginResponse(false, currentGameID, false, true);
            }else{
                return new LoginResponse(false, currentGameID, false, false);
            }
            //return new LoginResponse("/*gli dico che gameID avrà ovvero ''currentGameID'' e che NON è il primo giocatore");
        } catch (UsernameException e) {
            return new LoginResponse(true, -1, false, false);
            //return new LoginResponse("/*gli dico l'username è già usato");
        } catch (GameAlreadyStarted | MaxPlayerException | NullPointerException e) {
            startGame();
            try {
                masterController.getGameController(currentGameID).login(nickname);
                clients.put(currentGameID, new ArrayList<>());
                if(message.getProtocol().equals("TCP")){
                    clients.get(currentGameID).add(new TCPConnect(tempTcp.get(UID), nickname));
                }
                if(message.getProtocol().equals("RMI")){
                    clients.get(currentGameID).add(new RMIConnect(tempRmi.get(UID), nickname));
                }

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return new LoginResponse(false, currentGameID, true, false);
            //return new LoginMessage("/*gli dico che gameID avrà ovvero ''currentGameID'' e che è il primo giocatore ");
        }

    }









    public Message initGame(int gameID) throws RemoteException {
        try{
            this.masterController.getGameController(gameID).initGame();
            Message initMessage = new InitMessage();
            for(int i = 0; i < clients.get(gameID).size(); i++){
                clients.get(gameID).get(i).sendMessage(initMessage);
            }
            String player = masterController.getGameController(gameID).getCurrentPlayer().getNickname();
            for(int i=0; i<clients.get(gameID).size(); i++){
                if(clients.get(gameID).get(i).getNickname().equals(player)){
                    playClient(i, gameID);
                    break;
                }
            }
            return new InitResponse();
        }
        catch (GameNotReadyException | GameAlreadyStarted e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Message remove(int gameID, Coordinates[] positions) throws RemoteException {
        try {
            masterController.getGameController(gameID).remove(positions);
            return new RemoveResponse();
        } catch (Exception e) {

            return null;
        }
    }


    public Message turn(int gameID ,String[] colors, int column,String nickname) throws RemoteException {
        for(int i = 0; i < clients.get(gameID).size(); i++){
            if(masterController.getGameController(gameID).getPlayers().get(i).getNickname().equals(nickname)){

                try {
                    Tile[] tiles = new Tile[colors.length];

                    for(int j=0; j< colors.length;j++){
                        tiles[j] = new Tile(ColourTile.valueOf(colors[j]));
                    }

                    masterController.getGameController(gameID).turn(tiles, column);
                } catch (EmptySlotException | InvalidPositionsException | InvalidSlotException | NoSpaceInColumnException | SoldOutTilesException | GameAlreadyStarted e) {
                    return new TurnResponse(-1);
                } catch (EndGameException e) {

                    String winner = getWinner(gameID);
                    for(int j = 0; j < clients.get(gameID).size(); j++){
                        clients.get(gameID).get(i).sendMessage(new EndMessage(winner));
                    }
                }
                Message message = new TurnResponse(0);
                String player = masterController.getGameController(gameID).getCurrentPlayer().getNickname();
                for(int k=0; k<clients.get(gameID).size(); k++){
                    if(clients.get(gameID).get(k).getNickname().equals(player)){
                        playClient(k, gameID);
                        break;
                    }
                }
                return message;
            }
        }

        return null;


    }



    public Message setMaxPlayers(Message message) throws RemoteException {
        int gameID = message.getGameID();
        int maxPlayers = message.getMaxPlayers();
        masterController.getGameController(gameID).setMaxPlayers(maxPlayers);
        return new SetResponse();
    }



    public String getWinner(int gameID) throws RemoteException {
        return masterController.getGameController(gameID).endGame().getNickname();
    }


    public void sendBoard(Message message) throws RemoteException{

    }

    @Override
    public void playClient(int client, int gameID) throws RemoteException{
        Message message = new WakeMessage();
        clients.get(gameID).get(client).sendMessage(message);
    }



}