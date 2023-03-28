package it.polimi.ingsw.model;

import java.util.Objects;

/**
 * Class that represents the gaming board
 */
public class Board {
    /**
     * A TileSlot Matrix, big enough for all the number of players, with extra space to make it a square for simplify operation like refilling
     */
    private final TileSlot[][] board;

    private final int numOfPlayers;
    /**
     * The token that begins the final turn
     */
    private EndGameToken endGameToken;
    /**
     * The bag that contains all the tiles of the game, used by the board to initialize and refill itself
     */
    private final TileDeck bag;
    /**
     * this three boolean arrays are masks, used by the initializer and the refill method to, based on the number of players, know which tile slot has to be checked to fill or refill
     */
    private static final boolean[][] twoPlayersTiles =
            {       {false,false,false, false, false, false, false, false, false, false, false},
                    {false,false, false, false, false, false, false, false, false, false,false},
                    {false,false, false, false, true, true, false, false, false, false,false},
                    {false,false, false, false, true, true, true, false, false, false,false},
                    {false,false, false, true, true, true, true, true, true, false,false},
                    {false,false, true, true, true, true, true, true, true, false,false},
                    {false,false, true, true, true, true, true, true, false, false,false},
                    {false,false, false, false, true, true, true, false, false, false,false},
                    {false,false, false, false, false, true, true, false, false, false,false},
                    {false,false, false, false, false, false, false, false, false, false,false},
                    {false,false, false, false, false, false, false, false, false, false,false},
            };

    private static final boolean[][] threePlayersTiles =
            {       {false,false,false, false, false, false, false, false, false, false, false},
                    {false,false, false, false, true, false, false, false, false, false,false},
                    {false,false, false, false, true, true, false, false, false, false,false},
                    {false,false, false, true, true, true, true, true, false, false,false},
                    {false,false, false, true, true, true, true, true, true, true,false},
                    {false,false, true, true, true, true, true, true, true, false,false},
                    {false,true, true, true, true, true, true, true, false, false,false},
                    {false,false, false, true, true, true, true, true, false, false,false},
                    {false,false, false, false, false, true, true, false, false, false,false},
                    {false,false, false, false, false, false, true, false, false, false,false},
                    {false,false,false, false, false, false, false, false, false, false, false}
            };

    private static final boolean[][] fourPlayersTiles =
            {       {false,false,false, false, false, false, false, false, false, false, false},
                    {false,false, false, false, true, true, false, false, false, false,false},
                    {false,false, false, false, true, true, true, false, false, false,false},
                    {false,false, false, true, true, true, true, true, false, false,false},
                    {false,false, true, true, true, true, true, true, true, true,false},
                    {false,true, true, true, true, true, true, true, true, true,false},
                    {false,true, true, true, true, true, true, true, true, false,false},
                    {false,false, false, true, true, true, true, true, false, false,false},
                    {false,false, false, false, true, true, true, false, false, false,false},
                    {false,false, false, false, false, true, true, false, false, false,false},
                    {false,false,false, false, false, false, false, false, false, false, false}};

    /**
     * the constructor of this class, that uses the boolean masks to fill the "true" marked spots
     * @param numOfPlayers number of players at the start of the game,used for switch cases
     */
    Board(int numOfPlayers) throws SoldOutTilesException {
        this.bag = new TileDeck();
        this.numOfPlayers=numOfPlayers;
        CommonDeck commonDeck = new CommonDeck(numOfPlayers);
        this.board = new TileSlot[11][11];

        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                board[i][j]= new TileSlot();
            }
        }

        if (numOfPlayers == 2) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    if (twoPlayersTiles[j][k]) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }
        if (numOfPlayers == 3) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    if (threePlayersTiles[j][k]) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }
        if (numOfPlayers == 4) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    if (fourPlayersTiles[j][k]) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }

    }

    /**
     * method for refilling the board if necessary,leaving the already filled TileSlots untouched
     */
    public void refillBoard() throws SoldOutTilesException {

        if (numOfPlayers == 2) {
            for (int j = 0; j <11; j++) {
                for (int k = 0; k <11; k++) {
                    if ((twoPlayersTiles[j][k]) && (board[j][k].isFree())) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }
        if (numOfPlayers == 3) {
            for (int j = 0; j <11; j++) {
                for (int k = 0; k <11; k++) {
                    if ((threePlayersTiles[j][k]) && (board[j][k].isFree())) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }
        if (numOfPlayers == 4) {
            for (int j = 0; j <11; j++) {
                for (int k = 0; k <11; k++) {
                    if ((fourPlayersTiles[j][k]) && (board[j][k].isFree())) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }
    }

    /**
     * Method for removing from the board the player's selected tiles,first controlling if they are valid
     * @param positions an array of Coordinates,an Integer Pair with the coordinates of the selected tiles
     * @return Selected tile is an array of Tiles, later used by the Player to fill his Library
     * @throws EmptySlotException exception for managing the selection of a free space on the board
     * @throws InvalidSlotException exception for managing the selection of a Tile with no free spaces around
     */

    public Tile[] removeCardFromBoard(Coordinates[] positions) throws EmptySlotException,InvalidSlotException, InvalidPositionsException {

        for(int i = 1 ; i< positions.length ; i++ ){
                if(!Objects.equals(positions[i - 1].getX(), positions[i].getX()) && !Objects.equals(positions[i - 1].getY(), positions[i].getY())) {
                    throw new InvalidPositionsException();
                }
        }
        
        
        Tile[] selectedTile = new Tile[positions.length];
        for (int i = 0; i < positions.length; i++) {
            Coordinates position = positions[i];
            
            if (board[position.getX()][position.getY()].isFree()) throw new EmptySlotException();
            if (((numOfPlayers==2)&&(!twoPlayersTiles[position.getX()][position.getY()]))||((numOfPlayers==3)&&(!threePlayersTiles[position.getX()][position.getY()]))||((numOfPlayers==4)&&(!fourPlayersTiles[position.getX()][position.getY()])))
                throw new InvalidSlotException();
            
            if ((board[(position.getX()) + 1][position.getY()].isFree()) || (board[(position.getX()) - 1][position.getY()].isFree()) || (board[(position.getX())][position.getY() + 1].isFree()) || (board[(position.getX())][position.getY() - 1].isFree())) {
                    selectedTile[i] = board[position.getX()][position.getY()].getAssignedTile();
                } else throw new InvalidSlotException();
            }
        for (Coordinates position : positions) {
            board[position.getX()][position.getY()].removeAssignedTile();
        }
            return selectedTile;
    }


    public TileSlot[][] getBoard() {
        return this.board;
    }
}
