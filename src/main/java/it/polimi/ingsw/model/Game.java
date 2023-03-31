package it.polimi.ingsw.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * class that manage the logic of the game, receiving messages from the controller to evolve the game
 */
public class Game {
    public static int MAX_PLAYERS=4;
    public static int MIN_PLAYERS=2;
    private Board board;
    private PersonalDeck personalDeck;
    private List<Player> players;
    private GameState gameState;
    private boolean isLastTurn;
    private Utils utils;

    public Game(ArrayList<Player> playerArray) throws SoldOutTilesException, FileNotFoundException {
        this.players= new ArrayList<>();
        this.board = null;
        this.personalDeck = new PersonalDeck(playerArray.size());
        this.gameState = GameState.WAITING_PLAYERS;
        this.isLastTurn = false;
        this.utils= new Utils();
    }


    public void addPlayer(Player player) {
        if(gameState==GameState.IN_GAME) throw new GameAlreadyStarted("It is not possible to add a player when the game has already started");
        if (players.size()==MAX_PLAYERS) throw new MaxPlayerException("There are already 4 players");
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isLastTurn() {
        return isLastTurn;
    }

    public void setLastTurn(boolean lastTurn) {
        isLastTurn = lastTurn;
    }

}

