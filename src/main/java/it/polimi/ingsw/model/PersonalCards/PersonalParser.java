package it.polimi.ingsw.model.PersonalCards;

import com.google.gson.Gson;

import java.io.*;

public class PersonalParser implements Serializable {

    /**
     * vector of all Personal Cards in game
     */
    CardPersonalTarget[] cardPersonalTargets;

    /**
     * constructor that deserializes the JSON file: listOfPersonalCards.json
     *
     * @throws FileNotFoundException
     */

    public PersonalParser() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/listOfPersonalCards.json");
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();
        cardPersonalTargets = gson.fromJson(reader, CardPersonalTarget[].class);
        reader.close();

    }

    /**
     * getter of cardPersonalTargets
     *
     * @return
     */
    public CardPersonalTarget[] getCardPersonalTargets() {
        return cardPersonalTargets;
    }
}
