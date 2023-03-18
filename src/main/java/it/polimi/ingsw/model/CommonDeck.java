package it.polimi.ingsw.model;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Random;



public class CommonDeck {
    private ArrayList<CardCommonTarget> commonDeck;
    private int numOfPlayers;



    public CommonDeck(int numOfPlayers){


        //extraction of the first commonCard

        Random random = new Random();
        int number = random.nextInt(12) + 1;
        int oldNumber = number;


        //creation of the first commonCard

        if(number==1) {
            commonDeck.add(new CardCommonTarget(1, false, this.numOfPlayers));
        }

        if(number==2) {
            commonDeck.add(new CardCommonTarget(2, false, this.numOfPlayers));
        }

        if(number==3) {
            commonDeck.add(new CardCommonTarget(3, false, this.numOfPlayers));
        }

        if(number==4) {
            commonDeck.add(new CardCommonTarget(4, false, this.numOfPlayers));
        }

        if(number==5) {
            commonDeck.add(new CardCommonTarget(5, false, this.numOfPlayers));
        }

        if(number==6) {
            commonDeck.add(new CardCommonTarget(6, false, this.numOfPlayers));
        }
        if(number==7) {
            commonDeck.add(new CardCommonTarget(7, false, this.numOfPlayers));
        }

        if(number==8) {
            commonDeck.add(new CardCommonTarget(8, false, this.numOfPlayers));
        }

        if(number==9) {
            commonDeck.add(new CardCommonTarget(9, false, this.numOfPlayers));
        }

        if(number==10) {
            commonDeck.add(new CardCommonTarget(10, false, this.numOfPlayers));
        }

        if(number==11) {
            commonDeck.add(new CardCommonTarget(11, false, this.numOfPlayers));
        }

        if(number==12) {
            commonDeck.add(new CardCommonTarget(12, false, this.numOfPlayers));
        }

        //extraction of the second commonCard so that it is a different commonCard from the first


        number = random.nextInt(12) + 1;
        while(number == oldNumber) number = random.nextInt(12) + 1;

        //creation of the second commonCard

        if(number==1) {
            commonDeck.add(new CardCommonTarget(1, true, this.numOfPlayers));
        }

        if(number==2) {
            commonDeck.add(new CardCommonTarget(2, true, this.numOfPlayers));
        }

        if(number==3) {
            commonDeck.add(new CardCommonTarget(3, true, this.numOfPlayers));
        }

        if(number==4) {
            commonDeck.add(new CardCommonTarget(4, true, this.numOfPlayers));
        }

        if(number==5) {
            commonDeck.add(new CardCommonTarget(5, true, this.numOfPlayers));
        }

        if(number==6) {
            commonDeck.add(new CardCommonTarget(6, true, this.numOfPlayers));
        }
        if(number==7) {
            commonDeck.add(new CardCommonTarget(7, true, this.numOfPlayers));
        }

        if(number==8) {
            commonDeck.add(new CardCommonTarget(8, true, this.numOfPlayers));
        }

        if(number==9) {
            commonDeck.add(new CardCommonTarget(9, true, this.numOfPlayers));
        }

        if(number==10) {
            commonDeck.add(new CardCommonTarget(10, true, this.numOfPlayers));
        }

        if(number==11) {
            commonDeck.add(new CardCommonTarget(11, true, this.numOfPlayers));
        }

        if(number==12) {
            commonDeck.add(new CardCommonTarget(12, true, this.numOfPlayers));
        }
    }


    public ArrayList<CardCommonTarget> getCommonDeck() {
        return commonDeck;
    }
}
