package it.polimi.ingsw.Utils;

import java.io.Serializable;

/**
 * Class for a pair of Integer that represents the coordinates of a Tile in the library or on the board
 */
public class Coordinates implements Serializable {
    /**
     * Coordinate of the column
     */
    private Integer X;
    /**
     * Coordinate of the row
     */
    private Integer Y;

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

    public void setY(int Y){
        this.Y = Y;
    }

    public void setX(int X) {
        this.X = X;
    }
}
