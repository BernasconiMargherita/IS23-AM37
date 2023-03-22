package it.polimi.ingsw.model;

public class Utils {

    /**
     * method that compares the player's library and the personalCard, and returns the number of the completed objectives (0,...,6)
     * @param library
     * @param cardPersonalTarget
     * @return completedGoals
     */


    public int checkPersonalTarget(Library library, CardPersonalTarget cardPersonalTarget){
        int completedGoals = 0;


        for(int i = 0; i < 6 ; i++)
            if (!(library.getLibrary()[cardPersonalTarget.getPersonalCardTiles()[i].getCoordinates().getX()][cardPersonalTarget.getPersonalCardTiles()[i].getCoordinates().getY()].isFree()) && cardPersonalTarget.getPersonalCardTiles()[i].getColourTile() == library.getLibrary()[cardPersonalTarget.getPersonalCardTiles()[i].getCoordinates().getX()][cardPersonalTarget.getPersonalCardTiles()[i].getCoordinates().getY()].getAssignedTile().getColour()) {
                completedGoals++;
            }

        return completedGoals;
    }


    public boolean checkCommonTarget(Library library,CardCommonTarget commonCard){

        return false;
    }

    /**
     * Method for link a personal card to an array of Coordinates and TileColour,used for check later if the player has reached his personal target
     * @param personalType one of the element of PersonalList enum
     */
    public PersonalCardTile[] assignPersonalCard(PersonalList personalType){
        PersonalCardTile[] personalCardTiles = new PersonalCardTile[6];
        switch (personalType){
            case CARTA1 -> {
                personalCardTiles[0]=new PersonalCardTile(new Coordinates(0,0),ColourTile.CATS); //CATS
                personalCardTiles[1]=new PersonalCardTile(new Coordinates(4,1),ColourTile.GAMES);//giallo
                personalCardTiles[2]=new PersonalCardTile(new Coordinates(3,2),ColourTile.TROPHIES);//azzurro
                personalCardTiles[3]=new PersonalCardTile(new Coordinates(2,3),ColourTile.FRAMES);//FRAMES
                personalCardTiles[4]=new PersonalCardTile(new Coordinates(1,4),ColourTile.PLANTS);//PLANTS
                personalCardTiles[5]=new PersonalCardTile(new Coordinates(2,5),ColourTile.BOOKS);//bianco
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

