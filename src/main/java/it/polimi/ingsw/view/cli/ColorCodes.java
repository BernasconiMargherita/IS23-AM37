package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Tile.ColourTile;

/**
 * The ColorCodes class provides static methods to retrieve ANSI color codes for different colors.
 * The color codes are used to format the output with colors in supported terminals.
 */
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


    /**
     * Returns the ANSI color code associated with the specified color.
     *
     * @param color The color for which to retrieve the ANSI color code.
     * @return The ANSI color code as a string, or null if the color is not recognized.
     */
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

