package it.polimi.ingsw.Network.Messages;

import java.io.Serializable;

public class ErrorMessage extends Message implements Serializable {

    public ErrorMessage(String message) {
        super(message);
    }

}
