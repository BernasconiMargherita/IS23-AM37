package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class ChatMessage extends Message implements Serializable {
    private final String message;
    private  String typeMessage ;

    public ChatMessage(int gameID,long UID, String message) {
        super(gameID,UID);
        this.message=message;
        this.typeMessage = "ChatMessage";
    }
    public String typeMessage(){
        return "ChatMessage";
    }

    public String getMessage() {
        return message;
    }
}
