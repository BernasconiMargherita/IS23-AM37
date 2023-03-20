package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Random;

public class PersonalDeck {
    private ArrayList<CardPersonalTarget> personalDeck;

    private int numOfPlayers;

    private int[] numbers = new int[4]

    public CommonDeck(int numOfPlayers) {

        Random random = new Random();
        //generating 4 random numbers between 1 and 12 different from each other in order to extract 2,3 or 4 PersonalCards (based on the number of players)

        numbers[0] = random.nextInt(12) + 1;
        numbers[1] = random.nextInt(12) + 1;
        while (numbers[0] == numbers[1]) numbers[1] = random.nextInt(12) + 1;
        numbers[2] = random.nextInt(12) + 1;
        while (numbers[2] == numbers[0] || numbers[2] == numbers[1]) numbers[2] = random.nextInt(12) + 1;
        numbers[3] = random.nextInt(12) + 1;
        while (numbers[3] == numbers[0] || numbers[3] == numbers[1] || numbers[3] == numbers[2])
            numbers[3] = random.nextInt(12) + 1;

        //adding 2,3 or 4 (depending on the number of players) personalCards to the deck
        for (int i = 0; i < numOfPlayers; i++) {
            if (numbers[i] == 1) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA1))
            }


            if (numbers[i] == 2) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA2))
            }

            if (numbers[i] == 3) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA3))
            }

            if (numbers[i] == 4) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA4))
            }

            if (numbers[i] == 5) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA5))
            }

            if (numbers[i] == 6) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA6))
            }

            if (numbers[i] == 7) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA7))
            }

            if (numbers[i] == 8) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA8))
            }

            if (numbers[i] == 9) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA9))
            }

            if (numbers[i] == 10) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA10))
            }

            if (numbers[i] == 11) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA11))
            }

            if (numbers[i] == 12) {
                personalDeck.add(new CardPersonalTarget(PersonalList.CARTA12))
            }
        }

    }
    //getter of the personalDeck
    public ArrayList<CardPersonalTarget> getPersonalDeck() {
        return personalDeck;
    }
}
