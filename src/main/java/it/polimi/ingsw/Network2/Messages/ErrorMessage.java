package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class ErrorMessage extends Message implements Serializable {

    public ErrorMessage(String message) {
        super(-1);
    }
    public String typeMessage(){
        return "ErrorMessage";
    }




}
