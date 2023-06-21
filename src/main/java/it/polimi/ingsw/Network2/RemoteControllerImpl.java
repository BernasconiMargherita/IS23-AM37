package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network2.*;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.Tile.Tile;

import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * This class represents the implementation of the RemoteController interface. It provides the methods
 * to manage the remote operations of the game.
 */
public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController, Serializable {

    private final MasterController masterController;
    private HashMap<Long, Socket> tempTcp;
    private HashMap<Long, CommunicationProtocol> tempRmi;
    private HashMap<Integer, List<Connection> > clients;
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
        if(message.typeMessage().equals( "RemoveMessage")){
            return remove(message.getGameID(), message.getPositions());
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
    public Message registerPlayer(Message message) throws RemoteException {
        boolean initGame = false;
        String nickname = message.getNickname();
        long UID = message.getUID();
        try {
            masterController.getGameController(currentGameID).login(nickname);

            if(message.getProtocol().equals("TCP")){
                clients.get(currentGameID).add(new TCPConnect(tempTcp.get(UID)));
            }

            if(message.getProtocol().equals("RMI")){
                clients.get(currentGameID).add(new RMIConnect(tempRmi.get(UID)));
            }
            return new LoginResponse(false, currentGameID, false);
            //return new LoginMessage("/*gli dico che gameID avrà ovvero ''currentGameID'' e che NON è il primo giocatore");
        } catch (UsernameException e) {
            return new LoginResponse(true, -1, false);
            //return new LoginMessage("/*gli dico l'username è già usato");
        } catch (GameAlreadyStarted | MaxPlayerException | NullPointerException e) {
            startGame();
            try {
                masterController.getGameController(currentGameID).login(nickname);
                clients.put(currentGameID, new ArrayList<>());
                if(message.getProtocol().equals("TCP")){
                    clients.get(currentGameID).add(new TCPConnect(tempTcp.get(UID)));
                }
                if(message.getProtocol().equals("RMI")){
                    clients.get(currentGameID).add(new RMIConnect(tempRmi.get(UID)));
                }

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return new LoginResponse(false, currentGameID, true);
            //return new LoginMessage("/*gli dico che gameID avrà ovvero ''currentGameID'' e che è il primo giocatore ");
        }

    }









    public boolean initGame(int gameID) throws RemoteException {


        if (masterController.getGameController(gameID).getMaxPlayers() == masterController.getGameController(gameID).getNumOfPlayers()) {
            try {
                this.masterController.getGameController(gameID).initGame();
                Message initMessage = new InitMessage();
                for(int i = 0; i < clients.get(gameID).size(); i++){
                    clients.get(gameID).get(i).sendMessage(initMessage);
                }

                Random random = new Random();
                int randomNumber = random.nextInt(masterController.getGameController(gameID).getMaxPlayers());
                playClient(randomNumber, gameID);



            } catch (GameNotReadyException | GameAlreadyStarted e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        return false;
    }


    @Override
    public Message remove(int gameID, Coordinates[] positions) throws RemoteException {
        try {
            masterController.getGameController(gameID).remove(positions);
            return new RemoveResponse();
        } catch (Exception e) {
            //spostiamo lato client ;
            return null;
        }
    }


    public Message turn(int gameID ,Tile[] tiles, int column,String nickname) throws RemoteException {
        for(int i = 0; i < clients.get(gameID).size(); i++){
            if(masterController.getGameController(gameID).getPlayers().get(i).getNickname().equals(nickname)){
                try {
                    masterController.getGameController(gameID).turn(tiles, column);
                } catch (EmptySlotException | InvalidPositionsException | InvalidSlotException | NoSpaceInColumnException | SoldOutTilesException | GameAlreadyStarted e) {
                    throw new RuntimeException(e); //lo spostiamo lato client
                } catch (EndGameException e) {

                    String winner = getWinner(gameID);
                    for(int j = 0; j < clients.get(gameID).size(); j++){
                        clients.get(gameID).get(i).sendMessage(new endMessage(winner));
                    }
                    return new endMessage(winner);
                }
                Message message = new TurnResponse();
                if(i+1 != masterController.getGameController(gameID).getMaxPlayers() - 1 ){
                    clients.get(gameID).get(i+1).sendMessage(new wakeMessage());
                } else{
                    clients.get(gameID).get(0).sendMessage(new wakeMessage());
                }
                return message;
            }
        }

        return null;


    }







    /**
     * Returns the current player for the given game ID.
     *
     * @param gameID the ID of the game
     * @return the current player for the given game ID
     */





    public Message setMaxPlayers(Message message) throws RemoteException {
        int gameID = message.getGameID();
        int maxPlayers = message.getMaxPlayers();
        masterController.getGameController(gameID).setMaxPlayers(maxPlayers);
        return new setResponse();
    }



    public String getWinner(int gameID) throws RemoteException {
        return masterController.getGameController(gameID).endGame().getNickname();
    }





    @Override
    public void playClient(int client, int gameID) throws RemoteException{
        Message message = new wakeMessage();
        clients.get(gameID).get(client).sendMessage(message);
    }



}