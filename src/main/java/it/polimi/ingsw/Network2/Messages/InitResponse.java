package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class InitResponse extends Message implements Serializable {

    private String typeMessage ;

    public InitResponse(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "InitResponse";
    }



    @Override
    public String typeMessage() {
        return "InitResponse";
    }


}
