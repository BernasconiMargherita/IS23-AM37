package it.polimi.ingsw.Network;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Tile.Tile;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the implementation of the RemoteController interface. It provides the methods
 * to manage the remote operations of the game.
 */
public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController, Serializable {

    private final MasterController masterController;
    private Tile[] tiles;
    private int currentGameID;
    private final List<ArrayList<RemoteClient>> connectedClients;
    private boolean gameOver;




    /**
     * Constructor for RemoteControllerImpl class.
     * Creates a new instance of MasterController and sets the currentGameID to 0.
     */
    public RemoteControllerImpl() throws RemoteException {
        super();
        connectedClients = new ArrayList<>();
        masterController = new MasterController();
        currentGameID = -1;
        gameOver = false;
    }
    /**
     * Creates a new game controller instance and increments the currentGameID.
     * @throws RemoteException if there is an issue with the remote method call
     */
    @Override
    public int startGame() throws RemoteException {
        currentGameID++;
        return  masterController.newGameController();
    }


    public void addClient(RemoteClient client, int gameID) throws RemoteException {
        connectedClients.get(gameID).add(client);
    }



    /**
     * Registers a player in the game with the given gameID and returns the gameID.
     * If the game is already full, a new game is started and the player is registered there instead.
     * @param player the player to register
     * @param gameID the game ID to register the player in
     * @return the game ID that the player is registered in
     * @throws RemoteException if there is an issue with the remote method call
     */
    @Override
    public int registerPlayer(Player player, int gameID, RemoteClient client) throws RemoteException {

        try{
            masterController.getGameController(gameID).login(player.getNickname());
        } catch (UsernameException e) {
            throw new RuntimeException(e);
        } catch (GameAlreadyStarted | MaxPlayerException | NullPointerException e) {
            startGame();
            client.printMessage("New Game Creation..." + "\nHow Many Players ?");
            setMaxPlayers(gameID + 1 , client.setMaxPlayers());
            gameID = gameID + 1;
            registerPlayer(player, gameID, client);
            connectedClients.add(new ArrayList<RemoteClient>());
        }

        gameID = currentGameID;
        return gameID;
    }


    public boolean imTheFirst(int gameID) throws RemoteException{
        return masterController.getGameController(gameID).getNumOfPlayers() == 1;
    }

    @Override
    public int getPositionInArrayServer() throws RemoteException {
        return connectedClients.size() - 1;
    }


    /**
     * Initializes the game with the given game ID.
     * @param gameID the game ID to initialize
     * @throws RemoteException if there is an issue with the remote method call
     */
    public void initGame(int gameID) throws RemoteException{



        if(masterController.getGameController(gameID).getMaxPlayers() == masterController.getGameController(gameID).getNumOfPlayers()) {
            try {
                this.masterController.getGameController(gameID).initGame();
            } catch (GameNotReadyException | GameAlreadyStarted e) {
                throw new RuntimeException(e);
            }

            for (RemoteClient connectedClient : connectedClients.get(gameID)) {
                connectedClient.sendMessage("initializing Game...\n");
            }
            masterController.getGameController(gameID).getPlayers()
        }



    }

    /**
     * Allows the player to place tiles in their shelf and then insert them into a column.
     * If the slot is invalid or empty, prompts the player to select a valid slot.
     * If there is no space in the selected column, prompts the player to select a different column.
     * @param gameID ID of the game
     * @throws RemoteException if there is an issue with the remote method call
     */
    @Override
    public void remove(int gameID,int client) throws RemoteException {

        ArrayList<Coordinates> positions = new ArrayList<>();
        connectedClients.get(gameID).get(client).sendMessage("Give me the positions of the tile, in order with respect to column insertion \n");
        positions.add(connectedClients.get(gameID).get(client).getTilePosition());
        connectedClients.get(gameID).get(client).sendMessage("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");

        if(connectedClients.get(gameID).get(client).getString().equals("yes")){
            positions.add(connectedClients.get(gameID).get(client).getTilePosition());
            connectedClients.get(gameID).get(client).sendMessage("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");
            if(connectedClients.get(gameID).get(client).getString().equals("yes")){
                positions.add(connectedClients.get(gameID).get(client).getTilePosition());
                connectedClients.get(gameID).get(client).sendMessage("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");
            }
        }
        Coordinates[] positionsArray = new Coordinates[positions.size()];
        for(int i = 0; i < positions.size(); i++){
            positionsArray[i] = positions.get(i);
        }

        try{
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
            }catch (EndGameException e) {
                gameOver = true;
                connectedClients.get(gameID).get(client).sendMessage("game is over !");
                connectedClients.get(gameID).get(client).sendMessage("the winner is " + masterController.getGameController(gameID).endGame().getNickname());

            } catch (EmptySlotException | InvalidPositionsException | GameAlreadyStarted | SoldOutTilesException |
                     InvalidSlotException e) {
                throw new RuntimeException(e);
            }

    }



    /**
     * Returns the current game ID.
     * @return the current game ID
     */
    public int getCurrentGameID() {
        return currentGameID;
    }

    /**
     * Returns the current player for the given game ID.
     * @param gameID the ID of the game
     * @return the current player for the given game ID
     */
    public Player getCurrentPlayer(int gameID){
        return masterController.getGameController(gameID).getCurrentPlayer();
    }

    public MasterController getMasterController(){
        return this.masterController;
    }

    public List<RemoteClient> getConnectedClients(int gameID) throws RemoteException{
        return connectedClients.get(gameID);
    }

    public void setMaxPlayers(int gameID, int maxPlayers) throws RemoteException{
        masterController.getGameController(gameID).setMaxPlayers(maxPlayers);
    }
    public boolean isGameOver(){
        return gameOver;
    }

    public String getWinner(int gameID) throws RemoteException{
        return masterController.getGameController(gameID).endGame().getNickname();
    }

    public void ping(RemoteClient client) throws RemoteException{
        client.pong();
    }

    @Override
    public void playClient(RemoteClient client) throws RemoteException {
        client.sendMessage("it's your turn!\n");
        client.remove();
        client.turn();
    }
}