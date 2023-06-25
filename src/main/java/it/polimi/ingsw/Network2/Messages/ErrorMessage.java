package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class ErrorMessage extends Message implements Serializable {

    public ErrorMessage(String message,int gameID,long UID) {
        super(gameID,UID);
    }
    public String typeMessage(){
        return "ErrorMessage";
    }




}
