package it.polimi.ingsw.model;


/**
 * class that represents player's library
 */
public class Library {

    /**
     * Library is a 6x5 matrix
     */
    private TileSlot[][] library = new TileSlot[6][5];


    /**
     * initialize library with empty slots
     */
    public Library(){
        for(int i=0; i<5;i++){
            for(int j=0;j<5;j++){
                library[i][j] = new TileSlot();
            }
        }
    }


    /**
     * method that adds up to three selected tiles in Library. It counts, in the given column, how many rows are full with method isFree (form TileSlot)
     * Tiles are stored in array created by Board and put in the library with assignTile method
     * @param col
     * @throws FullColumnException if column is full
     * @throws NoSpaceInColumnException if there is not enough space for the selected numbers of tiles
     */
    public void addCardInColumn (int col, Tile[] selectedTile) throws FullColumnException, NoSpaceInColumnException {


        int row = 0;
        if (!library[row][col].isFree()) {
            while (row < 6 && !library[row][col].isFree()) {
                row++;
            }


            if (row == 5) {
                throw new FullColumnException();
            }


            if (row > 5 - selectedTile.length) {
                throw new NoSpaceInColumnException();
            }

        } else {

            for (Tile tile : selectedTile) {

                library[row][col].assignTile(tile);

            }


        }
    }


    /**
     * method that verifies if library is completely full. It controls if every slot of the last row is full
     * @return
     */
    public boolean isFull(){

        int count =0;
        for (int i= 0; i<4; i++){
                if (!library[5][i].isFree()) {
                    count++;
                }

                if (count == 5){
                    return true;

                }
            }
        return false;
    }

    public TileSlot[][] getLibrary() {
        return library;
    }
}