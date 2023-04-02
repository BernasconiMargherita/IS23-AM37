package it.polimi.ingsw.Utils;

/**
 * Class for a pair of Integer that represents the coordinates of a Tile in the library or on the board
 */
public class Coordinates {
    /**
     * Coordinate of the column
     */
    private final Integer X;
    /**
     * Coordinate of the row
     */
    private final Integer Y;

    /**
     * constructor to set the coordinates x and y
     */
    public Coordinates(Integer X, Integer Y) {
        this.X = X;
        this.Y = Y;
    }

    public Integer getX() {
        return X;
    }

    public Integer getY() {
        return Y;
    }
}
