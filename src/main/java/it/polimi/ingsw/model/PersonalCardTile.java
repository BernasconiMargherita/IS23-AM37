package it.polimi.ingsw.model;

/**
 * @param coordinates identify the position in the library
 * @param colourTile  identify the colour of the tile in a specific position
 */
public record PersonalCardTile(Coordinates coordinates, ColourTile colourTile){
}