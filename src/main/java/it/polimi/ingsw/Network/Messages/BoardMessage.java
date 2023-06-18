package it.polimi.ingsw.Network.Messages;

import java.io.Serializable;

public class BoardMessage extends Message implements Serializable {
    public BoardMessage(String message) {
        super(message);
    }

}
