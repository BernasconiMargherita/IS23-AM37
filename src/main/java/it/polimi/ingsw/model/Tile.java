package it.polimi.ingsw.model;

public class Tile extends Card{
    /**
     * ColourTile colour : type of the tile (CATS OR BOOKS OR GAMES OR FRAMES OR TROPHIES OR PLANTS)
     */
    private ColourTile colour;

    /**
     * costructor of Tile : assigning the value of colour
     */
    public Tile(ColourTile colour){
        this.colour = colour;
    }

    /**
     * getter of colour
     * @return colour
     */
    public ColourTile getColour() {
        return colour;
    }
}
