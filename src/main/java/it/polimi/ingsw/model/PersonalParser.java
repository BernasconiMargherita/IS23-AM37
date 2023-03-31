package it.polimi.ingsw.model;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class PersonalParser {
    private final Gson gson = new Gson();
    private final FileReader reader ;

    CardPersonalTarget[] cardPersonalTargets ;

    public PersonalParser() throws FileNotFoundException {
        reader = new FileReader("C:\\Users\\ramir\\Desktop\\ProgettoIngegneriaDelSoftware\\src\\main\\java\\it\\polimi\\ingsw\\model\\listOfPersonalCards.json");
        cardPersonalTargets = gson.fromJson(reader, CardPersonalTarget[].class);
    }

    public CardPersonalTarget[] getCardPersonalTargets() {
        return cardPersonalTargets;
    }
}
