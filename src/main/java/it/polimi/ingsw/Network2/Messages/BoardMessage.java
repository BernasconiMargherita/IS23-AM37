package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class BoardMessage extends Message implements Serializable {
    String nickname;
    int gameID;

    public BoardMessage(String nickname, int gameID,Long UID) {
        super(gameID,UID);
        this.nickname = nickname;
    }

    @Override
    public String typeMessage() {
        return "BoardMessage";
    }

    @Override
    public String getNickname() {
        return nickname;
    }

}
