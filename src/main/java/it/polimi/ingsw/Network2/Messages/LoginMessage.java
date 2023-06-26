package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class LoginMessage extends Message implements Serializable {

    private String nickname;
    private String protocol;
    private String typeMessage ;

    public LoginMessage(String nickname, int gameId, long UID) {
        super(gameId,UID);
        this.nickname = nickname;
        this.typeMessage = "LoginMessage";
    }

    public String getNickname() {
        return nickname;
    }

    public String getProtocol() {
        return protocol;
    }


    public String typeMessage(){
        return "LoginMessage";
    }
}
