package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class WakeMessage extends Message implements Serializable {
    public WakeMessage() {
        super(-1);
    }

    public String typeMessage(){
        return "WakeMessage";
    }
}
