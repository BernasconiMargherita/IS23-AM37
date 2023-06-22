package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.Utils.Coordinates;

public class RemoveMessage extends Message{

    private Coordinates[] positions;
    private int gameID;
    private String nickname;

    public RemoveMessage(Coordinates[] positions, int gameID, String nickname) {
        super(gameID);
        this.positions = positions;
        this.nickname = nickname;
    }

    @Override
    public int getGameID() {
        return gameID;
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
