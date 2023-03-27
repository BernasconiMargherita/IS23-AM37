package it.polimi.ingsw.model;

public class PersonalCardTile {
    /**
     * identify the position in the library
     */
    private final Coordinates coordinates;

    /**
     * identify the colour of the tile in a specific position
     */
    private final ColourTile colourTile;

    /**
     * constructor : assign the values to coordinates and colourTile
     */
    public PersonalCardTile(Coordinates coordinates, ColourTile colourTile) {
        this.coordinates = coordinates;
        this.colourTile = colourTile;
    }

    /**
     * getter of coordinates
     * @return coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * getter of colourTile
     * @return colourTile
     */

    public ColourTile getColourTile() {
        return colourTile;
    }
}