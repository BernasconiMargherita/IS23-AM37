package it.polimi.ingsw.model.Board;

import java.io.Serializable;

/**
 * Token given to the player who first finishes filling the library
 */

public class EndGameToken implements Serializable {

    private boolean taken;



    public EndGameToken() {
        this.taken = false;
    }
    public void setTaken() {
        this.taken=true;

    }
    public boolean isTaken() {
        return taken;
    }
}
