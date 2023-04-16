package it.polimi.ingsw.Network;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Tile.Tile;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoteControllerImpl implements RemoteController {

    private final MasterController masterController;
    private Tile[] tiles;
    private int currentGameID;
    Scanner scanner = new Scanner(System.in);


    public RemoteControllerImpl() {
        masterController = new MasterController();
        currentGameID = 0;
    }

    @Override
    public void startGame() throws RemoteException {
        masterController.newGameController();
        currentGameID++;
    }

    @Override
    public int registerPlayer(Player player, int gameID) throws RemoteException {
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
        return gameID;
    }



    public void initGame(int gameID) throws RemoteException{
        try{
            this.masterController.getGameController(gameID).initGame();
        } catch (GameNotReadyException | GameAlreadyStarted e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void placeInShelf(int gameID) throws RemoteException {

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
            }
        }
    }

    public int getCurrentGameID() {
        return currentGameID;
    }

    public Player getCurrentPlayer(int gameID){
        return masterController.getGameController(gameID).getCurrentPlayer();
    }
}