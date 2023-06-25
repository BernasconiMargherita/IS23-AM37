package it.polimi.ingsw.Network2.Messages;

import com.google.gson.Gson;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    private final Long UID;
    private final int gameID;
    public Message(int gameID,Long UID) {
        this.gameID=gameID;
        this.UID=UID;
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
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
        return UID;
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
    public CardPersonalTarget getCardPersonalTarget() {
        return null;
    }

    public ArrayList<CardCommonTarget> getCommonTargets() {
        return null;
    }

}
