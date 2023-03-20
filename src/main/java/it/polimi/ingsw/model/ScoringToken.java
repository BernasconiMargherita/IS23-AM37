package it.polimi.ingsw.model;

public class ScoringToken {
    boolean assignedCommonCard;
    int valueToken;
    public ScoringToken(boolean assignedCommonCard, int valueToken){
        this.assignedCommonCard = assignedCommonCard;
        this.valueToken = valueToken;
    }

    public int getValueToken() {
        return valueToken;
    }
}
