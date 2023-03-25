package it.polimi.ingsw.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */

public class BoardTest {
    @Test
    public void twoPlayersCorrectTilesDisposition() throws SoldOutTilesException {
        boolean[][] twoPlayersTiles =
                {{false, false, false, false, false, false, false, false, false},
                        {false, false, false, true, true, false, false, false, false},
                        {false, false, false, true, true, true, false, false, false},
                        {false, false, true, true, true, true, true, true, false},
                        {false, true, true, true, true, true, true, true, false},
                        {false, true, true, true, true, true, true, false, false},
                        {false, false, false, true, true, true, false, false, false},
                        {false, false, false, false, true, true, false, false, false},
                        {false, false, false, false, false, false, false, false, false}};
        Board board = new Board(2);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (twoPlayersTiles[i][j]) assertFalse(board.getBoard()[i][j].isFree());
                else assertTrue(board.getBoard()[i][j].isFree());
            }
        }
    }
    @Test
    public void threePlayersCorrectTilesDisposition() throws SoldOutTilesException {
        boolean[][] threePlayersTiles =
                {{false, false, false, true, false, false, false, false, false},
                        {false, false, false, true, true, false, false, false, false},
                        {false, false, true, true, true, true, true, false, false},
                        {false, false, true, true, true, true, true, true, true},
                        {false, true, true, true, true, true, true, true, false},
                        {true, true, true, true, true, true, true, false, false},
                        {false, false, true, true, true, true, true, false, false},
                        {false, false, false, false, true, true, false, false, false},
                        {false, false, false, false, false, true, false, false, false}};

        Board board = new Board(3);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (threePlayersTiles[i][j]) assertFalse(board.getBoard()[i][j].isFree());
                else assertTrue(board.getBoard()[i][j].isFree());
            }
        }
    }
    @Test
    public void fourPlayersCorrectTilesDisposition() throws SoldOutTilesException {
        boolean[][] fourPlayersTiles =
                {{false, false, false, true, true, false, false, false, false},
                        {false, false, false, true, true, true, false, false, false},
                        {false, false, true, true, true, true, true, false, false},
                        {false, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, false},
                        {false, false, true, true, true, true, true, false, false},
                        {false, false, false, true, true, true, false, false, false},
                        {false, false, false, false, true, true, false, false, false}};
        Board board = new Board(4);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (fourPlayersTiles[i][j]) assertFalse(board.getBoard()[i][j].isFree());
                else assertTrue(board.getBoard()[i][j].isFree());
            }
        }
    }
}
