package it.polimi.ingsw.Network.Messages;

import java.io.Serializable;

public class ChatMessage extends Message implements Serializable {
    public ChatMessage(String message) {
        super(message);
    }
}
