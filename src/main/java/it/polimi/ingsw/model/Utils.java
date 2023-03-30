package it.polimi.ingsw.model;

import static it.polimi.ingsw.model.CommonList.IN_DESCENDING_ORDER;

public class Utils {


    /**
     * method that compares the player's library and the personalCard, and returns the number of the completed objectives (0,...,6)
     * the if statement is true if the library's TileSlot is NOT EMPTY and true if the tile in the TileSlot is the same color as the Personal Card (in position i)
     * @param library Library of the player
     * @param cardPersonalTarget his personal target card
     * @return completedGoals
     */


    public int checkPersonalTarget(Library library, CardPersonalTarget cardPersonalTarget){
        int completedGoals = 0;


        for(int i = 0; i < 6 ; i++)

            if (!(library.getLibrary()[cardPersonalTarget.getPersonalCardTiles()[i].coordinates().getX()][cardPersonalTarget.getPersonalCardTiles()[i].coordinates().getY()].isFree()) && cardPersonalTarget.getPersonalCardTiles()[i].colourTile() == library.getLibrary()[cardPersonalTarget.getPersonalCardTiles()[i].coordinates().getX()][cardPersonalTarget.getPersonalCardTiles()[i].coordinates().getY()].getAssignedTile().getColour()) {
                completedGoals++;
            }

        return completedGoals;
    }
    public boolean checkCommonTarget(Library library,CardCommonTarget commonCard) {

        TileSlot[][] libraryMatrix = library.getLibrary();
        switch (commonCard.getCommonType()) {
            case SIX_GROUPS_OF_TWO -> {
            }
            case FOUR_EQUALS_ANGLES -> {
                if (libraryMatrix[0][5].getAssignedTile().getColour() == libraryMatrix[0][0].getAssignedTile().getColour())
                    if ((libraryMatrix[0][5].getAssignedTile().getColour() == libraryMatrix[5][4].getAssignedTile().getColour()) && (libraryMatrix[0][4].getAssignedTile().getColour() == libraryMatrix[0][5].getAssignedTile().getColour())) {
                        return true;

                    }
            }


            case FOUR_GROUPS_OF_FOUR -> {
            }
            case TWO_GROUPS_IN_SQUARE -> {
            }
            case THREE_FULL_COLUMNS_WITH_MAX_THREE_DIFFERENT_TYPES -> {
            }
            case EIGHT_EQUALS -> {

                int count = 0;

                    for (int x = 0; x < 6; x++) {
                        for (int i = 0; i <5 ; i++) {
                            for (int j = i+1; j < 5; j++) {
                                if (libraryMatrix[x][i].getAssignedTile().getColour() == libraryMatrix[x][j].getAssignedTile().getColour()) {
                                    count++;
                                }

                            }
                        }
                    }

                return count >= 8;

            }

            case FIVE_IN_DIGONAL -> {
                Coordinates firstDiagonal=new Coordinates(0,0);
                Coordinates secondDiagonal=new Coordinates(0,1);
                Coordinates thirdDiagonal=new Coordinates(0,4);
                Coordinates fourthDiagonal=new Coordinates(0,5);
                return ((checkDiagonal(libraryMatrix,firstDiagonal,1,1))||(checkDiagonal(libraryMatrix,secondDiagonal,1,1))||(checkDiagonal(libraryMatrix,thirdDiagonal,-1,-1))||(checkDiagonal(libraryMatrix,fourthDiagonal,-1,-1)));
            }
            case FOUR_FULL_ROWS_WITH_MAX_THREE_DIFFERENT_TYPES -> {

            }
            case TWO_FULL_COLUMNS_ALL_DIFFERENT -> {
                return checkAllDifferent(libraryMatrix, "COLUMN");
            }
            case TWO_FULL_ROWS_ALL_DIFFERENT -> {
                return checkAllDifferent(libraryMatrix,"ROW");
            }
            case FIVE_IN_A_X -> {

                    for(int i=1; i<4; i++){
                        for(int j=1; j<5; j++){
                            if (libraryMatrix[i][j].getAssignedTile().getColour() == libraryMatrix[i + 1][j + 1].getAssignedTile().getColour()
                                    && libraryMatrix[i][j].getAssignedTile().getColour() == libraryMatrix[i - 1][i - 1].getAssignedTile().getColour()
                                    && libraryMatrix[i][j].getAssignedTile().getColour() == libraryMatrix[i - 1][i + 1].getAssignedTile().getColour()
                                    && libraryMatrix[i][j].getAssignedTile().getColour() == libraryMatrix[i + 1][i - 1].getAssignedTile().getColour()) {
                                return true;
                            }
                        }
                    }


                    return false;


                }
            case IN_DESCENDING_ORDER -> {
            }

            }


        }



    public boolean checkAllDifferent(TileSlot[][] libraryMatrix, String Type) {

        int found = 0;
        int h=0;
        int count=0;

        if(Type.equals("ROW")){

            while (found != 2 && h<6) {
                for (int i = 0; i < 5; i++) {
                    for (int j = i + 1; j < 5; j++) {
                        if(libraryMatrix[h][i].getAssignedTile().getColour()!= libraryMatrix[h][j].getAssignedTile().getColour()){
                            count++;
                        }

                    }
                }
                if(count==5){
                    found++;
                }
                count=0;
                h++;

            }
            return found >= 2;
        }





        if(Type.equals("COLUMN")){

            while (found != 2 && h<6) {
                for (int i = 0; i < 5; i++) {
                    for (int j = i; j < 5; j++) {
                        if(libraryMatrix[i][h].getAssignedTile().getColour()!= libraryMatrix[j][h].getAssignedTile().getColour()){
                            count++;
                        }

                    }
                }
                if(count==6){
                    found++;
                }
                count=0;
                h++;
            }
            return found >= 2;
        }

        return false;
    }


    public boolean checkDiagonal(TileSlot[][] libraryMatrix, Coordinates coordinates, int k, int h){
        int j = coordinates.getY();
        int count = 0;
        for(int i= coordinates.getX(); i<5; i+=k){
            if (libraryMatrix[i][j].getAssignedTile().getColour() == libraryMatrix[i+k][j+h].getAssignedTile().getColour()) {
                j+=k;
                count++;
            }
        }
        return count == 5;
    }



    /**
     * Method for link a personal card to an array of Coordinates and TileColour,used for check later if the player has reached his personal target
     * @param personalType one of the element of PersonalList enum
     */
    public PersonalCardTile[] assignPersonalCard(PersonalList personalType){
        PersonalCardTile[] personalCardTiles = new PersonalCardTile[6];
        switch (personalType){
            case CARTA1 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(0,0),ColourTile.CATS);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(4,1),ColourTile.GAMES);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(3,2),ColourTile.TROPHIES);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(2,3),ColourTile.FRAMES);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(1,4),ColourTile.PLANTS);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(2,5),ColourTile.BOOKS);
            }
            case CARTA2 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(2,0),ColourTile.TROPHIES);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(1,2),ColourTile.GAMES);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(3,3),ColourTile.BOOKS);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(4,4),ColourTile.CATS);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(0,5),ColourTile.PLANTS);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(2,5),ColourTile.FRAMES);

            }
            case CARTA3 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(1,1),ColourTile.BOOKS);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(2,1),ColourTile.CATS);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(3,2),ColourTile.PLANTS);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(0,3),ColourTile.TROPHIES);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(2,3),ColourTile.FRAMES);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(4,5),ColourTile.GAMES);

            }
            case CARTA4 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(3,0),ColourTile.PLANTS);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(1,1),ColourTile.FRAMES);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(3,2),ColourTile.CATS);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(0,3),ColourTile.BOOKS);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(1,4),ColourTile.GAMES);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(4,5),ColourTile.TROPHIES);

            }
            case CARTA5 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(0,0),ColourTile.BOOKS);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(1,2),ColourTile.CATS);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(4,2),ColourTile.TROPHIES);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(2,3),ColourTile.PLANTS);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(0,4),ColourTile.FRAMES);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(3,4),ColourTile.GAMES);

            }
            case CARTA6 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(0,0),ColourTile.PLANTS);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(1,1),ColourTile.GAMES);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(3,1),ColourTile.FRAMES);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(3,3),ColourTile.BOOKS);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(2,5),ColourTile.TROPHIES);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(4,5),ColourTile.CATS);

            }
            case CARTA7 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(3,0),ColourTile.TROPHIES);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(4,1),ColourTile.CATS);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(2,2),ColourTile.FRAMES);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(0,3),ColourTile.GAMES);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(1,4),ColourTile.BOOKS);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(2,5),ColourTile.PLANTS);
            }
            case CARTA8 -> {
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(4,0),ColourTile.FRAMES);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(3,1),ColourTile.TROPHIES);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(4,2),ColourTile.BOOKS);
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(0,3),ColourTile.CATS);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(2,3),ColourTile.GAMES);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(1,4),ColourTile.PLANTS);

            }
            case CARTA9 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(0,0),ColourTile.FRAMES);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(1,1),ColourTile.TROPHIES);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(4,1),ColourTile.PLANTS);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(4,2),ColourTile.BOOKS);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(2,3),ColourTile.CATS);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(2,5),ColourTile.GAMES);
            }
            case CARTA10 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(0,0),ColourTile.GAMES);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(0,3),ColourTile.CATS);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(4,1),ColourTile.PLANTS);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(1,2),ColourTile.FRAMES);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(2,2),ColourTile.BOOKS);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(1,4),ColourTile.TROPHIES);
            }
            case CARTA11 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(0,2),ColourTile.BOOKS);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(4,1),ColourTile.GAMES);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(0,2),ColourTile.TROPHIES);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(1,3),ColourTile.PLANTS);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(3,4),ColourTile.FRAMES);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(0,5),ColourTile.CATS);
            }
            case CARTA12 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(3,0),ColourTile.GAMES);
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(3,1),ColourTile.BOOKS);
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(0,2),ColourTile.PLANTS);
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(2,3),ColourTile.TROPHIES);
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(1,4),ColourTile.CATS);
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(4,5),ColourTile.FRAMES);
            }
        }

        return personalCardTiles;

    }























}

