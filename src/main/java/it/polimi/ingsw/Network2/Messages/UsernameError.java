package it.polimi.ingsw.Network2.Messages;

public class UsernameError extends Message{

    private String typeMessage ;
    public UsernameError(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "UsernameError";
    }

    @Override
    public String typeMessage() {
        return "UsernameError";
    }

}
