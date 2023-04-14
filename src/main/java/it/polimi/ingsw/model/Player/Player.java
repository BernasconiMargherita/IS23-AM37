package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.Exception.NoSpaceInColumnException;
import it.polimi.ingsw.Utils.Utils;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;
import it.polimi.ingsw.model.Tile.Tile;

/**
 * Class that represent the Player
 */
public class Player {
    private final String nickname;
    private final Shelf personalShelf;
    private CardPersonalTarget cardPersonalTarget;
    private int score;
    private final boolean[] completedCommon;
    private boolean isFirstPlayer=false;
    private final Utils utils;


    public String getNickname() {
        return nickname;
    }

    /**
     * Constructor that assigns to the player a nickname, personalTarget and a boolean indicating if the player is the first one
     */
    public Player(String nickname){
        this.utils=new Utils();
        this.nickname=nickname;
        this.personalShelf =new Shelf();
        this.score=0;
        this.completedCommon = new boolean[]{false, false};
    }

    /**
     * Method that stores and modifies score value
     */
    public void addScore(int addedScore){
        this.score += addedScore;
    }

    /**
     * Method that allows the player to add the selected tiles from the board to the library
     * @param col chosen column
     * @param selectedTile array of selected tiles
     */
    public void addTilesInLibrary(int col, Tile[] selectedTile) throws NoSpaceInColumnException {
        personalShelf.addCardInColumn(col, selectedTile);
    }


    /**
     * method that calls the checkPersonalTarget of utils,
     * and transforms the value returned by the latter into points, finally adds these points to score
     */
    public void checkPersonalTarget(){
        int[] points = {0,1,2,4,6,9,12};
        int check=utils.checkPersonalTarget(personalShelf, cardPersonalTarget);
        if (check>0) {
            this.score += points[check];
        }
    }

    public void setPersonalCard(CardPersonalTarget personalCard) {
        this.cardPersonalTarget=personalCard;
    }

    public void setFirstPlayer(){
        this.isFirstPlayer=true;
    }
    public boolean isFirstPlayer() {
        return isFirstPlayer;
    }

    public boolean isShelfFull(){
        return personalShelf.isFull();
    }

    public Shelf getPersonalShelf() {
        return personalShelf;
    }

    public void setCompleted(int objective){
        completedCommon[objective]=true;
    }

    public boolean isCompleted(int objective){
        return completedCommon[objective];
    }

    public int getScore() {
        return score;
    }

    public void groupScore() {
        utils.groupScore(personalShelf);
    }
}


