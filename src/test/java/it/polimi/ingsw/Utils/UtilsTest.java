package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Exception.SoldOutTilesException;
import it.polimi.ingsw.model.Tile.ColourTile;
import it.polimi.ingsw.model.Tile.Tile;

import it.polimi.ingsw.model.Tile.TileDeck;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
    void checkDiagonal() throws SoldOutTilesException {
        Utils utils = new Utils();
        TileDeck bag = new TileDeck();
        TileSlot[][] libraryMatrix = new TileSlot[6][5];

        Coordinates coordinates= new Coordinates(0,0);
        int h=1;
        int k=1;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                libraryMatrix[i][j] = new TileSlot();
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j =0; j < 5; j++) {
                libraryMatrix[i][j].assignTile(bag.randomDraw());
            }
        }
        int i = coordinates.getX();
        for (int j = coordinates.getY(); j < 5; j+=h) {
            while(i<5){
            libraryMatrix[i][j].assignTile(new Tile(ColourTile.BOOKS));
                i+=k;

            }

        }
        assertTrue(utils.checkDiagonal(libraryMatrix, new Coordinates(0, 0), 1, 1));
    }


    @Test
    void checkGroupsOfFour() {
    }

    @Test
    void checkGroupsOfTwo() {
    }
}