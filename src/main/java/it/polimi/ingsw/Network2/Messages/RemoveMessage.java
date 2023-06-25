package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.Utils.Coordinates;

import java.io.Serializable;

public class RemoveMessage extends Message implements Serializable {

    private Coordinates[] positions;
    private String nickname;

    public RemoveMessage(Coordinates[] positions,int gameID,long UID, String nickname) {
        super(gameID,UID);
        this.positions = positions;
        this.nickname = nickname;
    }
    @Override
    public String getNickname() {
        return nickname;
    }

    public Coordinates[] getPositions() {
        return positions;
    }

    public String typeMessage(){
        return "RemoveMessage";
    }
}
