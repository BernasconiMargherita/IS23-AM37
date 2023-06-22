package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class SetResponse extends Message implements Serializable {
    public SetResponse() {
        super(-1);
    }

    public String typeMessage(){
        return "SetResponse";
    }

}
