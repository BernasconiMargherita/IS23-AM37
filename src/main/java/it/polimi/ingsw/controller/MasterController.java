package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.GameState;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class that manage the multiInstance of Games on the same server. At every game is associated a GameID,
 * which will be used by Clients during communications for identify in which game they take part.
 */
public class MasterController implements Serializable {
    /**
     * The Map of the Games
     */
    private final HashMap<Integer, GameController> gameMap;

    /**
     * the ID
     */
    private Integer gameID;

    /**
     * constructor that creates a new map and initialize the gameID to 0
     */

    public MasterController() {
        this.gameMap = new HashMap<Integer, GameController>();
        this.gameID = 0;
    }

    /**
     * this method creates a new game controller and associates a new gameID
     *
     * @return the gameID, that will be used by the Client for future communications
     */
    public Integer newGameController() {
        GameController gameController = new GameController();
        Integer newKey = gameID;
        gameMap.put(newKey, gameController);

        while (gameMap.containsKey(gameID)) {
            gameID++;
        }

        return newKey;
    }

    /**
     * Gets a game controller based on the given gameID,used by the server to call the methods of the game controller
     */
    public GameController getGameController(int gameID) {
        return gameMap.get(gameID);
    }


    public GameState getGameState(int gameID) {
        return gameMap.get(gameID).getGameState();
    }
}
