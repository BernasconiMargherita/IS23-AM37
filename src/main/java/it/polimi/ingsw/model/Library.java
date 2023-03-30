package it.polimi.ingsw.model;


/**
 * class that represents player's library
 */
public class Library {
    public static final int MAX_SHELF_ROWS = 6;
    public static final int MAX_SHELF_COLUMNS = 5;

    /**
     * Library is a MAX_SHELF_ROWSxMAX_SHELF_COLUMNS matrix
     */
    private final TileSlot[][] library = new TileSlot[MAX_SHELF_ROWS][MAX_SHELF_COLUMNS];


    /**
     * initialize library with empty slots
     */
    public Library(){
        for(int i=0; i<MAX_SHELF_ROWS;i++){
            for(int j=0;j<MAX_SHELF_COLUMNS;j++){
                library[i][j] = new TileSlot();
            }
        }
    }


    /**
     * method that adds up to three selected tiles in Library. It counts, in the given column, how many rows are full using the method isFree (form TileSlot)
     * Tiles are stored in array created by Board and put in the library with assignTile method
     * @throws FullColumnException if column is full
     * @throws NoSpaceInColumnException if there is not enough space for the selected numbers of tiles
     */
    public void addCardInColumn (int col, Tile[] selectedTile) throws FullColumnException, NoSpaceInColumnException {


        int row = 0;
        if (!library[row][col].isFree()) {
            while (row < MAX_SHELF_ROWS && !library[row][col].isFree()) {
                row++;
            }


            if (row == MAX_SHELF_COLUMNS) {
                throw new FullColumnException();
            }


            if (row > MAX_SHELF_COLUMNS - selectedTile.length) {
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
     */
    public boolean isFull(){

        int count =0;
        for (int i= 0; i<4; i++){
                if (!library[MAX_SHELF_COLUMNS][i].isFree()) {
                    count++;
                }

                if (count == MAX_SHELF_COLUMNS){
                    return true;

                }
            }
        return false;
    }

    public TileSlot[][] getLibrary() {
        return library;
    }
}