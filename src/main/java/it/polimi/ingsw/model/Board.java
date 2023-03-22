package it.polimi.ingsw.model;

/**
 * Class that represents the gaming board
 */
public class Board {
    /**
     * A TileSlot Matrix, big enough for all the number of players, with extra space to make it a square for simplify operation like refilling
     */
    private TileSlot[][] board;
    /**
     * A Deck of Common Objective Cards, used for randomly select two among the 12 possibilities
     */
    private CommonDeck commonDeck;
    /**
     * The token that begins the final turn
     */
    private EndGameToken endGameToken;
    /**
     * The bag that contains all the tiles of the game, used by the board to initialize and refill itself
     */
    private TileDeck bag;
    /**
     * this three boolean arrays are masks, used by the initializer and the refill method to, based on the number of players, know which tile slot has to be checked to fill or refill
     */
    private static final boolean[][] twoPlayersTiles =
            {{false, false, false, false, false, false, false, false, false},
                    {false, false, false, true, true, false, false, false, false},
                    {false, false, false, true, true, true, false, false, false},
                    {false, false, true, true, true, true, true, true, false},
                    {false, true, true, true, true, true, true, true, false},
                    {false, true, true, true, true, true, true, false, false},
                    {false, false, false, true, true, true, false, false, false},
                    {false, false, false, false, true, true, false, false, false},
                    {false, false, false, false, false, false, false, false, false}};

    private static final boolean[][] threePlayersTiles =
            {{false, false, false, true, false, false, false, false, false},
                    {false, false, false, true, true, false, false, false, false},
                    {false, false, true, true, true, true, true, false, false},
                    {false, false, true, true, true, true, true, true, true},
                    {false, true, true, true, true, true, true, true, false},
                    {true, true, true, true, true, true, true, false, false},
                    {false, false, true, true, true, true, true, false, false},
                    {false, false, false, false, true, true, false, false, false},
                    {false, false, false, false, false, true, false, false, false}};

    private static final boolean[][] fourPlayersTiles =
            {{false, false, false, true, true, false, false, false, false},
                    {false, false, false, true, true, true, false, false, false},
                    {false, false, true, true, true, true, true, false, false},
                    {false, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, false},
                    {false, false, true, true, true, true, true, false, false},
                    {false, false, false, true, true, true, false, false, false},
                    {false, false, false, false, true, true, false, false, false}};

    /**
     * the constructor of this class, that uses the boolean masks to fill the "true" marked spots
     * @param numOfPlayers number of players at the start of the game,used for switch cases
     */
    Board(int numOfPlayers) {
        this.bag = new TileDeck();
        this.commonDeck = new CommonDeck(numOfPlayers);
        this.board = new TileSlot[9][9];

        if (numOfPlayers == 2) {
            for (int j = 0; j <= 8; j++) {
                for (int k = 0; k <= 8; k++) {
                    if (twoPlayersTiles[j][k]) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }
        if (numOfPlayers == 3) {
            for (int j = 0; j <= 8; j++) {
                for (int k = 0; k <= 8; k++) {
                    if (threePlayersTiles[j][k]) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }
        if (numOfPlayers == 4) {
            for (int j = 0; j <= 8; j++) {
                for (int k = 0; k <= 8; k++) {
                    if (fourPlayersTiles[j][k]) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }

    }

    /**
     * method for refilling the board if necessary,leaving the already filled TileSlots untouched
     */
    public void refillBoard(int numOfPlayers) {

        if (numOfPlayers == 2) {
            for (int j = 0; j <= 8; j++) {
                for (int k = 0; k <= 8; k++) {
                    if ((twoPlayersTiles[j][k]) && (board[j][k].isFree())) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }
        if (numOfPlayers == 3) {
            for (int j = 0; j <= 8; j++) {
                for (int k = 0; k <= 8; k++) {
                    if ((threePlayersTiles[j][k]) && (board[j][k].isFree())) board[j][k].assignTile(bag.randomDraw());
                }
            }
        }
        if (numOfPlayers == 4) {
            for (int j = 0; j <= 8; j++) {
                for (int k = 0; k <= 8; k++) {
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

    public Tile[] removeCardFromBoard(Coordinates[] positions) throws EmptySlotException,InvalidSlotException {
        Tile[] selectedTile = new Tile[positions.length];
        for (int i = 0; i < positions.length; i++) {
            Coordinates position = positions[i];
            if (board[position.getX()][position.getY()].isFree()) throw new EmptySlotException();

            if ((board[(position.getX())+1][position.getY()].isFree())||(board[(position.getX())-1][position.getY()].isFree())||(board[(position.getX())][position.getY()+1].isFree())||(board[(position.getX())][position.getY()-1].isFree())) {
                selectedTile[i]=board[position.getX()][position.getY()].getAssignedTile();
            }
            else throw new InvalidSlotException();
            
        }

        for (Coordinates position : positions) {
            board[position.getX()][position.getY()].removeAssignedTile();
        }

            return selectedTile;
    }



}
