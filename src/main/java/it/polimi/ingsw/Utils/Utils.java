package it.polimi.ingsw.Utils;

import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;
import it.polimi.ingsw.model.Player.Shelf;
import it.polimi.ingsw.model.Tile.ColourTile;

import java.util.HashSet;

public class Utils {
    public static final int MAX_SHELF_COLUMNS = 5;
    private static final int MAX_SHELF_ROWS = 6;

    /**
     * method that compares the player's shelf and the personalCard, and returns the number of the completed objectives (0,...,6)
     * the if statement is true if the shelf's TileSlot is NOT EMPTY and true if the tile in the TileSlot is the same color as the Personal Card (in position i)
     * @param shelf Shelf of the player
     * @param cardPersonalTarget his personal target card
     * @return completedGoals
     */


    public int checkPersonalTarget(Shelf shelf, CardPersonalTarget cardPersonalTarget){
        int completedGoals = 0;


        for(int i = 0; i < 6 ; i++)

            if (!(shelf.getShelf()[cardPersonalTarget.personalCardTiles()[i].coordinates().getX()][cardPersonalTarget.personalCardTiles()[i].coordinates().getY()].isFree()) && cardPersonalTarget.personalCardTiles()[i].colourTile() == shelf.getShelf()[cardPersonalTarget.personalCardTiles()[i].coordinates().getX()][cardPersonalTarget.personalCardTiles()[i].coordinates().getY()].getAssignedTile().getColour()) {
                completedGoals++;
            }

        return completedGoals;
    }
    public boolean checkCommonTarget(Shelf shelf, CardCommonTarget commonCard) {

        TileSlot[][] shelfMatrix = shelf.getShelf();
        switch (commonCard.getCommonType()) {
            case SIX_GROUPS_OF_TWO -> {
                TileSlot[][] copy = shelfMatrix.clone();
                int found=0;
                for(int i=0; i<5; i++){
                    for(int j=0; j<4; j++){
                        if(checkGroupsOfTwo(copy[i][j],copy[i][j+1])){
                            found++;
                            if(found==6) return true;
                        }
                        if (checkGroupsOfTwo(copy[i][j],copy[i+1][j])){
                            found++;
                            if(found==6) return true;
                        }

                    }
                } for(int i=0; i<5; i++){
                        if(checkGroupsOfTwo(copy[i][4],copy[i+1][4])){
                            found++;
                            if(found==6) return true;
                        }

                }
                    for(int j=0; j<4; j++){
                        if (checkGroupsOfTwo(copy[5][j],copy[5][j+1])){
                            found++;
                            if(found==6) return true;
                        }
                    }
                return false;


            }
            case FOUR_EQUALS_ANGLES -> {
                if ((!shelfMatrix[0][0].isFree()) && (!shelfMatrix[MAX_SHELF_ROWS - 1][0].isFree()) && (!shelfMatrix[MAX_SHELF_ROWS - 1][MAX_SHELF_COLUMNS - 1].isFree()) && (!shelfMatrix[0][MAX_SHELF_COLUMNS - 1].isFree())) {
                    return (shelfMatrix[0][0].getAssignedTile().getColour() == shelfMatrix[MAX_SHELF_ROWS - 1][0].getAssignedTile().getColour()) && (shelfMatrix[0][MAX_SHELF_COLUMNS - 1].getAssignedTile().getColour() == shelfMatrix[MAX_SHELF_ROWS - 1][MAX_SHELF_COLUMNS - 1].getAssignedTile().getColour())
                            && (shelfMatrix[0][MAX_SHELF_COLUMNS - 1].getAssignedTile().getColour() == shelfMatrix[MAX_SHELF_ROWS - 1][0].getAssignedTile().getColour());
                }
            }

            case FOUR_GROUPS_OF_FOUR -> {
                TileSlot[][] copy = shelfMatrix.clone();
                int found=0;
                for(int i=0; i<3; i++){
                    for(int j=0; j<2; j++){
                        if(checkGroupsOfFour(copy[i][j],copy[i][j+1],copy[i][j+2],copy[i][j+3])){
                            found++;
                            if(found==4) return true;
                        }
                        if (checkGroupsOfFour(copy[i][j],copy[i+1][j],copy[i+2][j],copy[i+3][j])){
                            found++;
                            if(found==4) return true;
                        }

                    }
                } for(int i=3; i<6; i++){
                    for(int j=0; j<2; j++){
                        if(checkGroupsOfFour(copy[i][j],copy[i][j+1],copy[i][j+2],copy[i][j+3])){
                            found++;
                            if(found==4) return true;
                        }
                    }
                } for(int i=0; i<3; i++){
                    for(int j=2; j<5; j++){
                        if (checkGroupsOfFour(copy[i][j],copy[i+1][j],copy[i+2][j],copy[i+3][j])){
                            found++;
                            if(found==4) return true;
                        }
                    }
                }
                return false;
            }

            case TWO_GROUPS_IN_SQUARE -> {
                HashSet<ColourTile> differentColours= new HashSet<ColourTile>();
                TileSlot[][] copy = shelfMatrix.clone();
                int found=0;

                for (int i = 0; i < MAX_SHELF_ROWS - 1; i++) {
                    for (int j = 0; j < MAX_SHELF_COLUMNS - 1; j++) {
                        if (checkGroupsOfFour(copy[i][j], copy[i+1][j], copy[i][j+1], copy[i+1][j+1])) {
                            if(differentColours.contains(shelfMatrix[i][j].getAssignedTile().getColour())) return true;
                            else differentColours.add(shelfMatrix[i][j].getAssignedTile().getColour());
                        }
                    }
                }
                return false;
            }

            case THREE_COLUMNS_THREE_DIFFERENT_TYPES -> {
                int found = 0;
                for(int i = 0; i < MAX_SHELF_COLUMNS; i++){
                    TileSlot[] temp = new TileSlot[MAX_SHELF_ROWS];
                    for(int j = 0; j < MAX_SHELF_ROWS; j++){
                        temp[j] = shelfMatrix[j][i];
                    }
                    if (checkAllDifferent(temp, "COLUMN") < 4 ){
                        found++;
                    }
                }
                if(found>2) {
                    return true;
                }

            }
            case EIGHT_EQUALS -> {
                int count = 0;

                    for (int x = 0; x < MAX_SHELF_ROWS; x++) {
                        for (int i = 0; i <MAX_SHELF_COLUMNS ; i++) {
                            for (int j = i+1; j < MAX_SHELF_COLUMNS; j++) {
                                if (shelfMatrix[x][i].getAssignedTile().getColour() == shelfMatrix[x][j].getAssignedTile().getColour()) {
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
                return ((checkDiagonal(shelfMatrix,firstDiagonal,1,1))||(checkDiagonal(shelfMatrix,secondDiagonal,1,1))||(checkDiagonal(shelfMatrix,thirdDiagonal,-1,-1))||(checkDiagonal(shelfMatrix,fourthDiagonal,-1,-1)));
            }
            case FOUR_ROWS_THREE_DIFFERENT_TYPES -> {
                int found = 0;
                for(int i = 0; i < MAX_SHELF_ROWS; i++){
                    if (checkAllDifferent(shelfMatrix[i], "ROW") < 4){
                        found++;
                    }
                }
                if(found>3){
                    return true;
                }


            }


            case TWO_COLUMNS_ALL_DIFFERENT -> {
                int found = 0;
                for(int i = 0; i < MAX_SHELF_COLUMNS; i++){
                    TileSlot[] temp = new TileSlot[MAX_SHELF_ROWS];
                    for(int j = 0; j < MAX_SHELF_ROWS; j++){
                        temp[j] = shelfMatrix[j][i];
                    }
                    if (checkAllDifferent(temp, "COLUMN") == MAX_SHELF_ROWS ){
                        found++;
                    }
                }
                if(found>1) {
                    return true;
                }


            }
            case TWO_ROWS_ALL_DIFFERENT -> {
                int found = 0;
                for(int i = 0; i < MAX_SHELF_ROWS; i++){
                    if (checkAllDifferent(shelfMatrix[i], "ROW") == MAX_SHELF_COLUMNS){
                        found++;
                    }
                }
                if(found>1){
                    return true;
                }
            }



            case FIVE_IN_A_X -> {

                    for(int i=1; i<4; i++){
                        for(int j=1; j<MAX_SHELF_COLUMNS; j++){
                            if (shelfMatrix[i][j].getAssignedTile().getColour() == shelfMatrix[i + 1][j + 1].getAssignedTile().getColour()
                                    && shelfMatrix[i][j].getAssignedTile().getColour() == shelfMatrix[i - 1][i - 1].getAssignedTile().getColour()
                                    && shelfMatrix[i][j].getAssignedTile().getColour() == shelfMatrix[i - 1][i + 1].getAssignedTile().getColour()
                                    && shelfMatrix[i][j].getAssignedTile().getColour() == shelfMatrix[i + 1][i - 1].getAssignedTile().getColour()) {
                                return true;
                            }
                        }
                    }


                    return false;


                }
            case IN_DESCENDING_ORDER -> {

            int first= shelfMatrix[0].length< shelfMatrix[4].length ? 0 : 4 ;

            if (first==0){
                for (int i=0;i<MAX_SHELF_COLUMNS;i++){
                    if((shelfMatrix[i].length)+1 != shelfMatrix[i+1].length) return false;
                }
            }
            else {
                for (int i = 4; i >= 0; i--){
                    if((shelfMatrix[i].length)+1 != shelfMatrix[i-1].length) return false;
                }
            }
            return true;
            }

            default -> throw new IllegalStateException("Unexpected value: " + commonCard.getCommonType());
        }
        return false;
    }

//Idea:rendere più generica la funzione per utilizzarla anche per FOUR_ROWS_THREE_DIFFERENT_TYPES e THREE_COLUMNS_THREE_DIFFERENT_TYPES
// (per esempio potrebbe ritornare il numero di tessere diverse, e poi il controllo specifico sarebbe nei vari case)

    public int checkAllDifferent(TileSlot[] shelfMatrix, String type) {

        int numColours = 0;

        HashSet<ColourTile> differentColours= new HashSet<ColourTile>();

        if (type.equals("ROW")) {

            for (int i = 0; i < MAX_SHELF_COLUMNS; i++) {
                    if(shelfMatrix[i].isFree()) return 0;
                    differentColours.add(shelfMatrix[i].getAssignedTile().getColour());
            }

            numColours = differentColours.size();

            return numColours;

        }



        if(type.equals("COLUMN")){

            for (int i = 0; i < MAX_SHELF_ROWS; i++) {
                if(shelfMatrix[i].isFree()) return 0;
                differentColours.add(shelfMatrix[i].getAssignedTile().getColour());
            }

            numColours = differentColours.size();

            return numColours;

        }

        return 0;
    }


    public boolean checkDiagonal(TileSlot[][] shelfMatrix, Coordinates coordinates, int k, int h){
        int j = coordinates.getY();
        int count = 0;

        for(int i= coordinates.getX(); i<MAX_SHELF_COLUMNS-1; i+=k) {

                if (shelfMatrix[i][j].getAssignedTile().getColour() == shelfMatrix[i + k][j + h].getAssignedTile().getColour()) {
                    j += k;
                    count++;
                }
            }


    return (count + 1) == MAX_SHELF_COLUMNS;}



    public boolean checkGroupsOfFour(TileSlot tileSlot1, TileSlot tileSlot2, TileSlot tileSlot3, TileSlot tileSlot4){
        if(!(tileSlot1.isFree()) &&  !(tileSlot2.isFree()) && !(tileSlot3.isFree()) && !(tileSlot4.isFree())&&
                tileSlot1.getAssignedTile().getColour()==tileSlot2.getAssignedTile().getColour() &&
                tileSlot1.getAssignedTile().getColour()== tileSlot3.getAssignedTile().getColour() &&
                tileSlot1.getAssignedTile().getColour() == tileSlot4.getAssignedTile().getColour()){

            tileSlot1.removeAssignedTile();
            tileSlot2.removeAssignedTile();
            tileSlot3.removeAssignedTile();
            tileSlot4.removeAssignedTile();
            return true;
        }
        return false;
    }

    public boolean checkGroupsOfTwo(TileSlot tileSlot1, TileSlot tileSlot2){
        if(!(tileSlot1.isFree()) &&  !(tileSlot2.isFree()) &&
                tileSlot1.getAssignedTile().getColour()==tileSlot2.getAssignedTile().getColour()){

            tileSlot1.removeAssignedTile();
            tileSlot2.removeAssignedTile();
            return true;
        }
        return false;
    }

    public int groupScore(Shelf shelf){
        TileSlot[][] shelfMatrix = shelf.getShelf();
        TileSlot[][] copy = shelfMatrix.clone();
        int match=0;
        for (int i=0;i<MAX_SHELF_ROWS;i++){
            for (int j=0;j<MAX_SHELF_COLUMNS;j++){
                ColourTile colour;
                colour=copy[i][j].getAssignedTile().getColour();

                for (int k=i;k<MAX_SHELF_ROWS;k++){
                    for (int h=j;h<MAX_SHELF_COLUMNS;h++){
                        match++;
                    }
                }
            }
        }

        return match;
    }

}

