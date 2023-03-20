package it.polimi.ingsw.model;

public class CardPersonalTarget {
    //matrix (TileSlot) of the personalCard
    private TileSlot[][] personalMatrix;
    //utils ----> to fill the personalMatrix
    Utils utils;






    public CardPersonalTarget(, PersonalList personalType) {
        utils = new Utils();

        //initializing personalMatrix
        personalMatrix = new TileSlot[6][5];


        //filling of the personal matrix
        personalMatrix = utils.fillPersonalMatrix(personalMatrix, personalType);
    }





    //getter of the personalMatrix
    public TileSlot[][] getPersonalMatrix() {
        return personalMatrix;
    }
}

