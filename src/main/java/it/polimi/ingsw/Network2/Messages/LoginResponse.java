package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class LoginResponse extends Message implements Serializable {

    private String typeMessage ;
    public LoginResponse(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "LoginResponse";
    }

    public String typeMessage(){
        return "LoginResponse";
    }

}
