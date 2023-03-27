package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Random;

public class PersonalDeck {
    /**
     * ArrayList<CardPersonalTarget> personalDeck : arrayList that contains (numOfPlayers) personalCards
     */
    private final ArrayList<CardPersonalTarget> personalDeck;

    /**
     * int numOfPlayers : number of players in game
     */
    private int numOfPlayers;

    /**
     * constructor of PersonalDeck that extracts the (numOfPlayers) personalCards
     */
    public PersonalDeck(int numOfPlayers) {

        personalDeck = new ArrayList<>();

        Random random = new Random();

        //extraction of 4 different casual numbers

        int[] numbers = new int[4];
        numbers[0] = random.nextInt(12) + 1;
        numbers[1] = random.nextInt(12) + 1;

        while (numbers[0] == numbers[1]) numbers[1] = random.nextInt(12) + 1;
        numbers[2] = random.nextInt(12) + 1;

        while (numbers[2] == numbers[0] || numbers[2] == numbers[1]) numbers[2] = random.nextInt(12) + 1;
        numbers[3] = random.nextInt(12) + 1;

        while (numbers[3] == numbers[0] || numbers[3] == numbers[1] || numbers[3] == numbers[2])
            numbers[3] = random.nextInt(12) + 1;


        String[] personalList = {"CARTA1", "CARTA2", "CARTA3", "CARTA4", "CARTA5", "CARTA6", "CARTA7", "CARTA8", "CARTA9", "CARTA10", "CARTA11", "CARTA12"};
        personalDeck.add(new CardPersonalTarget(PersonalList.valueOf(personalList[numbers[0]])));
        personalDeck.add(new CardPersonalTarget(PersonalList.valueOf(personalList[numbers[1]])));
        if(numOfPlayers>=3) {
            personalDeck.add(new CardPersonalTarget(PersonalList.valueOf(personalList[numbers[2]])));
            if (numOfPlayers == 4) personalDeck.add(new CardPersonalTarget(PersonalList.valueOf(personalList[numbers[3]])));

        }
    }

    /**
     * getter of the personalDeck
     * @return personalDeck
     */
    public ArrayList<CardPersonalTarget> getPersonalDeck() {
        return personalDeck;
    }

}
