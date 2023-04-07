package it.polimi.ingsw.model.Board;

public class BoardMask {
    private final boolean[][] twoPlayersTiles;
    private final boolean[][] threePlayersTiles;
    private final boolean[][] fourPlayersTiles;

    public BoardMask(boolean[][] twoPlayersTiles, boolean[][] threePlayersTiles, boolean[][] fourPlayersTiles) {
        this.twoPlayersTiles = twoPlayersTiles;
        this.threePlayersTiles = threePlayersTiles;
        this.fourPlayersTiles = fourPlayersTiles;
    }

    public boolean[][] getTiles(int numOfPlayers) {

        if(numOfPlayers == 2){
            return twoPlayersTiles;
        }
        if(numOfPlayers == 3){
            return  threePlayersTiles;
        }

        return fourPlayersTiles;

    }

}
