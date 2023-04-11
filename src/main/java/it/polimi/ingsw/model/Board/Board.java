package it.polimi.ingsw.model.Board;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.Tile.*;
import it.polimi.ingsw.Utils.TileSlot;
import it.polimi.ingsw.Exception.*;

import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * Class that represents the gaming board
 */
public class Board {
    public static final int MAX_BOARD_ROWS = 11;
    public static final int MAX_BOARD_COLUMNS = 11;
    /**
     * A TileSlot Matrix, big enough for all the number of players, with extra space to make it a square for simplify operation like refilling
     */
    private final TileSlot[][] board;

    /**
     * The token that begins the final turn
     */
    private EndGameToken endGameToken;
    /**
     * The bag that contains all the tiles of the game, used by the board to initialize and refill itself
     */
    private final TileDeck bag;

    private final boolean[][] boardMask;




    /**
     * the constructor of this class, that uses the boolean masks to fill the "true" marked spots
     * @param numOfPlayers number of players at the start of the game,used for switch cases
     */
    public Board(int numOfPlayers) {

        this.bag = new TileDeck();
        this.board = new TileSlot[MAX_BOARD_ROWS][MAX_BOARD_COLUMNS];

        BoardMaskParser boardMaskParser;
        try{
            boardMaskParser = new BoardMaskParser();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.boardMask = boardMaskParser.getBoardMask().getTiles(numOfPlayers);

        for (int i=0;i<MAX_BOARD_ROWS;i++){
            for (int j=0;j<MAX_BOARD_COLUMNS;j++){
                board[i][j]= new TileSlot();
            }
        }
            for (int j = 0; j < MAX_BOARD_ROWS; j++) {
                for (int k = 0; k < MAX_BOARD_COLUMNS; k++) {
                    if (boardMask[j][k]) {
                        try {
                            board[j][k].assignTile(bag.randomDraw());
                        } catch (SoldOutTilesException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    }

    /**
     * method for refilling the board if necessary,leaving the already filled TileSlots untouched
     */
    public void refillBoard() throws SoldOutTilesException {
            for (int j = 0; j < MAX_BOARD_ROWS; j++) {
                for (int k = 0; k < MAX_BOARD_COLUMNS; k++) {
                    if ((boardMask[j][k]) && (board[j][k].isFree())) board[j][k].assignTile(bag.randomDraw());
                }
            }
    }

    /**
     * Method for removing from the board the player's selected tiles,first controlling if they are valid
     * @param positions an array of Coordinates,an Integer Pair with the coordinates of the selected tiles
     * @return Selected tile is an array of Tiles, later used by the Player to fill his Shelf
     * @throws EmptySlotException exception for managing the selection of a free space on the board
     * @throws InvalidSlotException exception for managing the selection of a Tile with no free spaces around
     */

    public Tile[] removeCardFromBoard(Coordinates[] positions) throws EmptySlotException, InvalidSlotException, InvalidPositionsException {

        for(int i = 1 ; i< positions.length ; i++ ){
                if(!Objects.equals(positions[i - 1].getX(), positions[i].getX()) && !Objects.equals(positions[i - 1].getY(), positions[i].getY())) {
                    throw new InvalidPositionsException("The selected positions are invalid");
                }
        }
        
        
        Tile[] selectedTile = new Tile[positions.length];
        for (int i = 0; i < positions.length; i++) {
            Coordinates position = positions[i];
            
            if (board[position.getX()][position.getY()].isFree()) throw new EmptySlotException("This slot is Empty");
            if (!boardMask[position.getX()][position.getY()]) throw new InvalidSlotException("This slot is invalid");

            if ((board[(position.getX()) + 1][position.getY()].isFree()) || (board[(position.getX()) - 1][position.getY()].isFree()) || (board[(position.getX())][position.getY() + 1].isFree()) || (board[(position.getX())][position.getY() - 1].isFree())) {
                    selectedTile[i] = board[position.getX()][position.getY()].getAssignedTile();
                } else throw new InvalidSlotException("this slot is invalid");
            }
        for (Coordinates position : positions) {
            board[position.getX()][position.getY()].removeAssignedTile();
        }
            return selectedTile;
    }

    /**
     * method to see if on the board are only tiles with free spaces near them
     */
    public boolean refillIsNecessary(){
        for (int i=0;i<MAX_BOARD_ROWS;i++){
            for (int j=0;j<MAX_BOARD_COLUMNS;j++){
                if ((boardMask[i][j])&&((!(board[i+1][j].isFree()))||(!(board[i-1][j].isFree()))||(!(board[i][j+1].isFree()))||(!(board[i][j-1].isFree())))) return false;
            }
        }
        return true;
    }



    public TileSlot[][] getBoard() {
        return this.board;
    }
}
