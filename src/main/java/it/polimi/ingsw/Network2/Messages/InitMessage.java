package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class InitMessage extends Message implements Serializable {

    public InitMessage(int gameID) {
        super(gameID);
    }

    public boolean init(){
        return true;
    }

    public String typeMessage(){
        return "InitMessage";
    }
}
