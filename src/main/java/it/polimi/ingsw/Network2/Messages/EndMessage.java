package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class EndMessage extends Message implements Serializable {

    private String winner;

    public EndMessage(String winner,int gameID,long UID) {
        super(gameID,UID);
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public String typeMessage() {
        return "EndMessage";
    }
}
