package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonDeckTest {

    @Test
    void CommonDeck(){
        CommonDeck deck1= new CommonDeck(2);
        CardCommonTarget carta1 = deck1.getCommonDeck().get(1);
        CardCommonTarget carta2 = deck1.getCommonDeck().get(2);
        assertNotEquals(carta1,carta2);
    }
}