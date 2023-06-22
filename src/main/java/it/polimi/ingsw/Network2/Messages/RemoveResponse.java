package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class RemoveResponse extends Message implements Serializable {


    public RemoveResponse() {
        super(-1);
    }
    public String typeMessage(){
        return "RemoveResponse";
    }
}
