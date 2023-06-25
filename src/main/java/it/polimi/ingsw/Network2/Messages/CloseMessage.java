package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class CloseMessage extends Message implements Serializable {
    public CloseMessage(int gameID,long UID) {
        super(gameID,UID);
    }
    @Override
    public String typeMessage() {
        return "CloseMessage";
    }

}
