package it.polimi.ingsw.Network2.Messages;

public class setMessage extends Message{

    private int maxPlayers;
    private int gameID;

    public setMessage(int maxPlayers, int gameID) {
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
        return "setMessage";
    }
}
