package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class RemoveResponse extends Message implements Serializable {
    boolean invalidSequence;
    private String typeMessage ;

    public RemoveResponse(int gameID,long UID, boolean invalidSequence) {
        super(gameID,UID);
        this.typeMessage = "RemoveResponse";
        this.invalidSequence = invalidSequence;
    }
    public String typeMessage(){
        return "RemoveResponse";
    }

    public boolean isInvalidSequence() {
        return invalidSequence;
    }


}
