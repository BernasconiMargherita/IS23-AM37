package it.polimi.ingsw.Exception;

public class GameAlreadyStarted extends RuntimeException {
    public GameAlreadyStarted(String s) {
        super(s);
    }
}
