package it.polimi.ingsw.Network2.Messages;

import com.google.gson.Gson;

public class FirstResponse extends Message{
    public FirstResponse(int gameID,long UID) {
        super(gameID,UID);
    }

    @Override
    public String typeMessage() {
        return "FirstResponse";
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
