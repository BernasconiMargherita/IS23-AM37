package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class LoginMessage extends Message implements Serializable {

    private String nickname;
    private String protocol;
    private long UID;

    public LoginMessage(String nickname, int gameId, long UID) {
        super(gameId);
        this.nickname = nickname;
        this.UID = UID;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProtocol() {
        return protocol;
    }

    public long getUID() {
        return UID;
    }

    public String typeMessage(){
        return "LoginMessage";
    }
}
