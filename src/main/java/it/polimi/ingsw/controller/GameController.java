package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState;
import it.polimi.ingsw.model.Player.Player;

import java.util.List;

public class GameController {
    private GameState gameState;
    private List<Player> players;
    private final Game game;
    private int turnChanger;
    private Player currentPlayer;

    GameController(){
        this.game = new Game();
        this.turnChanger=0;
        this.gameState= game.getGameState();
    }

    private void loginCase(loginMessage message) {
        currentPlayer=players.get(turnChanger);
    }

    public void Login(String nickname) {
        if (!(gameState == GameState.WAITING_PLAYERS)) throw new RuntimeException("Game already began");
        if (nickname.equals(getPlayerByNickname(nickname))) throw new RuntimeException("Username already taken");
        Player player=new Player(nickname);

    }

    private void initGame() {
        if (!game.getGameState().equals(GameState.WAITING_PLAYERS)) throw new RuntimeException("Game already begun");
        if (!game.isGameReadyToStart()) throw new RuntimeException("Game is not ready");
        game.GameInit();
        players=game.getPlayers();
        currentPlayer=players.get(turnChanger);
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
