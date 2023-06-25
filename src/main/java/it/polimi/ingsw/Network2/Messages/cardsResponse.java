package it.polimi.ingsw.Network2.Messages;

import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;

import java.util.ArrayList;

public class cardsResponse extends Message{
    private final ArrayList<CardCommonTarget> commonTargets;
    private final CardPersonalTarget cardPersonalTarget;
    public cardsResponse(int gameID, Long UID,ArrayList<CardCommonTarget> commonTargets, CardPersonalTarget cardPersonalTarget ) {
        super(gameID, UID);
        this.commonTargets = commonTargets;
        this.cardPersonalTarget = cardPersonalTarget;
    }
    public CardPersonalTarget getCardPersonalTarget() {
        return cardPersonalTarget;
    }

    public ArrayList<CardCommonTarget> getCommonTargets() {
        return commonTargets;
    }
}
