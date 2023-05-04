package it.polimi.ingsw.Network;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.Player.Player;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public interface RemoteController extends Remote {
    public void ping(RemoteClient client) throws RemoteException;
    public void playClient(RemoteClient client) throws RemoteException;

    /**
     * Initializes the game with the given game ID.
     * @param gameID the game ID to initialize
     * @throws RemoteException if there is an issue with the remote method call
     */
    void initGame(int gameID) throws RemoteException;

    /**
     * Creates a new game controller instance and increments the currentGameID.
     * @throws RemoteException if there is an issue with the remote method call
     */
    int startGame() throws RemoteException;
    public void setMaxPlayers(int gameID, int maxPlayers) throws RemoteException;

    /**
     * Allows the player to place tiles in their shelf and then insert them into a column.
     * If the slot is invalid or empty, prompts the player to select a valid slot.
     * If there is no space in the selected column, prompts the player to select a different column.
     * @param gameID ID of the game
     * @throws RemoteException if there is an issue with the remote method call
     */
    void remove(int gameID, int client) throws RemoteException;
    boolean isGameOver() throws RemoteException;
    void turn(int gameID, int column) throws RemoteException;

    /**
     * Registers a player in the game with the given gameID and returns the gameID.
     * If the game is already full, a new game is started and the player is registered there instead.
     * @param player the player to register
     * @param gameID the game ID to register the player in
     * @return the game ID that the player is registered in
     * @throws RemoteException if there is an issue with the remote method call
     */
     int registerPlayer(Player player, int gameID, RemoteClient client) throws RemoteException;

    /**
     * Returns the current game ID.
     * @return the current game ID
     */
    public int getCurrentGameID() throws RemoteException;

    /**
     * Returns the current player for the given game ID.
     * @param gameID the ID of the game
     * @return the current player for the given game ID
     */
    public Player getCurrentPlayer(int gameID) throws RemoteException;
    public void addClient(RemoteClient client, int gameID) throws RemoteException;
    public boolean imTheFirst(int gameID) throws RemoteException;
    public int getPositionInArrayServer() throws RemoteException;
    public List<RemoteClient> getConnectedClients(int gameID) throws RemoteException;
    public String getWinner(int gameID) throws RemoteException;


    public MasterController getMasterController() throws RemoteException;
}