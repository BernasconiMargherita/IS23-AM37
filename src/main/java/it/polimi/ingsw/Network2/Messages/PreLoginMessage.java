package it.polimi.ingsw.Network2.Messages;

public class PreLoginMessage extends Message{

    private Long UID;
    String nickname;
    public PreLoginMessage(int gameID, long UID, String nickname) {
        super(gameID);
        this.UID = UID;
        this.nickname = nickname;
    }

    @Override
    public String typeMessage() {
        return "PreLoginMessage";
    }

    @Override
    public long getUID() {
        return UID;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
