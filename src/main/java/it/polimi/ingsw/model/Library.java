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
    public void addCardInColumn (int col, int tileNumber, Tile tile1, Tile tile2, Tile tile3) throws FullColumnException, NoSpaceInColumnException {

        int row = 0;
        if (!tileMatrix[row][col].IsFree()) {
            while (row < 6 && !tileMatrix[row][col].IsFree()) {
                row++;
            }


            if (row == 5) {
                throw new FullColumnException();
                return;
            }


            if (row > 5 - tileNumber) {
                throw new NoSpaceInColumnException();
            }
        } else {

            tileMatrix[row][col].AssignTile(tile1);

        }

    }
        public boolean isFull(){
        int count =0;
        for (int i= 0; i<5; i++){
            for(int j=0; j<4; j++){
                if (!tileMatrix[i][j].IsFree()) {
                    count++;
                }

                if (count == 30){
                    return true;

                }
            }
        }
        return false;
    }



}