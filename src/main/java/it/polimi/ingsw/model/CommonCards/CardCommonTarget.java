package it.polimi.ingsw.model.CommonCards;

import java.io.Serializable;
import java.util.Stack;


public class CardCommonTarget implements Serializable {
    /**
     *  boolean assignedCommonCard: identifies the first or second common card of the board
     */

    private final int assignedCommonCard;

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
    public CardCommonTarget(CommonList commonType, int assignedCommonCard, int numOfPlayers){



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
     *
     * @return assignedCommonCard
     */
    public int getAssignedCommonCard() {
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
