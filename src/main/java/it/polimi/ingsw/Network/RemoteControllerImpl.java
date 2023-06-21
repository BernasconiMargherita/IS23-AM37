package it.polimi.ingsw.Network;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network2.CommunicationProtocol;
import it.polimi.ingsw.Network2.Connection;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.Network2.RMIConnect;
import it.polimi.ingsw.Network2.TCPConnect;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Tile.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
    private HashMap<Integer, List<Socket> > rmiClients;
    private final List<ArrayList<Client>> connectedClients;
    private Tile[] tiles;
    private int currentGameID;
    private boolean gameOver;
    private final ArrayList<Pair<PairSocket, String>> tcpManager;


    /**
     * Constructor for RemoteControllerImpl class.
     * Creates a new instance of MasterController and sets the currentGameID to 0.
     */
    public RemoteControllerImpl() throws RemoteException {
        super();
        tempTcp = new HashMap<>();
        tempRmi = new HashMap<>();
        clients = new HashMap<>();
        tcpManager = new ArrayList<>();
        connectedClients = new ArrayList<>();
        masterController = new MasterController();
        currentGameID = -1;
        gameOver = false;
    }

    public void addRmiCl(long UID, CommunicationProtocol protocol) throws RemoteException{
        tempRmi.put(UID, protocol);
    }

    public void addTcpCl(long UID, Socket socket) throws RemoteException{
        tempTcp.put(UID, socket);
    }

    /**
     * Creates a new game controller instance and increments the currentGameID.
     *
     * @throws RemoteException if there is an issue with the remote method call
     */
    //***//
    @Override
    public int startGame() throws RemoteException {
        currentGameID++;
        return masterController.newGameController();
    }




    public void addClient(Client client, int gameID) throws RemoteException {
        connectedClients.get(gameID).add(client);
    }


    /**
     * Registers a player in the game with the given gameID and returns the gameID.
     * If the game is already full, a new game is started and the player is registered there instead.
     *
     * @param player the player to register
     * @param gameID the game ID to register the player in
     * @return the game ID that the player is registered in
     * @throws RemoteException if there is an issue with the remote method call
     */
    //***//
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


    public boolean imTheFirst(int gameID) throws RemoteException {
        return masterController.getGameController(gameID).getNumOfPlayers() == 1;
    }

    public boolean nicknameOccupato(String nickname) throws RemoteException{
        for (ArrayList<Client> connectedClient : connectedClients) {
            for (Client client : connectedClient) {
                System.out.println("questo è il nickname : " + client.getNickname());
                if (client.getNickname().equals(nickname)) {
                    return true;
                }
            }
        }
        return false;
    }





    @Override
    public int getPositionInArrayServer() throws RemoteException {
        return connectedClients.size() - 1;
    }


    /**
     * Initializes the game with the given game ID.
     *
     * @param gameID the game ID to initialize
     * @return
     * @throws RemoteException if there is an issue with the remote method call
     */
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

    /**
     * Allows the player to place tiles in their shelf and then insert them into a column.
     * If the slot is invalid or empty, prompts the player to select a valid slot.
     * If there is no space in the selected column, prompts the player to select a different column.
     *
     * @param gameID ID of the game
     * @throws RemoteException if there is an issue with the remote method call
     */
    @Override
    public Message remove(int gameID, Coordinates[] positions) throws RemoteException {
        try {
            masterController.getGameController(gameID).remove(positions);
            return new RemoveResponse();
        } catch (EmptySlotException e) {
           //spostiamo lato client ;
        } catch (InvalidPositionsException e) {
            //spostiamo lato client ;
        } catch (InvalidSlotException e) {
            //spostiamo lato client ;
        }

    }


    public Message turn(int gameID ,Tile[] tiles, int column,String nickname) throws RemoteException {
        for(int i = 0; i < clients.get(gameID).size(); i++){
            if(masterController.getGameController(gameID).getPlayers().get(i).getNickname().equals(nickname)){
                try {
                    masterController.getGameController(gameID).turn(tiles, column);
                } catch (EmptySlotException e) {
                    throw new RuntimeException(e);
                } catch (InvalidPositionsException e) {
                    throw new RuntimeException(e);
                } catch (InvalidSlotException e) {
                    throw new RuntimeException(e);
                } catch (NoSpaceInColumnException e) {
                    throw new RuntimeException(e);
                } catch (EndGameException e) {
                    String winner = masterController.getGameController(gameID).chooseWinner().getNickname();
                    for(int j = 0; j < clients.get(gameID).size(); j++){
                        clients.get(gameID).get(i).sendMessage(new endMessage(winner));
                    }
                    return new endMessage(winner);
                } catch (SoldOutTilesException e) {
                    throw new RuntimeException(e);
                } catch (GameAlreadyStarted e) {
                    throw new RuntimeException(e);
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

    public void setSocket(BufferedReader in, PrintWriter out, String nickname) throws RemoteException{
        tcpManager.add(new Pair(new PairSocket(in, out), nickname));
    }


    /**
     * Returns the current game ID.
     *
     * @return the current game ID
     */
    public int getCurrentGameID() {
        return currentGameID;
    }

    /**
     * Returns the current player for the given game ID.
     *
     * @param gameID the ID of the game
     * @return the current player for the given game ID
     */
    public Player getCurrentPlayer(int gameID) {
        return masterController.getGameController(gameID).getCurrentPlayer();
    }

    public MasterController getMasterController() {
        return this.masterController;
    }

    public List<Client> getConnectedClients(int gameID) throws RemoteException {
        return connectedClients.get(gameID);
    }
//***//
    public Message setMaxPlayers(Message message) throws RemoteException {
        int gameID = message.getGameID();
        int maxPlayers = message.getMaxPlayers();
        masterController.getGameController(gameID).setMaxPlayers(maxPlayers);
        return new setResponse();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinner(int gameID) throws RemoteException {
        return masterController.getGameController(gameID).endGame().getNickname();
    }

    public void ping(RemoteClient client) throws RemoteException {
        client.pong();
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

    @Override
    public void playClient(int client, int gameID) throws RemoteException{
        Message message = new wakeMessage();
        clients.get(gameID).get(client).sendMessage(message);
    }
/*
        if(!connectedClients.get(gameID).get(client).imTCP()){
            connectedClients.get(gameID).get(client).sendMessage("it's your turn!\n");
            connectedClients.get(gameID).get(client).remove();
            connectedClients.get(gameID).get(client).turn();
        }
        if(connectedClients.get(gameID).get(client).imTCP()){

            int j = 0;
            for(int i = 0 ; i < tcpManager.size(); i++){
                if(tcpManager.get(i).getSecond().equals(connectedClients.get(gameID).get(client).getNickname())){
                    j = i;
                    break;
                }
            }
            tcpManager.get(j).getFirst().getOut().println("yourTurn");
            tcpManager.get(j).getFirst().getOut().flush();
            //
            remove(gameID, client);
            turn(gameID, client);
            //
        }

        if (client == masterController.getGameController(gameID).getMaxPlayers() - 1) {
            playClient(0, gameID);
        } else {
            playClient(client + 1, gameID);
        }
    }

 */


}