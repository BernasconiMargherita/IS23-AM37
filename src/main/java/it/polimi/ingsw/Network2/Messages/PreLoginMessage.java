package it.polimi.ingsw.Network2.Messages;

import com.google.gson.Gson;

public class PreLoginMessage extends Message{

    String nickname;
    String typeMessage ;
    public PreLoginMessage(int gameID, long UID, String nickname) {
        super(gameID,UID);
        this.nickname = nickname;
        this.typeMessage = "PreLoginMessage";
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    @Override
    public String typeMessage() {
        return "PreLoginMessage";
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
