package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class RemoveResponse extends Message implements Serializable {

    public RemoveResponse(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "RemoveResponse";
    }
    public String typeMessage(){
        return "RemoveResponse";
    }
    private String typeMessage ;
}
