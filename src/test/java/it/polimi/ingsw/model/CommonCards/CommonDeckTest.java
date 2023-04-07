package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.CommonCards.CommonDeck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonDeckTest {

    @Test
    void twoDifferentCards(){
        CommonDeck deck1= new CommonDeck(2);
        CardCommonTarget carta1 = deck1.getCommonDeck().get(0);
        CardCommonTarget carta2 = deck1.getCommonDeck().get(1);
        assertNotSame(carta1.getCommonType(), carta2.getCommonType());
    }
}