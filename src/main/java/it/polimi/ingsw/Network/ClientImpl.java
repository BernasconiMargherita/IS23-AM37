package it.polimi.ingsw.Network;

import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.GameState;
import it.polimi.ingsw.model.Player.Player;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *The ClientImpl class represents a client object that connects to the server via RMI and interacts with the game.
 */
public class ClientImpl implements Serializable {

    private final int gameID;
    public int positionInArrayServer;
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
        server.addClient(this);
        positionInArrayServer = server.getConnectedClients().size()-1;
        if(server.imTheFirst(gameID)){
            System.out.println("New Game Creation...");
            System.out.println("How many players ?");
            server.setMaxPlayers(gameID, scanner.nextInt());
            System.out.println("Maxplayers ->> " + server.getMasterController().getGameController(gameID).getMaxPlayers());
        };
        server.getMasterController().getGameController(gameID);
        boolean flagInitGame = server.initGame(gameID);
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
}