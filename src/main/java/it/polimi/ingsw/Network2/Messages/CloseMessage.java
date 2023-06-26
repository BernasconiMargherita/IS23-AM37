package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class CloseMessage extends Message implements Serializable {
    private  String typeMessage ;
    public CloseMessage(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "CloseMessage";
    }
    @Override
    public String typeMessage() {
        return "CloseMessage";
    }

}
