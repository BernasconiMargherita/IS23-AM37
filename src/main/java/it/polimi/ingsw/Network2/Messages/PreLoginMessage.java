package it.polimi.ingsw.Network2.Messages;

public class PreLoginMessage extends Message{

    private Long UID;
    public PreLoginMessage(int gameID, long UID) {
        super(gameID);
        this.UID = UID;
    }

    @Override
    public String typeMessage() {
        return "PreLoginMessage";
    }

    @Override
    public long getUID() {
        return UID;
    }
}
