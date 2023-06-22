package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class TurnResponse extends Message implements Serializable {

    private int status;

    public TurnResponse(int status) {
        super(-1);
        this.status = status;
    }

    public String typeMessage(){
        return "TurnResponse";
    }
}
