package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class ChatMessage extends Message implements Serializable {
    public ChatMessage(String message) {
        super();
    }
    public String typeMessage(){
        return "ChatMessage";
    }
}
