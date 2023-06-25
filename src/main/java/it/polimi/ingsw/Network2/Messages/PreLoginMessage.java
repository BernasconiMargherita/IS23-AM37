package it.polimi.ingsw.Network2.Messages;

public class PreLoginMessage extends Message{

    String nickname;
    public PreLoginMessage(int gameID, long UID, String nickname) {
        super(gameID,UID);
        this.nickname = nickname;
    }

    @Override
    public String typeMessage() {
        return "PreLoginMessage";
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
