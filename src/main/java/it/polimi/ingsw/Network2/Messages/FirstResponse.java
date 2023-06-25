package it.polimi.ingsw.Network2.Messages;

public class FirstResponse extends Message{
    public FirstResponse(int gameID,long UID) {
        super(gameID,UID);
    }

    @Override
    public String typeMessage() {
        return "FirstResponse";
    }
}
