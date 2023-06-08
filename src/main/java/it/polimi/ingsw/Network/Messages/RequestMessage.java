package it.polimi.ingsw.Network.Messages;

import java.io.Serializable;

public class RequestMessage extends Message implements Serializable {
    public RequestMessage(String message) {
        super(message);
    }
}
