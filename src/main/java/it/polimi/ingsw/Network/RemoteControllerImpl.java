package it.polimi.ingsw.Network;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Tile.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the implementation of the RemoteController interface. It provides the methods
 * to manage the remote operations of the game.
 */
public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController, Serializable {

    private final MasterController masterController;
    private final List<ArrayList<Client>> connectedClients;
    private Tile[] tiles;
    private int currentGameID;
    private boolean gameOver;

    private BufferedReader in = null;
    private PrintWriter out = null;
    private  ArrayList<Paair<PairSocket, String>> tcpManager;


    /**
     * Constructor for RemoteControllerImpl class.
     * Creates a new instance of MasterController and sets the currentGameID to 0.
     */
    public RemoteControllerImpl() throws RemoteException {
        super();
        tcpManager = new ArrayList<>();
        connectedClients = new ArrayList<>();
        masterController = new MasterController();
        currentGameID = -1;
        gameOver = false;
    }

    /**
     * Creates a new game controller instance and increments the currentGameID.
     *
     * @throws RemoteException if there is an issue with the remote method call
     */
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
    @Override
    public int registerPlayer(Player player, int gameID, Client client) throws RemoteException {

        if(gameID == -2) {
            gameID = getCurrentGameID();
        }else{
            gameID = gameID;
        }
        try {
            masterController.getGameController(gameID).login(player.getNickname());
        } catch (UsernameException e) {
            throw new RuntimeException(e);
        } catch (GameAlreadyStarted | MaxPlayerException | NullPointerException e) {
            startGame();
            client.printMessage("New Game Creation..." + "\nHow Many Players ?");

            setMaxPlayers(gameID + 1, client);
            gameID = gameID + 1;
            registerPlayer(player, gameID, client);
            connectedClients.add(new ArrayList<Client>());
            if(client.imTCP()){
                addClient(client, gameID);
            }

        }

        gameID = currentGameID;


        System.out.println("finiscelaregistratiofsofgsdgdfg");
        return gameID;
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
     * @throws RemoteException if there is an issue with the remote method call
     */
    public void initGame(int gameID) throws RemoteException {


        if (masterController.getGameController(gameID).getMaxPlayers() == masterController.getGameController(gameID).getNumOfPlayers()) {
            System.out.println("fa la init");
            try {
                this.masterController.getGameController(gameID).initGame();
            } catch (GameNotReadyException | GameAlreadyStarted e) {
                throw new RuntimeException(e);
            }

            for (Client connectedClient : connectedClients.get(gameID)) {
                if(!connectedClient.imTCP()){
                    connectedClient.sendMessage("initializing Game...\n");
                }
                else{
                    int j = 0;
                    for(int i=0; i < tcpManager.size(); i++){
                        if(connectedClient.getNickname().equals(tcpManager.get(i).getSecond())){
                            j=i;
                            break;
                        }
                    }
                    tcpManager.get(j).getFirst().getOut().println("initializing Game...\n");
                    tcpManager.get(j).getFirst().getOut().flush();
                }
            }
            for (int i = 0; i < masterController.getGameController(gameID).getPlayers().size(); i++) {
                if (!(masterController.getGameController(gameID).getPlayers().get(i).getNickname().equals(connectedClients.get(gameID).get(i).getNickname()))) {
                    Collections.swap(connectedClients.get(gameID), i, i + 1);
                }
            }

            playClient(0, gameID);
        }else System.out.println("NON fa la init"+ "\nMAXPLAYERS -> " + masterController.getGameController(gameID).getMaxPlayers() +"\nnumPLAYERS -> "+ masterController.getGameController(gameID).getNumOfPlayers());


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
    public void remove(int gameID, int client) throws RemoteException {

        ArrayList<Coordinates> positions = new ArrayList<>();
        connectedClients.get(gameID).get(client).sendMessage("Give me the positions of the tile, in order with respect to column insertion \n");
        positions.add(connectedClients.get(gameID).get(client).getTilePosition());
        connectedClients.get(gameID).get(client).sendMessage("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");

        if (connectedClients.get(gameID).get(client).getString().equals("yes")) {
            positions.add(connectedClients.get(gameID).get(client).getTilePosition());
            connectedClients.get(gameID).get(client).sendMessage("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");
            if (connectedClients.get(gameID).get(client).getString().equals("yes")) {
                positions.add(connectedClients.get(gameID).get(client).getTilePosition());
                connectedClients.get(gameID).get(client).sendMessage("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");
            }
        }
        Coordinates[] positionsArray = new Coordinates[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            positionsArray[i] = positions.get(i);
        }

        try {
            tiles = masterController.getGameController(gameID).remove(positionsArray);
        } catch (EmptySlotException e) {
            connectedClients.get(gameID).get(client).sendMessage("empty slot selected, select valid slots");
            remove(gameID, client);
        } catch (InvalidPositionsException | InvalidSlotException e) {
            connectedClients.get(gameID).get(client).sendMessage("invalid slot selected, select valid slots");
            remove(gameID, client);
        }

    }


    public void turn(int gameID, int client) throws RemoteException {

        connectedClients.get(gameID).get(client).sendMessage("insert the column please : ");

        try {
            masterController.getGameController(gameID).turn(tiles, connectedClients.get(gameID).get(client).getNum());
        } catch (NoSpaceInColumnException e) {
            connectedClients.get(gameID).get(client).sendMessage("Wrong Column");
            turn(gameID, client);
        } catch (EndGameException e) {
            gameOver = true;
            connectedClients.get(gameID).get(client).sendMessage("game is over !");
            connectedClients.get(gameID).get(client).sendMessage("the winner is " + masterController.getGameController(gameID).endGame().getNickname());

        } catch (EmptySlotException | InvalidPositionsException | GameAlreadyStarted | SoldOutTilesException |
                 InvalidSlotException e) {
            throw new RuntimeException(e);
        }

    }

    public void setSocket(BufferedReader in, PrintWriter out, String nickname) throws RemoteException{
        tcpManager.add(new Paair(new PairSocket(in, out), nickname));
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

    public void setMaxPlayers(int gameID, Client client) throws RemoteException {
        if(!client.imTCP()) {
            masterController.getGameController(gameID).setMaxPlayers(client.setMaxPlayers());
        }

        else{
            System.out.println("sei nel posto giusto");
            int j = 0;
            for(int i = 0; i < tcpManager.size(); i++){
                if(tcpManager.get(i).getSecond().equals(client.getNickname())){
                    j = i;
                    break;
                }
            }
            tcpManager.get(j).getFirst().getOut().println("setMaxPlayer");
            tcpManager.get(j).getFirst().getOut().flush();
            System.out.println("cia rrivoqua");
            try{
                int maxPlayers = Integer.parseInt(tcpManager.get(j).getFirst().getIn().readLine());
                System.out.println(maxPlayers);
                masterController.getGameController(gameID).setMaxPlayers(maxPlayers);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
    public void playClient(int client, int gameID) throws RemoteException {
        connectedClients.get(gameID).get(client).sendMessage("it's your turn!\n");
        connectedClients.get(gameID).get(client).remove();
        connectedClients.get(gameID).get(client).turn();
        if (client == masterController.getGameController(gameID).getMaxPlayers() - 1) {
            playClient(0, gameID);
        } else {
            playClient(client + 1, gameID);
        }
    }


}