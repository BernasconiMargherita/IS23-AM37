package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Random;


public class CommonDeck {
    /**
     *  ArrayList<CrdCommonTarget> commonDeck : ArrayList of commonCard (lenght -> 2)
     */
    private ArrayList<CardCommonTarget> commonDeck;

    /**
     * int numOfPlayers : number of players
     */
    private int numOfPlayers;


    /**
     * costructor of CommonDeck that extracts 2 commonCards
     * @param numOfPlayers
     */
    public CommonDeck(int numOfPlayers){

        commonDeck = new ArrayList<>();


        /**
         * extraction of the first commonCard
         */

        Random random = new Random();
        int number = random.nextInt(12) + 1;
        int oldNumber = number;


        /**
         * creation of the first commonCard
         */

        if(number==1) {
            commonDeck.add(new CardCommonTarget(CommonList.SIX_GROUPS_OF_TWO, false, this.numOfPlayers));
        }

        if(number==2) {
            commonDeck.add(new CardCommonTarget(CommonList.FOUR_EQUALS_ANGLES, false, this.numOfPlayers));
        }

        if(number==3) {
            commonDeck.add(new CardCommonTarget(CommonList.FOUR_GROUPS_OF_FOUR, false, this.numOfPlayers));
        }

        if(number==4) {
            commonDeck.add(new CardCommonTarget(CommonList.TWO_GROUPS_IN_SQUARE, false, this.numOfPlayers));
        }

        if(number==5) {
            commonDeck.add(new CardCommonTarget(CommonList.THREE_FULL_COLUMNS_WITH_MAX_THREE_DIFFERENT_TYPES, false, this.numOfPlayers));
        }

        if(number==6) {
            commonDeck.add(new CardCommonTarget(CommonList.EIGHT_EQUALS, false, this.numOfPlayers));
        }
        if(number==7) {
            commonDeck.add(new CardCommonTarget(CommonList.FIVE_IN_DIGONAL, false, this.numOfPlayers));
        }

        if(number==8) {
            commonDeck.add(new CardCommonTarget(CommonList.FOUR_FULL_ROWS_WITH_MAX_THREE_DIFFERENT_TYPES, false, this.numOfPlayers));
        }

        if(number==9) {
            commonDeck.add(new CardCommonTarget(CommonList.TWO_FULL_COLUMNS_ALL_DIFFERENT, false, this.numOfPlayers));
        }

        if(number==10) {
            commonDeck.add(new CardCommonTarget(CommonList.TWO_FULL_ROWS_ALL_DIFFERENT, false, this.numOfPlayers));
        }

        if(number==11) {
            commonDeck.add(new CardCommonTarget(CommonList.FIVE_IN_A_X, false, this.numOfPlayers));
        }

        if(number==12) {
            commonDeck.add(new CardCommonTarget(CommonList.IN_DESCENDING_ORDER, false, this.numOfPlayers));
        }

        /**
         * extraction of the second commonCard so that it is a different commonCard from the first
         */


        number = random.nextInt(12) + 1;
        while(number == oldNumber) number = random.nextInt(12) + 1;

        /**
         * creation of the second commonCard
         */

        if(number==1) {
            commonDeck.add(new CardCommonTarget(CommonList.SIX_GROUPS_OF_TWO, true, this.numOfPlayers));
        }

        if(number==2) {
            commonDeck.add(new CardCommonTarget(CommonList.FOUR_EQUALS_ANGLES, true, this.numOfPlayers));
        }

        if(number==3) {
            commonDeck.add(new CardCommonTarget(CommonList.FOUR_GROUPS_OF_FOUR, true, this.numOfPlayers));
        }

        if(number==4) {
            commonDeck.add(new CardCommonTarget(CommonList.TWO_GROUPS_IN_SQUARE, true, this.numOfPlayers));
        }

        if(number==5) {
            commonDeck.add(new CardCommonTarget(CommonList.THREE_FULL_COLUMNS_WITH_MAX_THREE_DIFFERENT_TYPES, true, this.numOfPlayers));
        }

        if(number==6) {
            commonDeck.add(new CardCommonTarget(CommonList.EIGHT_EQUALS, true, this.numOfPlayers));
        }
        if(number==7) {
            commonDeck.add(new CardCommonTarget(CommonList.FIVE_IN_DIGONAL, true, this.numOfPlayers));
        }

        if(number==8) {
            commonDeck.add(new CardCommonTarget(CommonList.FOUR_FULL_ROWS_WITH_MAX_THREE_DIFFERENT_TYPES, true, this.numOfPlayers));
        }

        if(number==9) {
            commonDeck.add(new CardCommonTarget(CommonList.TWO_FULL_COLUMNS_ALL_DIFFERENT, true, this.numOfPlayers));
        }

        if(number==10) {
            commonDeck.add(new CardCommonTarget(CommonList.TWO_FULL_ROWS_ALL_DIFFERENT, true, this.numOfPlayers));
        }

        if(number==11) {
            commonDeck.add(new CardCommonTarget(CommonList.FIVE_IN_A_X, true, this.numOfPlayers));
        }

        if(number==12) {
            commonDeck.add(new CardCommonTarget(CommonList.IN_DESCENDING_ORDER, true, this.numOfPlayers));
        }
    }

    /**
     * getter of the CommonCards Arraylist
     * @return commonDeck
     */
    public ArrayList<CardCommonTarget> getCommonDeck() {
        return commonDeck;
    }
}
