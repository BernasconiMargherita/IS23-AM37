package it.polimi.ingsw.Network2.Messages;

public class SetMessage extends Message{

    private int maxPlayers;
    private int gameID;

    public SetMessage(int maxPlayers, int gameID) {
        this.maxPlayers = maxPlayers;
        this.gameID = gameID;
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
