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

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * class that manage the logic of the game, receiving messages from the controller to evolve the game
 */
public class Game {
    public static int MAX_PLAYERS=4;
    public static int MIN_PLAYERS=2;
    private Board board;
    private ArrayList<Player> players;
    private GameState gameState;
    private boolean isLastTurn;
    private final Utils utils;
    private ArrayList<CardCommonTarget> commonDeck;

    /**
     * Constructor of The Game class
     */
    public Game() {
        this.players= new ArrayList<>();
        this.gameState = GameState.WAITING_PLAYERS;
        this.isLastTurn = false;
        this.utils= new Utils();
    }

    /**
     * Method for adding a new player in the players array
     * @param player the new player to add
     */
    public void addPlayer(Player player) {
        if(gameState==GameState.IN_GAME) throw new GameAlreadyStarted("It is not possible to add a player when the game has already started");
        if (players.size()==MAX_PLAYERS) throw new MaxPlayerException("There are already 4 players so "+ player.getNickname()+" cannot be added");
        players.add(player);
    }

    /**
     * method to check if there are sufficient players to start the game
     */
    public boolean isGameReadyToStart(){
        return ( (players.size() > MIN_PLAYERS - 1)  &&  (players.size() < MAX_PLAYERS + 1) );

    }

    /**
     * method to initialize effectively the Game, knowing the number of players, also chose a first player to start the game
     */
    public void GameInit(){
        commonDeck=new CommonDeck(players.size()).getCommonDeck();
        ArrayList<CardPersonalTarget> personalDeck = new PersonalDeck(players.size()).getPersonalDeck();
        board=new Board(players.size());

        pickFirstPlayer();
        setGameState(GameState.GAME_INIT);

        for (int i=0;i< players.size();i++){
            players.get(i).setPersonalCard(personalDeck.get(i));
        }

    }

    /**
     * method to randomly select a player to start the turn and redefine the turns order
     */
    private void pickFirstPlayer() {
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
     * @return a boolean for confirm
     */
    public boolean refillBoard(){
        if (board.refillIsNecessary()) {
            try {
                board.refillBoard();
                return true;
            } catch (SoldOutTilesException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * Method that manages the removing of the selected Tiles of currentPlayer from the board and places them in the shelf in the selected column
     * @param currentPlayer the player that is currently playing his turn
     * @param positions array of the selected tiles coordinates
     * @param selectedColumn the selected column in which the player wants to place the tiles
     */
    public void placeInShelf(Player currentPlayer, Coordinates[] positions, int selectedColumn) {
        Tile[] removedTile;
        try {

            removedTile = board.removeCardFromBoard(positions);

        } catch (EmptySlotException | InvalidSlotException | InvalidPositionsException e) {

            throw new RuntimeException("Error"); //chiedere quando è meglio usare un exception unchecked o checked;
        }

        try {

            currentPlayer.addTilesInLibrary(selectedColumn, removedTile);

        } catch (NoSpaceInColumnException e) {
            throw new RuntimeException("Error");
        }
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
     * @param currentPlayer the player that is currently playing his turn
     * @return a boolean for confirm
     */
    public boolean checkPersonalTarget(Player currentPlayer){
        return currentPlayer.checkPersonalTarget();
    }

    /**
     * method that checks if the player shelf is full,and if it is, begins the last turn and adds the score of the EndGameToken to the player score
     * @param currentPlayer The player currently playing
     * @return boolean for confirm
     */
    public boolean isShelfFull(Player currentPlayer){
        if(currentPlayer.isShelfFull()){
            setLastTurn(true);
            currentPlayer.addScore(1);
            return true;
        }
        return false;
    }


    public List<Player> getPlayers() {
        return players;
    }

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

