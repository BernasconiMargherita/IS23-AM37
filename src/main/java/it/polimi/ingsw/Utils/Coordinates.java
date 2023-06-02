package it.polimi.ingsw.Utils;

import java.io.Serializable;

/**
 * Class for a pair of Integer that represents the coordinates of a Tile in the library or on the board
 */
public class Coordinates implements Serializable {
    /**
     * Coordinate of the row
     */
    private Integer Row;
    /**
     * Coordinate of the column
     */
    private Integer Column;

    /**
     * constructor to set the coordinates row and column
     */
    public Coordinates(Integer Row, Integer Column) {
        this.Row = Row;
        this.Column = Column;
    }

    public Integer getRow() {
        return Row;
    }

    public void setRow(int row) {
        this.Row = row;
    }

    public Integer getColumn() {
        return Column;
    }

    public void setColumn(int column) {
        this.Column = column;
    }
}
