package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.Utils.Coordinates;

import java.io.Serializable;
import java.util.ArrayList;

public class RemoveMessage extends Message implements Serializable {

    private ArrayList<Coordinates> positions;
    private String nickname;
    private String typeMessage ;

    public RemoveMessage(ArrayList<Coordinates> positions, int gameID, long UID, String nickname) {
        super(gameID,UID);
        this.positions = positions;
        this.nickname = nickname;
        this.typeMessage = "RemoveMessage";
    }
    @Override
    public String getNickname() {
        return nickname;
    }

    public ArrayList<Coordinates> getPositions() {
        return positions;
    }

    public String typeMessage(){
        return "RemoveMessage";
    }
}
