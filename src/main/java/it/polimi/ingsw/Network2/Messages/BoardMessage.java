package it.polimi.ingsw.Network2.Messages;

public class BoardMessage extends Message{
    String nickname;
    int gameID;

    public BoardMessage(String nickname, int gameID) {
        this.nickname = nickname;
        this.gameID = gameID;
    }

    @Override
    public String typeMessage() {
        return "BoardMessage";
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public int getGameID() {
        return gameID;
    }
}
