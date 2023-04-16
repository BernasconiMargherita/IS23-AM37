package it.polimi.ingsw.Network;

import it.polimi.ingsw.model.Player.Player;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *The ClientImpl class represents a client object that connects to the server via RMI and interacts with the game.
 */
public class ClientImpl {

    private final int gameID;
    private final RemoteController server;

    private final Player player;
    private boolean myTurn;
    static Scanner scanner = new Scanner(System.in);


    /**
     * Constructs a new client object and registers the player with the server.
     * @param server the remote server object
     * @param player the player object representing the client
     * @throws Exception if there is an error while registering the player with the server
     */
    protected ClientImpl(RemoteController server, Player player) throws Exception{
        Scanner scanner = new Scanner(System.in);
        this.server = server;
        this.player = player;
        try{
            gameID = server.registerPlayer(player, server.getCurrentGameID());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected as " + player.getNickname());
    }

    /**
     * Returns whether it is currently the client's turn to play.
     * @return true if it is the client's turn to play, false otherwise
     */
    public boolean isMyTurn(){
        try {
            return server.getCurrentPlayer(gameID).equals(player);
        } catch (java.rmi.RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the game ID associated with the client.
     * @return the game ID associated with the client
     */
    public int getGameID() {
        return gameID;
    }
}