package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileDeckTest {
    @Test
    void randomDrawAll() throws SoldOutTilesException {
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