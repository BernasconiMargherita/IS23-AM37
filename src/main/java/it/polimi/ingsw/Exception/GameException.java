package it.polimi.ingsw.Exception;

public abstract class GameException extends Exception {

    public GameException(String message) {
        super(message);
    }
}