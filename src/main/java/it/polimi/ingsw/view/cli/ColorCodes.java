package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Tile.ColourTile;

public class ColorCodes {
    private ColorCodes() {

    }

    private static final String TEXT_BLACK = "\u001B[30m";

    private static final String TEXT_PURPLE = "\u001B[35m";
    ;
    private static final String TEXT_GREEN = "\u001B[32m";
    private static final String TEXT_YELLOW = "\u001B[33m";
    private static final String TEXT_BLUE = "\u001B[34m";
    private static final String TEXT_LIGHTBLUE = "\u001B[36m";
    private static final String TEXT_WHITE = "\u001B[37m";
    private String color;


    static String getColorCode(ColourTile color) {

        switch (color) {
            case GAMES:
                return TEXT_YELLOW;
            case CATS:
                return TEXT_GREEN;
            case PLANTS:
                return TEXT_PURPLE;
            case FRAMES:
                return TEXT_BLUE;
            case TROPHIES:
                return TEXT_LIGHTBLUE;
            case BOOKS:
                return TEXT_WHITE;
            case FREE:
                return TEXT_BLACK;
        }

        return null;
    }
}

