package it.polimi.ingsw.model;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.Utils.Utils;
import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.CommonCards.CommonDeck;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;
import it.polimi.ingsw.model.PersonalCards.PersonalDeck;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * class that manage the logic of the game, receiving messages from the controller to evolve the game
 */
public class Game implements Serializable {
    private Board board;
    private GameState gameState = GameState.WAITING_PLAYERS;
    private boolean isLastTurn;
    private final Utils utils;
    private ArrayList<CardCommonTarget> commonDeck;


    /**
     * Constructor of The Game class
     */
    public Game() {
        this.isLastTurn = false;
        this.utils= new Utils();
    }



    /**
     * method to initialize effectively the Game, knowing the number of players, also chose a first player to start the game
     */
    public void GameInit(List<Player> players){
        commonDeck = new CommonDeck(players.size()).getCommonDeck();
        ArrayList<CardPersonalTarget> personalDeck = new PersonalDeck(players.size()).getPersonalDeck();
        board = new Board(players.size());

        pickFirstPlayer(players);
        setGameState(GameState.GAME_INIT);

        for (int i=0;i< players.size();i++){
            players.get(i).setPersonalCard(personalDeck.get(i));
        }

    }

    /**
     * method to randomly select a player to start the placeInShelf and redefine the turns order
     */
    private void pickFirstPlayer(List<Player> players) {
        int first = (new Random()).nextInt(players.size());
        players.get(first).setFirstPlayer();

        ArrayList<Player> playerList = new ArrayList<>();

        for (int i = first; i < players.size(); ++i) {
            playerList.add(players.get(i));
        }

        for (int i = 0; i < first; ++i) {
            playerList.add(players.get(i));
        }

        players = playerList;
    }

    /**
     * method to refill the Board if it's necessary
     */
    public void refillBoard() throws SoldOutTilesException {
        if (board.refillIsNecessary()) {
            board.refillBoard();
        }
    }
    /**
     * Method that manages the removing of the selected Tiles of currentPlayer from the board
     *
     * @param currentPlayer  the player that is currently playing his turn
     * @param positions      array of the selected tiles coordinates
     */
    public Tile[] remove(Player currentPlayer, Coordinates[] positions) throws InvalidPositionsException, EmptySlotException, InvalidSlotException {
        Tile[] removedTile;

        removedTile = board.removeCardFromBoard(positions);
        return removedTile;

    }

    /**
     * method that manages places them in the shelf in the selected column.
     * @param tilesToAdd tiles to add to the shelf
     * @param currentPlayer player currently playing
     * @param selectedColumn the selected column
     * @throws NoSpaceInColumnException throw when the colum has not enough space
     */
    public void addInShelf(Tile[] tilesToAdd,Player currentPlayer,int selectedColumn) throws NoSpaceInColumnException {
        currentPlayer.addTilesInLibrary(selectedColumn, tilesToAdd);
    }

    /**
     * Method that check if the player has completed the two Common objective and if it has already completed them before,
     * and proceeds to add the value of the ScoringToken to the player score, removing it from the card.
     * @param currentPlayer the player that is currently playing his turn
     */
    public void checkCommonTarget(Player currentPlayer) {
        for (CardCommonTarget cardCommonTarget : commonDeck) {
            if (!(currentPlayer.isCompleted(cardCommonTarget.getAssignedCommonCard()))&&(utils.checkCommonTarget(currentPlayer.getPersonalShelf(), cardCommonTarget))) {

                currentPlayer.setCompleted(cardCommonTarget.getAssignedCommonCard());
                currentPlayer.addScore(cardCommonTarget.getScoringToken());
            }
        }
    }

    /**
     * method that checks if the player ha completed one of his personal goal and then proceeds to add the corresponding score to the player score
     *
     * @param currentPlayer the player that is currently playing his turn
     */
    public void checkPersonalTarget(Player currentPlayer){
        currentPlayer.checkPersonalTarget();
    }

    /**
     * method that checks if the player shelf is full,and if it is, begins the last turn and adds the score of the EndGameToken to the player score
     *
     * @param currentPlayer The player currently playing
     */
    public void isShelfFull(Player currentPlayer){
        if(currentPlayer.isShelfFull()){
            setLastTurn(true);
            currentPlayer.addScore(1);
        }
    }

    /**
     * Method to choose the Winner of the Game based on the score
     * @return the winner Player
     */



    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isLastTurn() {
        return isLastTurn;
    }

    public void setLastTurn(boolean lastTurn) {
        isLastTurn = lastTurn;
    }






}

