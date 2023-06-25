package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class InitMessage extends Message implements Serializable {

    public InitMessage(int gameID,long UID) {
        super(gameID,UID);
    }

    public boolean init(){
        return true;
    }

    public String typeMessage(){
        return "InitMessage";
    }
}
