package it.polimi.ingsw.model;

import java.util.Stack;



import java.util.Stack;

public class CardCommonTarget {
    //first or second common card of the board
    private boolean assignedCommonCard;

    //typeOfCommonCard
    private CommonList commonType;

    //token pile
    private Stack<ScoringToken> stackToken;

    public CardCommonTarget(CommonList commonType, boolean assignedCommonCard, int numOfPlayers){

        //initializing the stack of ScoringTokens
        stackToken = new Stack<ScoringToken>();
        //CommonCard type
        this.commonType = commonType;
        //first or second CommonCard on the Board
        this.assignedCommonCard = assignedCommonCard;


        //case: 2 players
        if(numOfPlayers == 2){
            //push the values of the ScoringToken
            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }


        //case: 3 players
        if(numOfPlayers == 3){
            //push the values of the ScoringToken
            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,6));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }


        //case: 4 players
        if(numOfPlayers == 4){
            //push the values of the ScoringToken
            stackToken.push(new ScoringToken(assignedCommonCard,2));
            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,6));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }
    }
    //return the CommonList value (enum) of the Scoringtoken
    public int getScoringToken(){
        return stackToken.pop().getValueToken();
    }

    //return the value of assignedCommonCard
    public boolean getAssignedCommonCard() {
        return assignedCommonCard;
    }
    //return the type of the commonCard
    public CommonList getCommonType() {
        return commonType;
    }
}
