package it.polimi.ingsw.model.PersonalCards;

public class CardPersonalTarget {
    private final PersonalCardTile[] personalCardTiles;

    public CardPersonalTarget(PersonalCardTile[] personalCardTiles) {
        this.personalCardTiles = personalCardTiles;
    }

    public PersonalCardTile[] getPersonalCardTiles() {
        return personalCardTiles;
    }
}
