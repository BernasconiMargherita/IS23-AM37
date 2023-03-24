package it.polimi.ingsw.model;

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

    public void checkPersonalTarget(){
        int checks=utils.checkPersonalTarget(personalLibrary,cardPersonalTarget);
        switch (checks){
            case 0: setScore(0);
            case 1: setScore(1);
            case 2: setScore(2);
            case 3: setScore(3);
            case 4: setScore(6);
            case 5: setScore(9);
            case 6: setScore(12);
        }

    }

    public boolean checkCommonTarget(){
        utils.checkCommonTarget();
    }
}
