package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class WakeMessage extends Message implements Serializable {
    public WakeMessage(int gameID,long UID) {
        super(gameID,UID);
    }

    public String typeMessage(){
        return "WakeMessage";
    }
}
