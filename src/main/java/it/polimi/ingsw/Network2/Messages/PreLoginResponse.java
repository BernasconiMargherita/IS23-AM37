package it.polimi.ingsw.Network2.Messages;

public class PreLoginResponse extends Message{
    public PreLoginResponse(int gameID) {
        super(gameID);
    }

    @Override
    public String typeMessage() {
        return "PreLoginResponse";
    }
}
