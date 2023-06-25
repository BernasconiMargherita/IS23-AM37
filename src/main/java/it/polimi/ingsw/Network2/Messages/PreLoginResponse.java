package it.polimi.ingsw.Network2.Messages;

public class PreLoginResponse extends Message{
    public PreLoginResponse(int gameID,long UID) {
        super(gameID,UID);
    }

    @Override
    public String typeMessage() {
        return "PreLoginResponse";
    }
}
