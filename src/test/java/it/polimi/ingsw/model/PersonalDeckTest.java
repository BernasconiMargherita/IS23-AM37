package it.polimi.ingsw.model;

import it.polimi.ingsw.model.PersonalCards.PersonalDeck;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PersonalDeckTest {
    @Test
    void personalDeckTest(){
        PersonalDeck personalDeck = new PersonalDeck(2);
        System.out.println(personalDeck.getPersonalDeck().get(1).getPersonalCardTiles()[0].colourTile());
    }

}