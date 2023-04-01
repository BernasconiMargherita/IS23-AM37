package it.polimi.ingsw.model;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.Utils.Utils;
import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.CommonCards.CommonDeck;
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
    private PersonalDeck personalDeck;
    private List<Player> players;
    private GameState gameState;
    private boolean isLastTurn;
    private Utils utils;
    private CommonDeck commonDeck;

    public Game() throws SoldOutTilesException, FileNotFoundException {
        this.players= new ArrayList<>();
        this.gameState = GameState.WAITING_PLAYERS;
        this.isLastTurn = false;
        this.utils= new Utils();
    }



    public void addPlayer(Player player) {
        if(gameState==GameState.IN_GAME) throw new GameAlreadyStarted("It is not possible to add a player when the game has already started");
        if (players.size()==MAX_PLAYERS) throw new MaxPlayerException("There are already 4 players");
        players.add(player);
    }


    public boolean isGameReadyToStart(){
        return (players.size()>1 && players.size()<5);

    }

    public void GameInit(){
        commonDeck=new CommonDeck(players.size());
        personalDeck=new PersonalDeck(players.size());
        board=new Board(players.size());

        pickFirstPlayer();
        setGameState(GameState.GAME_INIT);

        for (int i=0;i< players.size();i++){
            players.get(i).setPersonalCard(personalDeck.getPersonalDeck().get(i));
        }

    }

    private void pickFirstPlayer() {
        int first = (new Random()).nextInt(players.size());
        players.get(first).setFirstPlayer();

        List<Player> playerList = new ArrayList<>();

        for (int i = first; i < players.size(); ++i) {
            playerList.add(players.get(i));
        }

        for (int i = 0; i < first; ++i) {
            playerList.add(players.get(i));
        }

        players = playerList;
    }

    public void placeInShelf(Coordinates[] positions, Player currentPlayer, int selectedColumn){
        try {
            Tile[] removedTile = board.removeCardFromBoard(positions);
            currentPlayer.addTilesInLibrary(selectedColumn,removedTile);

        } catch (EmptySlotException | InvalidSlotException | InvalidPositionsException | NoSpaceInColumnException e) {
            throw new RuntimeException(e);
        }
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

    public Board getBoard() {
        return board;
    }

    public PersonalDeck getPersonalDeck() {
        return personalDeck;
    }

    public CommonDeck getCommonDeck() {
        return commonDeck;
    }
}

