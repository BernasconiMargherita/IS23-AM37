package it.polimi.ingsw.model;


import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.Player.Player;

public class Controller {


    private Game game;




    public void startGame(){
        game = new Game();
    }




    public void addPlayers(Player[] players){
        for (Player player : players) {
            game.addPlayer(player);
        }
    }




    public void initGame(){
        if(game.isGameReadyToStart()){
            game.GameInit();
        }
    }





    public void placeInShelf(Player currentPlayer, Coordinates[] position, int selectedColumn){
        game.placeInShelf(currentPlayer, position, selectedColumn);
    }


}
