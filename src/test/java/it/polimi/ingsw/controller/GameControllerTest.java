package it.polimi.ingsw.controller;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Tile.ColourTile;
import it.polimi.ingsw.model.Tile.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void login() {
        GameController gameController= new GameController();
        try {
            gameController.login("Nicola");
            gameController.login("Alessandra");
            gameController.login("Margherita");
            gameController.login("Ramiro");
        } catch (UsernameException e) {
            System.out.println("Username already taken");
        } catch (GameAlreadyStarted e) {
            System.out.println("Game already started");
        } catch (MaxPlayerException e) {
            System.out.println("Max player reached");
        }
    }
    @Test
    void login5Players() {
        GameController gameController= new GameController();
        try {
            gameController.login("Nicola");
            gameController.login("Alessandra");
            gameController.login("Margherita");
            gameController.login("Ramiro");
            gameController.login("Andrea");
        } catch (UsernameException e) {
            System.out.println("Username already taken");
        } catch (GameAlreadyStarted e) {
            System.out.println("Game already started");
        } catch (MaxPlayerException e) {
            System.out.println("Max player reached");
        }
    }
    @Test
    void loginUsername() {
        GameController gameController= new GameController();
        try {
            gameController.login("Nicola");
            gameController.login("Alessandra");
            gameController.login("Margherita");
            gameController.login("Ramiro");
            gameController.login("Nicola");
        } catch (UsernameException e) {
            System.out.println("Username already taken");
        } catch (GameAlreadyStarted e) {
            System.out.println("Game already started");
        } catch (MaxPlayerException e) {
            System.out.println("Max player reached");
        }
    }
    @Test
    void GameAlreadyStarted() {
        GameController gameController= new GameController();
        try {
            gameController.login("Nicola");
            gameController.login("Alessandra");
            gameController.login("Margherita");
            gameController.login("Ramiro");
        } catch (UsernameException e) {
            System.out.println("Username already taken");
        } catch (GameAlreadyStarted e) {
            System.out.println("Game already started 1");
        } catch (MaxPlayerException e) {
            System.out.println("Max player reached");
        }

        try {
            gameController.initGame();
        } catch (GameNotReadyException e) {
            throw new RuntimeException("Game Not Ready");
        } catch (GameAlreadyStarted e) {
            throw new RuntimeException("Game already started 2");
        }

        try {
            gameController.login("Andrea");
        } catch (UsernameException e) {
            System.out.println("Username already taken");
        } catch (GameAlreadyStarted e) {
            System.out.println("Game already started 3");
        } catch (MaxPlayerException e) {
            System.out.println("Max player reached");
        }
    }


    @Test
    void turn() {
        GameController gameController= new GameController();
        Player firstPlayer=null;
        try {
            gameController.login("Nicola");
            gameController.login("Alessandra");
            gameController.login("Margherita");
            gameController.login("Ramiro");
        } catch (UsernameException e) {
            System.out.println("Username already taken");
        } catch (GameAlreadyStarted e) {
            System.out.println("Game already started");
        } catch (MaxPlayerException e) {
            System.out.println("Max player reached");
        }

        try {
            gameController.initGame();
            firstPlayer=gameController.getCurrentPlayer();
        } catch (GameNotReadyException e) {
            throw new RuntimeException("Game Not Ready");
        } catch (GameAlreadyStarted e) {
            throw new RuntimeException("Game already started");
        }
            Tile[] tiles;
        try {
            tiles= gameController.remove(new Coordinates[]{new Coordinates(1,5),new Coordinates(1,4)});
        } catch (EmptySlotException | InvalidPositionsException | InvalidSlotException e) {
            throw new RuntimeException(e);
        }

        try {

            gameController.turn(tiles ,0);
        } catch (EmptySlotException | SoldOutTilesException | EndGameException | NoSpaceInColumnException |
                 InvalidSlotException | InvalidPositionsException | GameAlreadyStarted e) {
            throw new RuntimeException(e);
        }

        assertFalse(firstPlayer.getPersonalShelf().getShelf()[1][0].isFree());


    }


    @Test
    void endGame() {
    }

    @Test
    void nextTurn() {
    }

    @Test
    void getPlayerByNickname() {
    }

    @Test
    void setMaxPlayers() {
    }
}