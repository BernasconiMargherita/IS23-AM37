package it.polimi.ingsw.model;

import java.util.Objects;

/**
 * @param coordinates identify the position in the library
 * @param colourTile  identify the colour of the tile in a specific position
 */
public record PersonalCardTile(Coordinates coordinates, ColourTile colourTile) {


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PersonalCardTile) obj;
        return Objects.equals(this.coordinates, that.coordinates) &&
                Objects.equals(this.colourTile, that.colourTile);
    }

    @Override
    public String toString() {
        return "PersonalCardTile[" +
                "coordinates=" + coordinates + ", " +
                "colourTile=" + colourTile + ']';
    }

}