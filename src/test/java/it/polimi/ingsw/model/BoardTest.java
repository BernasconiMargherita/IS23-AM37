package it.polimi.ingsw.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */

public class BoardTest {
    @Test
    public void twoPlayersCorrectTilesDisposition() {
        Board board = new Board(2);
        for(int i=0; i<8; i++ ){
           assertTrue(board.getBoard()[0][i].isFree());
        }
    }

}
