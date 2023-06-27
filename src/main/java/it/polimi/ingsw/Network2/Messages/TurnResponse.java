package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.model.Tile.ColourTile;

import java.io.Serializable;

public class TurnResponse extends Message implements Serializable {

    private int status;
    private String typeMessage ;
    ColourTile[][] shelfColours ;
    int column;

    public TurnResponse(int status,int gameID,long UID,ColourTile[][] shelfColours, int column) {
        super(gameID,UID);
        this.status = status;
        this.typeMessage = "TurnResponse";
        this.shelfColours = shelfColours;
        this.column = column;
    }

    public String typeMessage(){
        return "TurnResponse";
    }

    @Override
    public int getColumn() {
        return column;
    }

    public int getStatus() {
        return status;
    }

    public ColourTile[][] getShelf() {
        return shelfColours;
    }
}
