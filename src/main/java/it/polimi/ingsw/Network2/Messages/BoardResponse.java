package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.model.Tile.ColourTile;

public class BoardResponse extends Message{
    public static final int MAX_BOARD_ROWS = 9;
    public static final int MAX_BOARD_COLUMNS = 9;

    private ColourTile[][] board;
    public BoardResponse(){
        board
    }

    @Override
    public String typeMessage() {
        return "BoardResponse";
    }
}
