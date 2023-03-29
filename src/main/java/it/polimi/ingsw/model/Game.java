package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * class that manage the logic of the game, receiving messages from the controller to evolve the game
 */
public class Game {

    private Board board;
    private PersonalDeck personalDeck;
    private ArrayList<Player> playerArray;
    private GameState gameState;
    private boolean isLastTurn;

    public Game(ArrayList<Player> playerArray) {
        this.playerArray = playerArray;
        this.board = new Board(playerArray.size());
        this.personalDeck = new PersonalDeck(playerArray.size());
        this.gameState = GameState.WAITING_PLAYERS;
        this.isLastTurn = false;
    }

    
}
