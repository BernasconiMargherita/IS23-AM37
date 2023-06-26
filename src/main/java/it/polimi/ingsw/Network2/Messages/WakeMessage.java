package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class WakeMessage extends Message implements Serializable {
    private String typeMessage ;
    public WakeMessage(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "WakeMessage";
    }

    public String typeMessage(){
        return "WakeMessage";
    }
}
