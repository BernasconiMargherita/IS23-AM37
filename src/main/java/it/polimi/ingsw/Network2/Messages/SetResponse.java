package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class SetResponse extends Message implements Serializable {
    public SetResponse(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "SetResponse";
    }

    public String typeMessage(){
        return "SetResponse";
    }
    private String typeMessage ;

}
