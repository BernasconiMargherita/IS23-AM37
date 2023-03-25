package it.polimi.ingsw.model;



public class CardPersonalTarget {

    /**
     * vector of PersonalCardTile containing the information for assigning the personalCard
     */
    private final PersonalCardTile[] personalCardTiles;


    /**
     * constructor : assignment of personal card
     */

    public CardPersonalTarget(PersonalList personalType){
        Utils utils = new Utils();
        personalCardTiles = utils.assignPersonalCard(personalType);

    }

    /**
     *
     * @return personalCardTiles vector containing the information of the personalCard
     */

    public PersonalCardTile[] getPersonalCardTiles() {
        return personalCardTiles;
    }
}
