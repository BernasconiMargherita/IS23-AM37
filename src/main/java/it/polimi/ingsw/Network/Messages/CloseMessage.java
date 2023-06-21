package it.polimi.ingsw.Network.Messages;

import java.io.Serializable;

public class CloseMessage extends Message implements Serializable {
    public CloseMessage(String message) {
        super(message);
    }

}
