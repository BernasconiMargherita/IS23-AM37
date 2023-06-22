package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class ChatMessage extends Message implements Serializable {
    private final String message;

    public ChatMessage(int gameID, String message) {
        super(gameID);
        this.message=message;
    }
    public String typeMessage(){
        return "ChatMessage";
    }

    public String getMessage() {
        return message;
    }
}
