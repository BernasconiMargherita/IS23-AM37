package it.polimi.ingsw.Network2.Messages;

public class PingMessage extends Message {
    private final String typeMessage;

    public PingMessage(int gameID, Long UID) {
        super(gameID, UID);
        this.typeMessage = "PingMessage";
    }

    public String typeMessage(){
        return "PingMessage";
    }
}
