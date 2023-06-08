package it.polimi.ingsw.Network.Messages;

import java.io.Serializable;

public class OkMessage extends Message implements Serializable {

    public OkMessage(String message) {
        super(message);
    }
}
