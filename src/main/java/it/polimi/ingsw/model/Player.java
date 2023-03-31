package it.polimi.ingsw.model;

/**
 * Class that represent the Player
 */
public class Player {
    private final String nickname;
    private final Library personalLibrary;
    private final CardPersonalTarget cardPersonalTarget;
    private int score;

    private final boolean isFirstPlayer;
    private final Utils utils;



    public String getNickname() {
        return nickname;
    }

    /**
     * Constructor that assigns to the player a nickname, personalTarget and a boolean indicating if the player is the first one
     * @param nickname
     * @param cardPersonalTarget
     * @param isFirstPlayer tells if this player is the first one
     */
    public Player(String nickname, CardPersonalTarget cardPersonalTarget, boolean isFirstPlayer){
        this.utils=new Utils();
        this.nickname=nickname;
        this.cardPersonalTarget=cardPersonalTarget;
        this.isFirstPlayer = isFirstPlayer;
        this.personalLibrary=new Library();
        this.score=0;
    }

    /**
     * Method that stores and modifies score value
     * @param addedScore
     */
    public void setScore(int addedScore){
        this.score += addedScore;
    }

    /**
     * Method that allows the player to add the selected tiles from the board to the library
     * @param col chosen column
     * @param selectedTile array of selected tiles
     * @throws NoSpaceInColumnException
     */
    public void addTilesInLibrary(int col,Tile[] selectedTile) throws NoSpaceInColumnException {
        personalLibrary.addCardInColumn(col, selectedTile);
    }


    /**
     * method that calls the checkPersonalTarget of utils,
     * and transforms the value returned by the latter into points, finally adds these points to score
     */
    public void checkPersonalTarget(){
        int[] points = {0,1,2,4,6,9,12};
        this.score += points[utils.checkPersonalTarget(personalLibrary, cardPersonalTarget)];
    }


    public boolean isFirstPlayer() {
        return isFirstPlayer;
    }
}


