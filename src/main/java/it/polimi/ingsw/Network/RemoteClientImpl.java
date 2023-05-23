package it.polimi.ingsw.Network;

import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.GameState;
import it.polimi.ingsw.model.Player.Player;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * The ClientImpl class represents a client object that connects to the server via RMI and interacts with the game.
 */
public class RemoteClientImpl extends UnicastRemoteObject implements Serializable, RemoteClient {

    private int gameID;
    private final RemoteController server;
    public int positionInArrayServer;
    private boolean first = false;
    private boolean init = false;
    private Player player;
    private boolean myTurn;

    /**
     * Constructs a new client object and registers the player with the server.
     *
     * @param server the remote server object
     * @throws Exception if there is an error while registering the player with the server
     */
    public RemoteClientImpl(RemoteController server, Client client) throws Exception {
        super();
        this.server = server;
    }

    public boolean setNickname(String nickname) throws RemoteException {
        this.player = new Player(nickname);
        return !server.nicknameOccupato(this.player.getNickname());
    }


    public boolean registration(Client client) throws RemoteException {
        try {
            gameID = server.registerPlayer(player, server.getCurrentGameID(), client);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        if(!first){
            positionInArrayServer = server.getConnectedClients(gameID).size();
        }
        return first;
    }



    /**
     * Returns whether it is currently the client's turn to play.
     *
     * @return true if it is the client's turn to play, false otherwise
     */
    public boolean isMyTurn() throws RemoteException {
        if (server.getMasterController().getGameState(gameID) != GameState.WAITING_PLAYERS && server.getMasterController().getGameState(gameID) != GameState.GAME_INIT) {
            return server.getCurrentPlayer(gameID).equals(player);
        }
        return false;
    }

    public boolean getInit(){
        return init;
    }

    public void setInit(){
        init = true;
    }

    public void setFirst(){
        first=true;
    }

    public String getNickname() throws RemoteException {
        return player.getNickname();
    }

    /**
     * Returns the game ID associated with the client.
     *
     * @return the game ID associated with the client
     */
    public int getGameID() {
        return gameID;
    }


    public void sendMessage(String message) {
        System.out.println(message);
    }

    public int getPositionInArrayServer() throws RemoteException {
        return positionInArrayServer;
    }

    public void remove() throws RemoteException {
        server.remove(gameID, positionInArrayServer);
    }


    public void turn() throws RemoteException {
        server.turn(gameID, positionInArrayServer);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void setMaxPlayers(int maxPlayers, Client client){
       try{
           server.setMaxPlayers(gameID ,maxPlayers, client);
       } catch (RemoteException e) {
           throw new RuntimeException(e);
       }
    }

    public void pong() throws RemoteException {
        System.out.println("connected to the server");
    }



    public Coordinates getTilePosition() throws RemoteException {
        return new Coordinates(scanner.nextInt(), scanner.nextInt());
    }

    public String getString() throws RemoteException {
        return scanner.next();
    }

    public int getNum() throws RemoteException {
        return scanner.nextInt();
    }
}