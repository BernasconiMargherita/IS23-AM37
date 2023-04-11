package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

public class GameController {
    private final GameState gameState;
    private final ArrayList<Player> players;
    int turnChanger;
    Player currentPlayer;

    public GameController(ArrayList<Player> players){
        Game game = new Game();
        this.players=players;
        this.turnChanger=0;
        this.gameState= game.getGameState();
        currentPlayer=players.get(turnChanger);
    }

    public void Login(String nickname) {
        if (!(gameState == GameState.WAITING_PLAYERS)) throw new RuntimeException("Game already began");
        if (nickname.equals(getPlayerByNickname(nickname))) throw new RuntimeException("Username already taken");
        Player player=new Player(nickname);

    }

    public void initCase(InitMessage message) {
    }

    public void inGameCase(InGameMessage message) {
    }

    public void endGameCase(EndGameMessage message) {
    }

    public void nextTurn(){
        turnChanger=(turnChanger+1)% players.size();
        currentPlayer=players.get(turnChanger);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getPlayerByNickname(String nickname) {
        for (Player player : players) {
            if (player.getNickname().equals(nickname)) return player.getNickname();
        }
        return null;
    }


}
