package it.polimi.ingsw.Network;

import it.polimi.ingsw.Network2.Messages.LoginMessage;
import it.polimi.ingsw.Network2.Messages.Message;
import it.polimi.ingsw.Network2.CommunicationProtocol;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.Player.Player;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RemoteController extends Remote {
    public void addTcpCl(long UID, Socket socket) throws RemoteException;
    public void ping(RemoteClient client) throws RemoteException;
    public Message onMessage(Message message) throws RemoteException;

    public void playClient(int client, int gameID) throws RemoteException;

    /**
     * Initializes the game with the given game ID.
     *
     * @param gameID the game ID to initialize
     * @return
     * @throws RemoteException if there is an issue with the remote method call
     */
    boolean initGame(int gameID) throws RemoteException;
    public void addRmiCl(long UID, CommunicationProtocol protocol) throws RemoteException;

    /**
     * Creates a new game controller instance and increments the currentGameID.
     *
     * @throws RemoteException if there is an issue with the remote method call
     */
    int startGame() throws RemoteException;



    /**
     * Allows the player to place tiles in their shelf and then insert them into a column.
     * If the slot is invalid or empty, prompts the player to select a valid slot.
     * If there is no space in the selected column, prompts the player to select a different column.
     *
     * @param gameID ID of the game
     * @throws RemoteException if there is an issue with the remote method call
     */
    Message remove(int gameID, Coordinates[] positions) throws RemoteException;


    boolean isGameOver() throws RemoteException;

    void turn(int gameID, int column) throws RemoteException;

    /**
     * Registers a player in the game with the given gameID and returns the gameID.
     * If the game is already full, a new game is started and the player is registered there instead.
     *
     * @param player the player to register
     * @param gameID the game ID to register the player in
     * @return the game ID that the player is registered in
     * @throws RemoteException if there is an issue with the remote method call
     */
    Message registerPlayer(LoginMessage message) throws RemoteException;
    public void setSocket(BufferedReader in, PrintWriter out, String nickname) throws RemoteException;
    /**
     * Returns the current game ID.
     *
     * @return the current game ID
     */
    public int getCurrentGameID() throws RemoteException;

    /**
     * Returns the current player for the given game ID.
     *
     * @param gameID the ID of the game
     * @return the current player for the given game ID
     */
    public Player getCurrentPlayer(int gameID) throws RemoteException;

    public void addClient(Client client, int gameID) throws RemoteException;

    public boolean imTheFirst(int gameID) throws RemoteException;
    public boolean nicknameOccupato(String nickname) throws RemoteException;

    public int getPositionInArrayServer() throws RemoteException;

    public List<Client> getConnectedClients(int gameID) throws RemoteException;

    public String getWinner(int gameID) throws RemoteException;


    public MasterController getMasterController() throws RemoteException;
    public Message setMaxPlayers(Message message) throws RemoteException;
}