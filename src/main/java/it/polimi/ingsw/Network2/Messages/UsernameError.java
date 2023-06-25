package it.polimi.ingsw.Network2.Messages;

public class UsernameError extends Message{

    public UsernameError(int gameID,long UID) {
        super(gameID,UID);
    }

    @Override
    public String typeMessage() {
        return "UsernameError";
    }

}
