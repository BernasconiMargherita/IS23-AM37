package it.polimi.ingsw.Network2.Messages;

import com.google.gson.Gson;

public class PingMessage extends Message {
    private final String typeMessage;

    public PingMessage(int gameID, Long UID) {
        super(gameID, UID);
        this.typeMessage = "PingMessage";
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    public String typeMessage(){
        return "PingMessage";
    }
}
