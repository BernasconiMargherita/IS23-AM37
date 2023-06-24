package it.polimi.ingsw.Network2.Messages;

public class FirstResponse extends Message{
    public FirstResponse(int gameID) {
        super(gameID);
    }

    @Override
    public String typeMessage() {
        return "FirstResponse";
    }
}
