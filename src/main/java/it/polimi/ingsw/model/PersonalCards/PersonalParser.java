package it.polimi.ingsw.model.PersonalCards;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;

public class PersonalParser implements Serializable {

    /**
     * vector of all Personal Cards in game
     */
    CardPersonalTarget[] cardPersonalTargets ;

    /**
     * constructor that deserializes the JSON file: listOfPersonalCards.json
     * @throws FileNotFoundException
     */

    public PersonalParser() throws FileNotFoundException {
        FileReader reader = new FileReader("src/main/resources/json/listOfPersonalCards.json");
        Gson gson = new Gson();
        cardPersonalTargets = gson.fromJson(reader, CardPersonalTarget[].class);
    }

    /**
     * getter of cardPersonalTargets
     * @return
     */
    public CardPersonalTarget[] getCardPersonalTargets() {
        return cardPersonalTargets;
    }
}
