package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class LoginMessage extends Message implements Serializable {

    private String nickname;
    private String protocol;
    private long UID;

    public LoginMessage(String nickname, String protocol, long UID) {
        super(0);
        this.nickname = nickname;
        this.protocol = protocol;
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
