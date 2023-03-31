package it.polimi.ingsw.model;

public class CardPersonalTarget {
    private final PersonalCardTile[] personalCardTiles;

    public CardPersonalTarget(PersonalCardTile[] personalCardTiles) {
        this.personalCardTiles = personalCardTiles;
    }

    public PersonalCardTile[] getPersonalCardTiles() {
        return personalCardTiles;
    }
}
