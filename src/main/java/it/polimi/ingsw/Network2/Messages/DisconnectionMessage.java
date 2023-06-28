package it.polimi.ingsw.Network2.Messages;

public class DisconnectionMessage extends Message {

    private final String typeMessage;

    public DisconnectionMessage(int gameID, Long UID) {
        super(gameID, UID);
        this.typeMessage = "DisconnectionMessage";
    }

    public String typeMessage(){
        return "DisconnectionMessage";
    }
}
