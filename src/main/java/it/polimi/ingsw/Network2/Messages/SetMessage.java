package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class SetMessage extends Message implements Serializable {

    private int maxPlayers;

    public SetMessage(int maxPlayers, int gameID, Long UID) {
        super(gameID,UID);
        this.maxPlayers = maxPlayers;
    }


    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public String typeMessage() {
        return "SetMessage";
    }
}
