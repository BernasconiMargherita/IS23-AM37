package it.polimi.ingsw.model;


/**
 * class that represents player's library
 */
public class Library {

    private TileSlot[][] tileMatrix = new TileSlot[6][5];

    public Library(){
        /**initialize library with empty slots*
         */
        for(int i=0; i<5;i++){
            for(int j=0;j<5;j++){
                tileMatrix[i][j] = new TileSlot();
            }
        }
    }

    /**method that adds up to three cards in the chosen column*
     */
    public void addCardInColumn (int col, Tile tile1, Tile tile2, Tile tile3) throws InvalidColumnException, FullColumnException{

        if(col<0 || col>=5){
            throw new InvalidColumnException();
            return;
        }
        /**
         * if column is not empty
         */
        if (tileMatrix[0][col] != 0){
            int row=0;
            while(row<6 && tileMatrix[row][col]!=0){
                row++;
            }
            /**
             * if column is completely full
             */
            if (row == 5){
                throw new FullColumnException();
                return;
            }
        }
        tileMatrix[0][col]= tile1;
        if(tile2 !=0){
            tileMatrix[1][col] = tile3;
        }
        if(tile3 !=0){

        }

        /**
         * method that verifies if library is full
         */
        public boolean isFull(){
            int count =0;
            for (int i= 0; i<5; i++){
                for(int j=0; j<4; j++){
                    if (tileMatrix[i][j] != 0) {
                        count++;
                    }
                    /**
                     * if library full
                     */
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
    tileMatrix[2][col] = tile3;
        }