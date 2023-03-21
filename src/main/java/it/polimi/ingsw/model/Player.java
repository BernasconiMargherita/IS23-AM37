package it.polimi.ingsw.model;

public class Player {
    private String nickname;

    public Player(String nickname){
        this.nickname=nickname;
    }
    public void addTilesinLibrary( Library library, int row, int column) {
        library.addCardInColumn(row, column, 1);
    }

    /** metodo completamente da modificare
     *
     * @param library
     * @param cardCommonTarget
     * @return
     */
    public boolean checkCommonTarget( Library library, CardCommonTarget cardCommonTarget){
    }
}
