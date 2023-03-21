package it.polimi.ingsw.model;

public class Player {
    private String nickname;

    public Player(String nickname){
        return this.nickname;
    }
    public void addTilesinLibrary( Library library, int row, int column) {
        library.setValue(row, column, 1);
    }

    /** metodo completamente da modificare
     *
     * @param library
     * @param cardCommonTarget
     * @return
     */
    public boolean checkCommonTarget( Library library, CardCommonTarget cardCommonTarget){
        int matrix = cardCommonTarget.getMatrix();
        int numRows = library.getNumRows();
        int numCols = library.getNumCols();

        for(int i=0; i< numRows; i++){
            for(int j= 0; i< numCols; j++){
                if(matrix[i][j] !=null){
                    if(matrix[i][j] != library.getValue[i][j]){   /** qua adesso metto semplicemnte diverso **/
                        return false;   /** in realtà poi controlliamo che il colore sia giusto etc **/
                    }
                    return true;

                }
            }
        }
    }

}
