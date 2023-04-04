package it.polimi.ingsw.Utils;

import it.polimi.ingsw.model.Tile.ColourTile;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void checkPersonalTarget() {
    }

    @Test
    void checkCommonTarget() {
    }

    @Test
    int checkAllDifferent(TileSlot[] libraryMatrix, String type) {

        TileSlot[] libraryMatrix = new TileSlot[];
        assertEquals(5, checkAllDifferent(libraryMatrix, type));


        if (type.equals("ROW")) {
            libraryMatrix[0] = new TileSlot(ColourTile.CATS);
            libraryMatrix[1] = new TileSlot(ColourTile.BOOKS);
            libraryMatrix[2] = new TileSlot(ColourTile.GAMES);
            libraryMatrix[3] = new TileSlot(ColourTile.FRAMES);
            libraryMatrix[4] = new TileSlot(ColourTile.TROPHIES);
            libraryMatrix[4] = new TileSlot(ColourTile.TROPHIES);



        }

        if (type.equals("COLUMN")){
            libraryMatrix[0] = new TileSlot(ColourTile.CATS);
            libraryMatrix[1] = new TileSlot(ColourTile.BOOKS);
            libraryMatrix[2] = new TileSlot(ColourTile.GAMES);
            libraryMatrix[3] = new TileSlot(ColourTile.FRAMES);
            libraryMatrix[4] = new TileSlot(ColourTile.TROPHIES);



        }

    }

    @Test
    void checkDiagonal() {
    }

    @Test
    void checkGroupsOfFour() {
    }

    @Test
    void checkGroupsOfTwo() {
    }
}