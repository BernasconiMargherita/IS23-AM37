package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class TurnMessage extends Message implements Serializable {
    private int gameID;
    private int column;
    private String nickname;
    private String[] colours;

    public TurnMessage(int gameID, int column, String nickname, String[] colours) {
        super(gameID);
        this.column = column;
        this.nickname = nickname;
        this.colours = colours;
    }

    public int getGameID() {
        return gameID;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public String[] getColours() {
        return colours;
    }
}
