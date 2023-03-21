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
     *
     * @param col
     * @throws FullColumnException
     * @throws NoSpaceInColumnException
     */
    public void addCardInColumn (int col, Tile[] selectedTile) throws FullColumnException, NoSpaceInColumnException {


        int row = 0;
        if (!tileMatrix[row][col].IsFree()) {
            while (row < 6 && !tileMatrix[row][col].IsFree()) {
                row++;
            }


            if (row == 5) {
                throw new FullColumnException();
            }


            if (row > 5 - selectedTile.length) {
                throw new NoSpaceInColumnException();
            }

        } else {

            for(int i=0; i < selectedTile.length; i++) {

                tileMatrix[row][col].AssignTile(selectedTile[i]);

            }


        }
    }




    /**
     * method that verifies if library is full
     */
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