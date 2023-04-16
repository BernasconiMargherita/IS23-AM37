package it.polimi.ingsw.Network;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Tile.Tile;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the implementation of the RemoteController interface. It provides the methods
 * to manage the remote operations of the game.
 */
public class RemoteControllerImpl implements RemoteController, Serializable {

    private final MasterController masterController;
    private Tile[] tiles;
    private int currentGameID;


    /**
     * Constructor for RemoteControllerImpl class.
     * Creates a new instance of MasterController and sets the currentGameID to 0.
     */
    public RemoteControllerImpl() {
        masterController = new MasterController();
        currentGameID = 0;
    }
    /**
     * Creates a new game controller instance and increments the currentGameID.
     * @throws RemoteException if there is an issue with the remote method call
     */
    @Override
    public void startGame() throws RemoteException {
        masterController.newGameController();
        currentGameID++;
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
    public int registerPlayer(Player player, int gameID) throws RemoteException {
        Scanner scanner  = new Scanner(System.in);
        try{
            masterController.getGameController(gameID).login(player.getNickname());
        } catch (UsernameException e) {
            throw new RuntimeException(e);
        } catch (GameAlreadyStarted | MaxPlayerException e) {
            System.out.println("new game creation...");
            startGame();
            registerPlayer(player, gameID);
        }

        if(masterController.getGameController(gameID).getPlayers().size() == 1){
            masterController.getGameController(gameID).setMaxPlayers((scanner.nextInt()));
        }

        if(masterController.getGameController(gameID).getMaxPlayers() == masterController.getGameController(gameID).getPlayers().size()){
           try{
               masterController.getGameController(gameID).initGame();
           } catch (GameNotReadyException | GameAlreadyStarted e) {
               throw new RuntimeException(e);
           }
        }


        return gameID;
    }


    /**
     * Initializes the game with the given game ID.
     * @param gameID the game ID to initialize
     * @throws RemoteException if there is an issue with the remote method call
     */
    public void initGame(int gameID) throws RemoteException{
        try{
            this.masterController.getGameController(gameID).initGame();
        } catch (GameNotReadyException | GameAlreadyStarted e) {
            throw new RuntimeException(e);
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
    public void placeInShelf(int gameID) throws RemoteException {

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


        Coordinates[] positionsArray = new Coordinates[positions.size()];
        for(int i = 0 ; i < positions.size(); i++){
            positionsArray[i] = positions.get(i);
        }


        try{
            tiles = masterController.getGameController(gameID).remove(positionsArray);

        } catch (EmptySlotException e) {
            System.out.println("empty slot selected, select valid slots");
            placeInShelf(gameID);
        } catch (InvalidPositionsException | InvalidSlotException e) {
            System.out.println("invalid slot selected, select valid slots");
            placeInShelf(gameID);
        }


        boolean retry = true;
        while (retry) {
            try {
                System.out.println("insert the column please : ");
                masterController.getGameController(gameID).turn(tiles, scanner.nextInt());
                retry = false; // se la funzione ha successo, esci dal ciclo
            } catch (NoSpaceInColumnException e) {
                System.out.println("no space in this column, retry please");
            }catch (EndGameException e) {
                retry = false;
                System.out.println("game is over !");
                System.out.println("the winner is " + masterController.getGameController(gameID).endGame().getNickname());
            } catch (EmptySlotException e) {
                throw new RuntimeException(e);
            } catch (GameAlreadyStarted e) {
                throw new RuntimeException(e);
            } catch (InvalidPositionsException e) {
                throw new RuntimeException(e);
            } catch (SoldOutTilesException e) {
                throw new RuntimeException(e);
            } catch (InvalidSlotException e) {
                throw new RuntimeException(e);
            }
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
}