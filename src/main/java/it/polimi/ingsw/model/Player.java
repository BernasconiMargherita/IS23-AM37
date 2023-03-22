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
    public void addTilesInLibrary(int col,Tile[] selectedTile) throws NoSpaceInColumnException, FullColumnException {
        personalLibrary.addCardInColumn(col, selectedTile);
    }

    public boolean checkCommonTarget(){
        utils.checkCommonTarget();
    }
}
