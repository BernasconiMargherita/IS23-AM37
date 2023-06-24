package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class SetMessage extends Message implements Serializable {

    private int maxPlayers;
    private int gameID;
    private long UID

    public SetMessage(int maxPlayers, int gameID, Long UID) {
        super(gameID);
        this.maxPlayers = maxPlayers;
        this.UID = UID;

    }

    @Override
    public long getUID() {
        return UID;
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public String typeMessage() {
        return "SetMessage";
    }
}
