package it.polimi.ingsw.model;

import it.polimi.ingsw.model.PersonalCards.PersonalDeck;
import org.junit.jupiter.api.Test;

class PersonalDeckTest {
    @Test
    void personalDeckTest(){
        PersonalDeck personalDeck = new PersonalDeck(2);
        System.out.println(personalDeck.getPersonalDeck().get(1).personalCardTiles()[0].colourTile());
    }

}