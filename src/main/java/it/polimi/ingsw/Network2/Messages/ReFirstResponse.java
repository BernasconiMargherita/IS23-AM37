package it.polimi.ingsw.Network2.Messages;

public class ReFirstResponse extends Message {
    private String typeMessage ;
    public ReFirstResponse(int gameID, Long UID) {
        super(gameID, UID);
        this.typeMessage = "ReFirstResponse";
    }

    @Override
    public String typeMessage() {
        return "ReFirstResponse";
    }

}
