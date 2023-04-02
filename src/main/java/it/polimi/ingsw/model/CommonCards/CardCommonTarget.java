package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.model.Card;

import java.util.Stack;



import java.util.Stack;


public class CardCommonTarget extends Card {
    /**
     *  boolean assignedCommonCard: identifies the first or second common card of the board
     */

    private final boolean assignedCommonCard;

    /**
     * CommonList typeOfCommonCard: identifies the goal of the common card
     */

    private final CommonList commonType;

    /**
     *Stack<ScoringToken> stackToken : stack of scoring tokens
     */
    private final Stack<ScoringToken> stackToken;
    /**
     * constructor that assign to the card:
     * @param commonType enum value of the common card
     * @param assignedCommonCard which of the two card the scoring token is assigned to
     */
    public CardCommonTarget(CommonList commonType, boolean assignedCommonCard, int numOfPlayers){



        stackToken = new Stack<ScoringToken>();

        this.commonType = commonType;

        this.assignedCommonCard = assignedCommonCard;



        if(numOfPlayers == 2){


            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }



        if(numOfPlayers == 3){

            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,6));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }



        if(numOfPlayers == 4){

            stackToken.push(new ScoringToken(assignedCommonCard,2));
            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,6));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }
    }

    /**
     * getter of the CommonList value (enum) of the Scoring token  -> also removes the scoringToken from the stack
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
