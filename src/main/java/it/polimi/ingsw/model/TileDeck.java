package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Random;

public class TileDeck {
    /**
     * int tilesLeft : used for the extraction in RandomDraw
     */
    private int tilesLeft = 133;

    /**
     * tileDeck : ArrayList of tiles
     */
    private ArrayList<Tile> tileDeck;

    /**
     * costructor of TileDeck that adds 132 Tiles in tileDeck
     */
    public TileDeck(){
        /**
         * adding 132 cards in tileDeck
         */


        tileDeck = new ArrayList<>();

        /**
         * adding 22 ''CATS''
         */
        for(int i = 0; i < 22; i++) tileDeck.add(new Tile(ColourTile.CATS));
        /**
         * adding 22 ''BOOKS''
         */
        for(int i = 0; i < 22; i++) tileDeck.add(new Tile(ColourTile.BOOKS));
        /**
         * adding 22 ''GAMES''
         */
        for(int i = 0; i < 22; i++) tileDeck.add(new Tile(ColourTile.GAMES));
        /**
         * adding 22 ''FRAMES''
         */
        for(int i = 0; i < 22; i++) tileDeck.add(new Tile(ColourTile.FRAMES));
        /**
         * adding 22 ''TROPHIES''
         */
        for(int i = 0; i < 22; i++) tileDeck.add(new Tile(ColourTile.TROPHIES));
        /**
         * adding 22 ''PLANTS''
         */
        for(int i = 0; i < 22; i++) tileDeck.add(new Tile(ColourTile.PLANTS));
    }




    /**
     * random draw from TileDeck without re-entering i.e. removed from tileDeck
     * @return tileDeck.remove(...) : return a tile thet it's removed from the tileDeck
     */

    public Tile randomDraw(){
        tilesLeft = tilesLeft - 1;
        Random random = new Random();
        int number = random.nextInt(tilesLeft);
        return tileDeck.remove(number);
    }


    /**
     * getter of the tileDeck
     * @return tileDeck
     */
    public ArrayList<Tile> getTileDeck() {
        return tileDeck;
    }
}
