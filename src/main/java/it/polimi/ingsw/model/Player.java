package it.polimi.ingsw.model;

/**
 * Class that represent the Player
 */
public class Player {
    private final Utils utils;
    private final String nickname;
    private final Library personalLibrary;
    private final CardPersonalTarget cardPersonalTarget;
    private int score;

    public String getNickname() {
        return nickname;
    }

    public Player(String nickname,CardPersonalTarget cardPersonalTarget){
        this.nickname=nickname;
        this.cardPersonalTarget=cardPersonalTarget;
        this.personalLibrary=new Library();
        this.utils=new Utils();
        this.score=0;
    }

    public void setScore(int addedScore){
        this.score += addedScore;
    }

    public void addTilesInLibrary(int col,Tile[] selectedTile) throws NoSpaceInColumnException, FullColumnException {
        personalLibrary.addCardInColumn(col, selectedTile);
    }


    public boolean checkCommonTarget(){
        return utils.checkCommonTarget(personalLibrary);
    }

    /**
     * method that calls the checkPersonalTarget of utils,
     * and transforms the value returned by the latter into points, finally adds these points to score
     */
    public void checkPersonalTarget(){
        int[] points = {0,1,2,4,6,9,12};
        this.score += points[utils.checkPersonalTarget(personalLibrary, cardPersonalTarget)];
    }


}


