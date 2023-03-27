package it.polimi.ingsw.model;

public class ScoringToken {
    /**
     *  boolean assignedCommonCard: identifies the first or second common card of the board
     */
    boolean assignedCommonCard;
    /**
     * int valueToken : value of the ScoringToken (2 or 4 or 6 or 8)
     */
    int valueToken;

    /**
     * constructor of ScoringToken that assign the prameters to assignedCommonCard and valueToken
     * @param assignedCommonCard identifies the first or second common card of the board
     * @param valueToken value of the ScoringToken
     */
    public ScoringToken(boolean assignedCommonCard, int valueToken){
        this.assignedCommonCard = assignedCommonCard;
        this.valueToken = valueToken;
    }

    /**
     * getter of getValueToken
     * @return valueToken
     */

    public int getValueToken() {
        return valueToken;
    }
}
