package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;

import java.util.ArrayList;

public class InitResponse extends Message{

    private ArrayList<CardCommonTarget> commonTargets;
    private CardPersonalTarget cardPersonalTarget;


    public InitResponse(ArrayList<CardCommonTarget> commonTargets, CardPersonalTarget cardPersonalTarget) {
        this.commonTargets = commonTargets;
        this.cardPersonalTarget = cardPersonalTarget;
    }

    public CardPersonalTarget getCardPersonalTarget() {
        return cardPersonalTarget;
    }

    public ArrayList<CardCommonTarget> getCommonTargets() {
        return commonTargets;
    }

    @Override
    public String typeMessage() {
        return "InitResponse";
    }


}
