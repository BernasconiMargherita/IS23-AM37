package it.polimi.ingsw.Network;

import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.GameState;
import it.polimi.ingsw.model.Player.Player;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *The ClientImpl class represents a client object that connects to the server via RMI and interacts with the game.
 */
public class ClientImpl extends UnicastRemoteObject implements Serializable, RemoteClient {

    private final int gameID;
    public int positionInArrayServer;
    private final RemoteController server;
    private Player player;
    private boolean myTurn;
    static Scanner scanner = new Scanner(System.in);


    /**
     * Constructs a new client object and registers the player with the server.
     * @param server the remote server object

     * @throws Exception if there is an error while registering the player with the server
     */
    protected ClientImpl(RemoteController server) throws Exception{
        super();
        server.ping(this);
        Scanner scanner = new Scanner(System.in);
        this.server = server;
        System.out.println("Enter your Nickname : ");
        this.player = new Player(scanner.next());




        try{
            gameID = server.registerPlayer(player, server.getCurrentGameID(), this);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected as " + player.getNickname());
        server.addClient(this, gameID);
        positionInArrayServer = server.getConnectedClients(gameID).size()-1;
        server.initGame(gameID);
    }

    /**
     * Returns whether it is currently the client's turn to play.
     * @return true if it is the client's turn to play, false otherwise
     */
    public boolean isMyTurn() throws RemoteException {
        if(server.getMasterController().getGameState(gameID) != GameState.WAITING_PLAYERS  && server.getMasterController().getGameState(gameID) !=GameState.GAME_INIT){
           return server.getCurrentPlayer(gameID).equals(player);
        }
        return false;
    }

    /**
     * Returns the game ID associated with the client.
     * @return the game ID associated with the client
     */
    public int getGameID() {
        return gameID;
    }


    public void sendMessage(String message) {
        System.out.println(message);
    }

    public int getPositionInArrayServer() throws RemoteException{
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
    public int setMaxPlayers(){
        return scanner.nextInt();
    }
    public void pong() throws RemoteException{
        System.out.println("connected to the server");
    }

    public void setNickname(){
        this.player = new Player(scanner.next());
    }
    public Coordinates getTilePosition() throws RemoteException{
        return new Coordinates(scanner.nextInt(), scanner.nextInt());
    }

    public String getString() throws RemoteException{
        return scanner.next();
    }

    public int getNum() throws RemoteException{
        return scanner.nextInt();
    }
}