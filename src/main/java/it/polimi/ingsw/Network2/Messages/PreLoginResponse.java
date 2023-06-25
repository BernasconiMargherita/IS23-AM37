package it.polimi.ingsw.Network2.Messages;

public class PreLoginResponse extends Message{
    private String typeMessage ;
    public PreLoginResponse(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "PreLoginResponse";
    }

    @Override
    public String typeMessage() {
        return "PreLoginResponse";
    }
}
