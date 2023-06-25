package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.model.Tile.ColourTile;

import java.io.Serializable;

public class BoardResponse extends Message implements Serializable {
    private ColourTile[][] board;

    public BoardResponse(ColourTile[][] board,int gameID,long UID){
        super(gameID,UID);
        this.board = board;
    }

    @Override
    public String typeMessage() {
        return "BoardResponse";
    }

    public ColourTile[][] getBoard() {
        return board;
    }
}
