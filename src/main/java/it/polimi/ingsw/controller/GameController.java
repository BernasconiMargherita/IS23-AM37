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

    GameController(ArrayList<Player> players){
        Game game = new Game();
        this.players=players;
        this.turnChanger=0;
        this.gameState= game.getGameState();
        currentPlayer=players.get(turnChanger);
    }

    private void onMessage(Message message){
        switch (gameState){

            case WAITING_PLAYERS -> {
                loginCase((loginMessage) message);
            }
            case GAME_INIT -> {
                initCase((initMessage) message);
            }
            case IN_GAME -> {
                inGameCase((InGameMessage)message);
            }
            case END_GAME -> {
                endGameCase((endGameMessage) message);
            }
        }
    }

    private void loginCase(loginMessage message) {

    }

    private void initCase(initMessage message) {
    }

    private void inGameCase(InGameMessage message) {
    }

    private void endGameCase(endGameMessage message) {
    }

    private void nextTurn(){
        turnChanger=(turnChanger+1)% players.size();
        currentPlayer=players.get(turnChanger);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


}
