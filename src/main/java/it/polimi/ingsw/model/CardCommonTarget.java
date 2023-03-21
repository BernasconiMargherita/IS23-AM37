package it.polimi.ingsw.model;

import java.util.Stack;



import java.util.Stack;


public class CardCommonTarget extends Card {
    /**
     *  boolean assignedCommonCard: identifies the first or second common card of the board
     */

    private boolean assignedCommonCard;

    /**
     * CommonList typeOfCommonCard: identifies the goal of the common card
     */

    private CommonList commonType;

    /**
     *Stack<ScoringToken> stackToken : stack of scoring tokens
     */
    private Stack<ScoringToken> stackToken;
    /**
     * constructor that assign to the card:
     * @param commonType
     * @param assignedCommonCard
     * @param numOfPlayers
     */
    public CardCommonTarget(CommonList commonType, boolean assignedCommonCard, int numOfPlayers){

        /**
         * initializing the stack of ScoringTokens
         */

        stackToken = new Stack<ScoringToken>();
        /**
         * assigning commonType
         */
        this.commonType = commonType;
        /**
         * assigning assignedCommonCard
         */
        this.assignedCommonCard = assignedCommonCard;


        /**
         * case: 2 players
         */
        if(numOfPlayers == 2){
            /**
             * push the values of the ScoringToken
             */

            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }


        /**
         * case: 3 players
         */
        if(numOfPlayers == 3){
            /**
             * push the values of the ScoringToken
             */
            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,6));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }


        /**
         * case: 4 players
         */
        if(numOfPlayers == 4){
            /**
             * push the values of the ScoringToken
             */
            stackToken.push(new ScoringToken(assignedCommonCard,2));
            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,6));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }
    }

    /**
     * getter of the CommonList value (enum) of the Scoringtoken  -> also removes the scoringToken from the stack
     * @return stackToken.pop().getValueToken()  ->  scoringToken
     */
    public int getScoringToken(){
        return stackToken.pop().getValueToken();
    }

    /**
     * getter of the value of assignedCommonCard
     * @return assignedCommonCard
     */
    public boolean getAssignedCommonCard() {
        return assignedCommonCard;
    }

    /**
     *   getter of the type of the commonCard
     * @return commonType
     */

    public CommonList getCommonType() {
        return commonType;
    }
}
