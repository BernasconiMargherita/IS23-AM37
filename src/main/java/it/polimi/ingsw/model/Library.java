package it.polimi.ingsw.model;

public class Library {
    private TileSlot[][] tileMatrix = new TileSlot[6][5];

    public Library(){
        /**inizializza la matrice con slot vuoti**/
        for(int i=0; i<5;i++){
            for(int j=0;j<5;j++){
                tileMatrix[i][j] = new TileSlot();
            }
        }
    }

    /**metodo per aggiungere fino a tre carte nella colonna scelta**/
    public void addCardInColumn (int col, Card tile1, Card tile2, Card tile3){
        if(col<0 || col>=5){
            System.out.println("colonna non valida");
            return;
        }
        /**se la colonna non è vuota**/
        if (tileMatrix[0][col] != 0){
            int row=0;
            while(row<6 && tileMatrix[row][col]!=0){
                row++;
            }
            /**se la colonna è piena**/
            if (row == 5){
                System.out.println("la colonna è piena");
                return;
            }
        }
        tileMatrix[0][col]= tile1;
        if(tile2 !=0){
            tileMatrix[1][col] = tile3;
        }
        if(tile3 !=0){

        }

        /**metodo per verificare se la libreria è piena**/
        public boolean isFull(){
            int count =0;
            for (int i= 0; i<5; i++){
                for(int j=0; j<4; j++){
                    if (tileMatrix[i][j] != 0) {
                        count++;
                    }
                    /**se la libreria è piena**/
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