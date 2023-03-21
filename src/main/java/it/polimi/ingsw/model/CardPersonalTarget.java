package it.polimi.ingsw.model;

public class CardPersonalTarget extends Card {
    /**
     * TileSlot[][] personalMatrix : matrix (TileSlot) of the personalCard
     */
    private TileSlot[][] personalMatrix;
    /**
     *Utils utils : to fill the personalMatrix
     */
    Utils utils;


    /**
     * costructor of CardPersonalTarget that assign the personalType
     * @param personalType : identifies the goal of the personalCard
     */
    public CardPersonalTarget(PersonalList personalType) {
        utils = new Utils();

        /**
         * initializing personalMatrix
         */
        personalMatrix = new TileSlot[6][5];


        /**
         * filling of the personal matrix
         */
        personalMatrix = utils.fillPersonalMatrix(personalMatrix, personalType);
    }


    /**
     * getter of the personalMatrix
     * @return personalMatrix
     */
    public TileSlot[][] getPersonalMatrix() {
        return personalMatrix;
    }
}
