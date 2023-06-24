package it.polimi.ingsw.Network2.Messages;

public class PreLoginMessage extends Message {
    public PreLoginMessage(int gameID, long uid, String username) {
        super(gameID);
    }
}
