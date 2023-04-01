package it.polimi.ingsw.model.PersonalCards;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class PersonalParser {


    CardPersonalTarget[] cardPersonalTargets ;

    public PersonalParser() throws FileNotFoundException {
        FileReader reader = new FileReader("listOfPersonalCards.json");
        Gson gson = new Gson();
        cardPersonalTargets = gson.fromJson(reader, CardPersonalTarget[].class);
    }

    public CardPersonalTarget[] getCardPersonalTargets() {
        return cardPersonalTargets;
    }
}
