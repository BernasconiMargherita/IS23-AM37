package it.polimi.ingsw.Network2.Messages;

public class UsernameError extends Message{
    private int gameID;
    public UsernameError(int gameID) {
        super(gameID);
    }

    @Override
    public String typeMessage() {
        return "UsernameError";
    }

    @Override
    public int getGameID() {
        return gameID;
    }
}
