package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class TurnMessage extends Message implements Serializable {
    private int column;
    private String nickname;
    private String[] colours;

    public TurnMessage(int gameID,long UID, int column, String nickname, String[] colours) {
        super(gameID,UID);
        this.column = column;
        this.nickname = nickname;
        this.colours = colours;
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
