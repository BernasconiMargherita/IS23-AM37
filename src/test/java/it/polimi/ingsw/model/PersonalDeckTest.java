package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PersonalDeckTest {
    @Test
    void personalDeckTest() throws FileNotFoundException {
        PersonalDeck personalDeck = new PersonalDeck(2);
        System.out.println(personalDeck.getPersonalDeck().get(1).getPersonalCardTiles()[0].colourTile());
    }

}