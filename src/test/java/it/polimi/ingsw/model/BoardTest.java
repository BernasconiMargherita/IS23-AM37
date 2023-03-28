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
                {       {false,false,false, false, false, false, false, false, false, false, false},
                        {false,false, false, false, false, false, false, false, false, false,false},
                        {false,false, false, false, true, true, false, false, false, false,false},
                        {false,false, false, false, true, true, true, false, false, false,false},
                        {false,false, false, true, true, true, true, true, true, false,false},
                        {false,false, true, true, true, true, true, true, true, false,false},
                        {false,false, true, true, true, true, true, true, false, false,false},
                        {false,false, false, false, true, true, true, false, false, false,false},
                        {false,false, false, false, false, true, true, false, false, false,false},
                        {false,false, false, false, false, false, false, false, false, false,false},
                        {false,false, false, false, false, false, false, false, false, false,false},
                };
        Board board = new Board(2);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <11; j++) {
                if (twoPlayersTiles[i][j]) assertFalse(board.getBoard()[i][j].isFree());
                else assertTrue(board.getBoard()[i][j].isFree());
            }
        }
    }
    @Test
    public void threePlayersCorrectTilesDisposition() throws SoldOutTilesException {
         boolean[][] threePlayersTiles =
                {       {false,false,false, false, false, false, false, false, false, false, false},
                        {false,false, false, false, true, false, false, false, false, false,false},
                        {false,false, false, false, true, true, false, false, false, false,false},
                        {false,false, false, true, true, true, true, true, false, false,false},
                        {false,false, false, true, true, true, true, true, true, true,false},
                        {false,false, true, true, true, true, true, true, true, false,false},
                        {false,true, true, true, true, true, true, true, false, false,false},
                        {false,false, false, true, true, true, true, true, false, false,false},
                        {false,false, false, false, false, true, true, false, false, false,false},
                        {false,false, false, false, false, false, true, false, false, false,false},
                        {false,false,false, false, false, false, false, false, false, false, false}
                };

        Board board = new Board(3);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (threePlayersTiles[i][j]) assertFalse(board.getBoard()[i][j].isFree());
                else assertTrue(board.getBoard()[i][j].isFree());
            }
        }
    }
    @Test
    public void fourPlayersCorrectTilesDisposition() throws SoldOutTilesException {
        boolean[][] fourPlayersTiles =
                {       {false,false,false, false, false, false, false, false, false, false, false},
                        {false,false, false, false, true, true, false, false, false, false,false},
                        {false,false, false, false, true, true, true, false, false, false,false},
                        {false,false, false, true, true, true, true, true, false, false,false},
                        {false,false, true, true, true, true, true, true, true, true,false},
                        {false,true, true, true, true, true, true, true, true, true,false},
                        {false,true, true, true, true, true, true, true, true, false,false},
                        {false,false, false, true, true, true, true, true, false, false,false},
                        {false,false, false, false, true, true, true, false, false, false,false},
                        {false,false, false, false, false, true, true, false, false, false,false},
                        {false,false,false, false, false, false, false, false, false, false, false}};

        Board board = new Board(4);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (fourPlayersTiles[i][j]) assertFalse(board.getBoard()[i][j].isFree());
                else assertTrue(board.getBoard()[i][j].isFree());
            }
        }
    }

    @Test
    void emptySlotOnBoard() throws EmptySlotException, InvalidSlotException , SoldOutTilesException{
        Board board = new Board(2);
        Tile[] tiles = new Tile[1];

        Coordinates[] positions = new Coordinates[1];
        positions[0] = new Coordinates(0,0);
        try {
            tiles = board.removeCardFromBoard(positions);

        } catch(EmptySlotException e) {
            System.out.println("EmptySlotException");
          } catch (InvalidPositionsException e) {
            System.out.println("InvalidPositionsException");
        }
    }


    @Test

    void invalidSlotOnBoard() throws EmptySlotException, InvalidSlotException , SoldOutTilesException, InvalidPositionsException{
        Board board = new Board(2);
        Tile[] tiles = new Tile[1];

        Coordinates[] positions = new Coordinates[1];
        positions[0] = new Coordinates(5,5);
        try {
            tiles = board.removeCardFromBoard(positions);

        } catch(InvalidSlotException e) {
            System.out.println("InvalidSlotException");
        }
    }




    @Test


    void validPositionsInBoard() throws InvalidPositionsException, InvalidSlotException, EmptySlotException, SoldOutTilesException{
        Board board = new Board(4);
        Tile[] tiles;
        Coordinates[] positions = new Coordinates[2];
        positions[0] = new Coordinates(1,8);
        positions[1] = new Coordinates(0,4);
        try{
            tiles = board.removeCardFromBoard(positions);
        }
         catch (InvalidSlotException e){
            System.out.println("invalidSlot");
         }

         catch(EmptySlotException e){
            System.out.println("emptySlot");
         }

         catch(InvalidPositionsException e){
            System.out.println("invalidPositions");
         }

        System.out.println("validPositions");
    }
}
