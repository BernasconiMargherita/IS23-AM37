package it.polimi.ingsw.model;

public class Utils {

    /**
     * method that compares the player's library and the personalCard, and returns the number of the completed objectives (0,...,6)
     * @param library
     * @param cardPersonalTarget
     * @return completedGoals
     */


    public int CheckPersonalTarget(Library library, CardPersonalTarget cardPersonalTarget){
        int completedGoals = 0;


        for(int i = 0; i < 6 ; i++)
            if (!(library.getLibrary()[cardPersonalTarget.getPersonalCardTiles()[i].getCoordinates().getX()][cardPersonalTarget.getPersonalCardTiles()[i].getCoordinates().getY()].IsFree()) && cardPersonalTarget.getPersonalCardTiles()[i].getColourTile() == library.getLibrary()[cardPersonalTarget.getPersonalCardTiles()[i].getCoordinates().getX()][cardPersonalTarget.getPersonalCardTiles()[i].getCoordinates().getY()].getAssignedTile().getColour()) {
                completedGoals++;
            }

        return completedGoals;
    }


    public boolean CheckCommonTarget(Library library,CardCommonTarget commonCard){

        return false;
    }

    public TileSlot[][] assignPersonalCard(PersonalCardTile[] personalCardTiles, PersonalList personalType){
        if(personalType==PersonalList.CARTA1){

        }
        if(personalType==PersonalList.CARTA2){

        }
        if(personalType==PersonalList.CARTA3){

        }
        if(personalType==PersonalList.CARTA4){

        }
        if(personalType==PersonalList.CARTA5){

        }
        if(personalType==PersonalList.CARTA6){

        }
        if(personalType==PersonalList.CARTA7){

        }
        if(personalType==PersonalList.CARTA8){

        }
        if(personalType==PersonalList.CARTA9){

        }
        if(personalType==PersonalList.CARTA10){

        }
        if(personalType==PersonalList.CARTA11){

        }
        if(personalType==PersonalList.CARTA12){

        }



    }




}

