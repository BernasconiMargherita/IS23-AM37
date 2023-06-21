package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.Utils.TileSlot;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;
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
    public void onMessage(Message message) throws RemoteException {

        if(message.typeMessage().equals( "LoginMessage")){
             registerPlayer(message);
        }
        if(message.typeMessage().equals( "SetMessage")){
             setMaxPlayers(message);
        }
        if(message.typeMessage().equals( "InitMessage")){
             initGame(message.getGameID());
        }
        if(message.typeMessage().equals( "RemoveMessage")){
             remove(message.getGameID(), message.getPositions());
        }
        if(message.typeMessage().equals( "TurnMessage")){
             turn(message.getGameID(), message.getColours(),message.getColumn(), message.getNickname());
        }
        if(message.typeMessage().equals("BoardMessage")){
            sendBoard(message.getGameID(),message.getNickname());
        }


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
                for(int i = 0; i < clients.get(currentGameID).size();i++){
                    if(clients.get(currentGameID).get(i).getNickname().equals(nickname)){
                        clients.get(currentGameID).get(i).sendMessage(new LoginResponse(false, currentGameID, false, true));
                        break;
                    }
                }
            }else{
                for(int i = 0; i < clients.get(currentGameID).size();i++){
                    if(clients.get(currentGameID).get(i).getNickname().equals(nickname)){
                        clients.get(currentGameID).get(i).sendMessage(new LoginResponse(false, currentGameID, false, false));
                        break;
                    }
                }

            }
            //return new LoginResponse("/*gli dico che gameID avrà ovvero ''currentGameID'' e che NON è il primo giocatore");
        } catch (UsernameException e) {
            for(int i = 0; i < clients.get(currentGameID).size();i++){
                if(clients.get(currentGameID).get(i).getNickname().equals(nickname)){
                    clients.get(currentGameID).get(i).sendMessage(new LoginResponse(true, -1, false, false));
                    break;
                }
            }


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

            for(int i = 0; i < clients.get(currentGameID).size();i++){
                if(clients.get(currentGameID).get(i).getNickname().equals(nickname)){
                    clients.get(currentGameID).get(i).sendMessage(new LoginResponse(false, currentGameID, true, false));
                    break;
                }
            }


            //return new LoginMessage("/*gli dico che gameID avrà ovvero ''currentGameID'' e che è il primo giocatore ");
        }

    }









    public void initGame(int gameID) throws RemoteException {
        try{
            this.masterController.getGameController(gameID).initGame();
            ArrayList<CardCommonTarget> commonTargets = masterController.getGameController(gameID).getCommonTargets();
            for(int i = 0; i < clients.get(gameID).size(); i++){
                CardPersonalTarget cardPersonalTarget = masterController.getGameController(gameID).getCardPersonalTarget(clients.get(gameID).get(i).getNickname());
                Message initResponse = new InitResponse(commonTargets,cardPersonalTarget);
                clients.get(gameID).get(i).sendMessage(initResponse);
            }
            String player = masterController.getGameController(gameID).getCurrentPlayer().getNickname();
            for(int i=0; i<clients.get(gameID).size(); i++){
                if(clients.get(gameID).get(i).getNickname().equals(player)){
                    playClient(i, gameID);
                    break;
                }
            }

        }
        catch (GameNotReadyException | GameAlreadyStarted e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void remove(int gameID, Coordinates[] positions) throws RemoteException {
        try {
            masterController.getGameController(gameID).remove(positions);
            String nickname = masterController.getGameController(gameID).getCurrentPlayer().getNickname();
            for(int i = 0; i < clients.get(gameID).size();i++){
                if(clients.get(gameID).get(i).getNickname().equals(nickname)){
                    clients.get(gameID).get(i).sendMessage(new RemoveResponse());
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void turn(int gameID ,String[] colors, int column,String nickname) throws RemoteException {
        for(int i = 0; i < clients.get(gameID).size(); i++){
            if(masterController.getGameController(gameID).getPlayers().get(i).getNickname().equals(nickname)){

                try {
                    Tile[] tiles = new Tile[colors.length];

                    for(int j=0; j< colors.length;j++){
                        tiles[j] = new Tile(ColourTile.valueOf(colors[j]));
                    }

                    masterController.getGameController(gameID).turn(tiles, column);
                } catch (EmptySlotException | InvalidPositionsException | InvalidSlotException | NoSpaceInColumnException | SoldOutTilesException | GameAlreadyStarted e) {
                    for(int j = 0; j < clients.get(gameID).size();j++){
                        if(clients.get(gameID).get(j).getNickname().equals(nickname)){
                            clients.get(gameID).get(j).sendMessage(new TurnResponse(-1));
                            break;
                        }
                    }

                } catch (EndGameException e) {

                    String winner = getWinner(gameID);
                    for(int j = 0; j < clients.get(gameID).size(); j++){
                        clients.get(gameID).get(i).sendMessage(new EndMessage(winner));
                    }
                }
                Message message = new TurnResponse(0);
                for(int j = 0; j < clients.get(gameID).size();j++){
                    if(clients.get(gameID).get(j).getNickname().equals(nickname)){
                        clients.get(gameID).get(j).sendMessage(message);
                        break;
                    }
                }
                String player = masterController.getGameController(gameID).getCurrentPlayer().getNickname();
                for(int j=0; j<clients.get(gameID).size(); j++){
                    if(clients.get(gameID).get(j).getNickname().equals(player)){
                        playClient(j, gameID);
                        break;
                    }
                }
            }
        }
    }



    public void setMaxPlayers(Message message) throws RemoteException {
        int gameID = message.getGameID();
        int maxPlayers = message.getMaxPlayers();
        masterController.getGameController(gameID).setMaxPlayers(maxPlayers);
        clients.get(gameID).get(0).sendMessage(new SetResponse());
    }



    public String getWinner(int gameID) throws RemoteException {
        return masterController.getGameController(gameID).endGame().getNickname();
    }


    public void sendBoard(int gameID, String nickname) throws RemoteException{
        TileSlot[][] board = masterController.getGameController(gameID).getBoard();
        ColourTile[][] colours = new ColourTile[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j<9 ; j++){
                if(!board[i][j].isFree()){
                    colours[i][j] = board[i][j].getAssignedTile().getColour();
                }
                else{
                    colours[i][j] = ColourTile.FREE;
                }
            }
        }

        for(int j = 0; j < clients.get(gameID).size();j++){
            if(clients.get(gameID).get(j).getNickname().equals(nickname)){
                clients.get(gameID).get(j).sendMessage(new BoardResponse(colours));
                break;
            }
        }


    }



    @Override
    public void playClient(int client, int gameID) throws RemoteException{
        Message message = new WakeMessage();
        clients.get(gameID).get(client).sendMessage(message);
    }



}