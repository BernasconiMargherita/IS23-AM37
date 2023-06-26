package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.model.Tile.ColourTile;

import java.io.Serializable;

public class BoardResponse extends Message implements Serializable {
    private ColourTile[][] board;
    private String typeMessage ;
    private int[] commonTokens;
    boolean endGameToken;

    public BoardResponse(ColourTile[][] board,int gameID,long UID, int[] commonTokens, boolean endGameToken){
        super(gameID,UID);
        this.board = board;
        this.typeMessage = "BoardResponse";
        this.commonTokens = commonTokens;
        this.endGameToken = endGameToken;
    }

    @Override
    public String typeMessage() {
        return "BoardResponse";
    }

    public ColourTile[][] getBoard() {
        return board;
    }
}
