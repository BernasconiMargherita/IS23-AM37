package it.polimi.ingsw.model.PersonalCards;

import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.Tile.ColourTile;

import java.util.Objects;

/**
 * @param coordinates identify the position in the library
 * @param colourTile  identify the colour of the tile in a specific position
 */
public record PersonalCardTile(Coordinates coordinates, ColourTile colourTile) {
    @Override
    public Coordinates coordinates() {
        return coordinates;
    }

    @Override
    public ColourTile colourTile() {
        return colourTile;
    }
}