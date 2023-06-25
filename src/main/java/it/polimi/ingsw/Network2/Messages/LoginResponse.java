package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class LoginResponse extends Message implements Serializable {


    public LoginResponse(int gameID,long UID) {
        super(gameID,UID);
    }

    public String typeMessage(){
        return "LoginResponse";
    }

}
