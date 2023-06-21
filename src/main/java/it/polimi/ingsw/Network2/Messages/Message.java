package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.Utils.Coordinates;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;

    public Message() {

    }
    public int getMaxPlayers() {
        return -1;
    }
    public String getWinner() {
        return "";
    }
    public boolean isUsernameError() {
        return false;
    }

    public int getGameID() {
        return -1;
    }

    public boolean isFirst() {
        return false;
    }

    public String getNickname() {
        return "";
    }

    public String getProtocol() {
        return "";
    }

    public long getUID() {
        return -1;
    }

    public String getMessage() {
        return message;
    }
    public boolean init(){
        return false;
    }

    public String typeMessage(){
        return "";
    }

    public Coordinates[] getPositions() {
        return null;
    }

}
