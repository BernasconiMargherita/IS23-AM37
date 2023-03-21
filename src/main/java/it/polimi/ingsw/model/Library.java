package it.polimi.ingsw.model;


/**
 * class that represents player's library
 */
public class Library {

    /**
     * Library is a 6x5 matrix
     */
    private TileSlot[][] tileMatrix = new TileSlot[6][5];


    /**
     * initialize library with empty slots
     */
    public Library(){
        for(int i=0; i<5;i++){
            for(int j=0;j<5;j++){
                tileMatrix[i][j] = new TileSlot();
            }
        }
    }





    /**
     * method that adds up to three cards in the chosen column
     */
    public void addCardInColumn (int col, int tileNumber, Tile tile1, Tile tile2, Tile tile3) throws InvalidColumnException, FullColumnException, NoSpaceInColumnException {



        /**
         * if column is not empty
         */
        int row = 0;
        if (tileMatrix[row][col].IsFree() == false) {
            while (row < 6 && tileMatrix[row][col].IsFree()== false) {
                row++;
            }


            /**
             * if column is completely full
             */
            if (row == 5) {
                throw new FullColumnException();
                return;
            }


            /**
             * if column has not enough space
             */
            if (row > 5 - tileNumber) {
                throw new NoSpaceInColumnException();
            }
        }


        /**
         * if column is completely empty or has enough space.
         */

        else {

                tileMatrix[row][col].AssignTile(tile1);
            if ()
        }
    }




    /**
     * method that verifies if library is full
     */
    public boolean isFull(){
        int count =0;
        for (int i= 0; i<5; i++){
            for(int j=0; j<4; j++){
                if (tileMatrix[i][j].IsFree()==false) {
                    count++;
                }

                if (count == 30){
                    isFull() = true;
                    return true;

                }
            }
        }
        isFull() = false;
        return false;
    }


}