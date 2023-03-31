package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void fullSlotOnBoard() {

        Library library = new Library();
        Tile[] tiles = new Tile[3];
        tiles[0] = new Tile(ColourTile.CATS);
        tiles[1] = new Tile(ColourTile.CATS);
        tiles[2] = new Tile(ColourTile.CATS);

        Tile[] tiles1 = new Tile[3];
        tiles1[0] = new Tile(ColourTile.TROPHIES);
        tiles1[1] = new Tile(ColourTile.TROPHIES);
        tiles1[2] = new Tile(ColourTile.CATS);


        Tile[] tiles2 = new Tile[1];


        try{
        for(int i=0; i<3; i++)
            library.addCardInColumn(0, tiles);
        for(int i=0; i<3; i++)
                library.addCardInColumn(0, tiles1);
        library.addCardInColumn(0, tiles2);

        }
         catch(NoSpaceInColumnException e){
            System.out.println("NoSpaceInCL....");
        }
         catch(FullColumnException e){
            System.out.println("Full");
         }





    }

}