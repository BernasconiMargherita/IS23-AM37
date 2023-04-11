package it.polimi.ingsw.model;

import it.polimi.ingsw.Exception.GameAlreadyStarted;
import it.polimi.ingsw.Exception.MaxPlayerException;
import it.polimi.ingsw.model.Player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
   
    static Game FullGame(){
        Game game=new Game();
        Player player= new Player("Nicola");
        Player player1=new Player("Ramiro");
        Player player2=new Player("Margherita");
        Player player3=new Player("Alessandra");
        return game;
    }
    @Test
    void addPlayer() {
        Game game = FullGame();
        Player player4=new Player("Pippo");
        try {
            game.addPlayer(player4);
        } catch (GameAlreadyStarted | MaxPlayerException e) {
            System.out.println("Error!");
        }

    }

    @Test
    void isGameReadyToStart() {
        Game game=new Game();
        assertFalse(game.isGameReadyToStart());

        Game game1 = FullGame();
        assertTrue(game1.isGameReadyToStart());
    }

    @Test
    void gameInit() {
    }


    @Test
    void placeInShelf() {
    }

    @Test
    void checkCommonTarget() {
    }

    @Test
    void checkPersonalTarget() {
    }

    @Test
    void isShelfFull() {
    }

    @Test
    void getPlayers() {
    }

    @Test
    void getGameState() {
    }

    @Test
    void setGameState() {
    }

    @Test
    void isLastTurn() {
    }

    @Test
    void setLastTurn() {
    }
}