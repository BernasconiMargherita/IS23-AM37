package it.polimi.ingsw.Network2.Messages;

import com.google.gson.Gson;

import java.io.Serializable;

public class BoardMessage extends Message implements Serializable {
    private String nickname;
    private String typeMessage ;

    public BoardMessage(String nickname, int gameID,Long UID) {
        super(gameID,UID);
        this.nickname = nickname;
        this.typeMessage = "BoardMessage";
    }

    @Override
    public String typeMessage() {
        return "BoardMessage";
    }

    @Override
    public String getNickname() {
        return nickname;
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
