package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;
import it.polimi.ingsw.Utils.Coordinates;

import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//
/**
 * The RemoteController interface defines the remote methods that can be invoked by clients.
 */
public interface RemoteController extends Remote {
    /**
     * Adds a TCP client to the remote controller.
     *
     * @param UID    the unique identifier of the client
     * @param socket the client socket
     * @throws RemoteException if a communication-related exception occurs
     */
    void addTcpCl(long UID, Socket socket) throws RemoteException;

    /**
     * Called when a message is received from a client.
     *
     * @param message the received message
     * @throws RemoteException if a communication-related exception occurs
     */
    void onMessage(Message message) throws RemoteException;

    /**
     * Plays the client with the specified ID in the game with the specified ID.
     *
     * @param client  the client ID
     * @param gameID  the game ID
     * @throws RemoteException if a communication-related exception occurs
     */
    void playClient(int client, int gameID) throws RemoteException;


    /**
     * Initializes the game with the specified ID.
     *
     * @param gameID the game ID
     * @throws RemoteException if a communication-related exception occurs
     */
    void initGame(int gameID) throws RemoteException;

    /**
     * Adds an RMI client to the remote controller.
     *
     * @param UID      the unique identifier of the client
     * @param protocol the communication protocol of the RMI client
     * @throws RemoteException if a communication-related exception occurs
     */
    void addRmiCl(long UID, CommunicationProtocol protocol) throws RemoteException;

    /**
     * Starts a new game and returns the game ID.
     *
     * @return the game ID
     * @throws RemoteException if a communication-related exception occurs
     */
    int startGame() throws RemoteException;

    /**
     * Removes the specified positions from the game with the specified ID for the client with the given UID.
     *
     * @param gameID    the game ID
     * @param positions the positions to be removed
     * @param UID       the client UID
     * @throws RemoteException if a communication-related exception occurs
     */
    void remove(int gameID, ArrayList<Coordinates> positions, Long UID) throws RemoteException;

    /**
     * Performs a turn in the game with the specified ID for the client with the given UID.
     *
     * @param gameID   the game ID
     * @param colors   the available colors
     * @param column   the column to play
     * @param nickname the nickname of the client
     * @param UID      the client UID
     * @throws RemoteException if a communication-related exception occurs
     */
    void turn(int gameID ,String[] colors, int column,String nickname, Long UID) throws RemoteException;

    /**
     * Registers a player with the specified nickname and UID in the game with the given ID.
     *
     * @param gameID   the game ID
     * @param nickname the nickname of the player
     * @param UID      the player UID
     * @throws RemoteException if a communication-related exception occurs
     */
    void registerPlayer(int gameID , String nickname, Long UID) throws RemoteException;

    /**
     * Retrieves the winner of the game with the specified ID.
     *
     * @param gameID the game ID
     * @return the winner's nickname
     * @throws RemoteException if a communication-related exception occurs
     */
    String getWinner(int gameID) throws RemoteException;


    /**
     * Sends the game board to the clients in the game with the specified ID.
     *
     * @param gameID the game ID
     * @throws RemoteException if a communication-related exception occurs
     */
    void sendBoard(int gameID) throws RemoteException;

    /**
     * Sets the maximum number of players for the game based on the received message.
     *
     * @param message the message containing the maximum number of players
     * @throws RemoteException if a communication-related exception occurs
     */
    void setMaxPlayers(Message message) throws RemoteException;


    /**
     * Performs pre-registration for the game based on the received message.
     *
     * @param message the pre-registration message
     * @throws RemoteException if a communication-related exception occurs
     */
    void preRegistration(Message message) throws RemoteException;
}
