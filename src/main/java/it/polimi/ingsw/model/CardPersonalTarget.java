package it.polimi.ingsw.model;



public class CardPersonalTarget {

    /**
     * vector of PersonalCardTile containing the information for assigning the personalCard
     */
    private final PersonalCardTile[] personalCardTiles;


    /**
     * costructor : assignment of personal card
     */

    public CardPersonalTarget(PersonalList personalType){
        /**
         *Utils utils : used for the assignment of the personal card
         */
        Utils utils = new Utils();
        personalCardTiles = new PersonalCardTile[6];
        utils.assignPersonalCard(personalCardTiles, personalType);
    }

    /**
     *
     * @return personalCardTiles vector containing the information of the personalCard
     */

    public PersonalCardTile[] getPersonalCardTiles() {
        return personalCardTiles;
    }
}
