package it.polimi.ingsw.Network2.Messages;

public class turnMessage extends Message {
    private int gameID;
    private int column;
    private String nickname;

    public turnMessage(int gameID, int column, String nickname) {
        this.gameID = gameID;
        this.column = column;
        this.nickname = nickname;
    }

    public int getGameID() {
        return gameID;
    }

    public int getColumn() {
        return column;
    }
}
