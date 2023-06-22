package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.Utils.Coordinates;

import java.io.Serializable;

public class Message implements Serializable {

    private final int gameID;
    public Message(int gameID) {
        this.gameID=gameID;
    }

    public int getMaxPlayers() {
        return -1;
    }
    public String getWinner() {
        return "";
    }
    public boolean isInit() {
        return false;
    }
    public int getColumn() {
        return -1;
    }
    public boolean isUsernameError() {
        return false;
    }
    public int getGameID() {
        return gameID;
    }
    public boolean isFirst() {
        return false;
    }

    public String getNickname() {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public long getUID() {
        return -1;
    }

    public boolean init(){
        return false;
    }

    public String typeMessage(){
        return null;
    }

    public Coordinates[] getPositions() {
        return null;
    }
    public String[] getColours() {
        return null;
    }

}
