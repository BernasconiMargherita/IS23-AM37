package it.polimi.ingsw.Utils;

import it.polimi.ingsw.model.Tile.ColourTile;
import it.polimi.ingsw.model.Tile.Tile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class UtilsTest {

    @Test
    void checkPersonalTarget() {
    }

    @Test
    void checkCommonTarget() {
    }

    @Test
    void checkAllDifferent() {
        Utils utils=new Utils();
        TileSlot[] libraryMatrix = new TileSlot[5];
            for(int i=0; i<5;i++){
                libraryMatrix[i] = new TileSlot();
            }


        libraryMatrix[0].assignTile(new Tile(ColourTile.CATS));
        libraryMatrix[1].assignTile(new Tile(ColourTile.TROPHIES));
        libraryMatrix[2].assignTile(new Tile(ColourTile.PLANTS));
        libraryMatrix[3].assignTile(new Tile(ColourTile.BOOKS));
        libraryMatrix[4].assignTile(new Tile(ColourTile.BOOKS));

        assertEquals(4,utils.checkAllDifferent(libraryMatrix,"ROW"));



        TileSlot[] libraryMatrix1 = new TileSlot[6];

        for(int i=0; i<6;i++){
            libraryMatrix1[i] = new TileSlot();
        }


        libraryMatrix1[0].assignTile(new Tile(ColourTile.CATS));
        libraryMatrix1[1].assignTile(new Tile(ColourTile.TROPHIES));
        libraryMatrix1[2].assignTile(new Tile(ColourTile.PLANTS));
        libraryMatrix1[3].assignTile(new Tile(ColourTile.BOOKS));
        libraryMatrix1[4].assignTile(new Tile(ColourTile.BOOKS));
        libraryMatrix1[5].assignTile(new Tile(ColourTile.BOOKS));

        assertEquals(4,utils.checkAllDifferent(libraryMatrix1,"COLUMN"));

    }

    @Test
    void checkDiagonal() {
        Utils utils=new Utils();
        TileSlot[][] libraryMatrix = new TileSlot[6][5];

        for(int i= 0; i< 6; i++){
            for

    }

    @Test
    void checkGroupsOfFour() {
    }

    @Test
    void checkGroupsOfTwo() {
    }
}