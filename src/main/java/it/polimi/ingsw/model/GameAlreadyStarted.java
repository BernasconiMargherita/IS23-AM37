package it.polimi.ingsw.model;

public class GameAlreadyStarted extends RuntimeException {
    public GameAlreadyStarted(String s) {
        super(s);
    }
}
