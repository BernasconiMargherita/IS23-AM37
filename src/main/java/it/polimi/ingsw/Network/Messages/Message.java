package it.polimi.ingsw.Network.Messages;

import java.io.Serializable;

public class Message implements Serializable {
    private final String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return "Message";
    }
}
