package it.polimi.ingsw.model;

import java.util.Stack;

public class CardCommonTarget {
    private boolean assignedCommonCard;

    //token pile
    private Stack<ScoringToken> stackToken;

    private CommonList commonType;

    public CardCommonTarget(CommonList commonType, boolean assignedCommonCard, int numOfPlayers){

        //inizializzo la pila di token
        stackToken = new Stack<ScoringToken>();

        this.commonType = commonType;

        this.assignedCommonCard = assignedCommonCard;


        //case: 2 players
        if(numOfPlayers == 2){
            //push the values of the ScoringToken
            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }


        //case: 2 players
        if(numOfPlayers == 3){
            //push the values of the ScoringToken
            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,6));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }


        //case: 2 players
        if(numOfPlayers == 4){
            //push the values of the ScoringToken
            stackToken.push(new ScoringToken(assignedCommonCard,2));
            stackToken.push(new ScoringToken(assignedCommonCard,4));
            stackToken.push(new ScoringToken(assignedCommonCard,6));
            stackToken.push(new ScoringToken(assignedCommonCard,8));
        }
    }
    //ritorna il valore int del token
    public int getScoringToken(){
        return stackToken.pop().getValueToken();
    }

    //ritorna il valore del numero romano dietro al token
    public boolean getAssignedCommonCard() {
        return assignedCommonCard;
    }

    public CommonList getCommonType() {
        return commonType;
    }
}
