package it.polimi.ingsw.model;

import it.polimi.ingsw.Exception.SoldOutTilesException;
import it.polimi.ingsw.model.Tile.Tile;
import it.polimi.ingsw.model.Tile.TileDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileDeckTest {
    @Test
    void randomDrawAll() {
        TileDeck tileDeck= new TileDeck();
        try {
            for (int i=0;i<133;i++){
                Tile tile=tileDeck.randomDraw();
            }
        } catch (SoldOutTilesException e) {
            System.out.println("empty bag");
        }
    }
}