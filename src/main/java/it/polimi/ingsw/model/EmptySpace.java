package it.polimi.ingsw.model;

/**
 * class that creates an object "empty Tile" used for not assign a null value to an empty slot
 */
public class EmptySpace extends Tile {
    /**
     * constructor of Tile : assigning the value of colour, in this case empty
     */
    public EmptySpace() {
        super(ColourTile.EMPTY);
    }
}
