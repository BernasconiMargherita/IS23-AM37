package it.polimi.ingsw.Network2.Messages;

public class ReFirstResponse extends Message {
    public ReFirstResponse(int gameID, Long UID) {
        super(gameID, UID);
    }

    @Override
    public String typeMessage() {
        return "ReFirstResponse";
    }
}
