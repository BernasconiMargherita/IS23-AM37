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
        server.addClient(this);
        positionInArrayServer = server.getConnectedClients().size()-1;



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

    public void remove(){

        Scanner scanner  = new Scanner(System.in);
        ArrayList<Coordinates> positions = new ArrayList<>();

        System.out.println("Give me the positions of the tiles, in order with respect to column insertion \n");
        positions.add(new Coordinates(scanner.nextInt(), scanner.nextInt()));
        System.out.println("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");
        if(scanner.next().equals("yes")){
            positions.add(new Coordinates(scanner.nextInt(), scanner.nextInt()));
            System.out.println("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");
            if(scanner.next().equals("yes")) {
                positions.add(new Coordinates(scanner.nextInt(), scanner.nextInt()));
            }
        }
        boolean retry = true;
        while(retry){
           try{
               if(server.remove(gameID, positions)){
                   positions.clear();

                   System.out.println("Wrong Positions Selected... retry");
                   System.out.println("Give me the positions of the tiles, in order with respect to column insertion \n");
                   positions.add(new Coordinates(scanner.nextInt(), scanner.nextInt()));
                   System.out.println("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");
                   if(scanner.next().equals("yes")) {
                       positions.add(new Coordinates(scanner.nextInt(), scanner.nextInt()));
                       System.out.println("if you want to select other tiles write \"yes\", otherwise write \"no\" \n");
                       if (scanner.next().equals("yes")) {
                           positions.add(new Coordinates(scanner.nextInt(), scanner.nextInt()));
                       }
                   }

               } else{
                   retry = false;
               }

        } catch (RemoteException e) {
               throw new RuntimeException(e);
           }

        }
    }


    public void turn() throws RemoteException {
        System.out.println("insert the column please : ");
        if(!server.turn(gameID, scanner.nextInt())){
            if(server.isGameOver()){
                System.out.println("Game Is Over " +  "the winner is... " + server.getWinner(gameID));

            }
            else {
                System.out.println("Wrong Column");
                turn();
            }
        };
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
}