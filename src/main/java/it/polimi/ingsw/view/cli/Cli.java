package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Network2.ClientManager;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.CommonCards.CommonList;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;
import it.polimi.ingsw.model.Tile.ColourTile;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Scanner;

import static it.polimi.ingsw.view.cli.ColorCodes.getColorCode;

public class Cli extends ClientManager {
    ArrayList<Coordinates> tempCoordinates;

    private String protocol;
    private ColourTile[][] shelf = new ColourTile[6][5];
    private String message;
    private MyShelfiePrintStream out;
    private String username;
    private long UID;
    private int gameID;
    private CardPersonalTarget cardPersonalTarget;
    private ArrayList<CardCommonTarget> cardCommonTargets;
    private String winner;
    private ColourTile[][] board;
    private final String ANSI_RESET = "\u001B[0m";
    private CommonList cardCommonTarget0;
    private CommonList cardCommonTarget1;
    private int column;
    private int[] commonTokens;
    private boolean endGameToken;
    private String[] colors;
    private Scanner in;
    private static final String TEXT_BLACK = "\u001B[30m";

    public Cli(Scanner scanner) {
        super();
        in = scanner;

    }


    public void start() {
        this.out = new MyShelfiePrintStream();
        out.println(("Welcome , choose your connection : "));
        protocol = in.nextLine();
        createConnection(protocol);
    }

    @Override
    public void createConnection(String connection) {
        if (protocol.equals("TCP") || protocol.equals("tcp")) {
            super.createConnection("TCP");

        }
        if (protocol.equals("RMI") || protocol.equals("rmi")) {
            super.createConnection("RMI");
        }
        UID = getClient().getUID();
        login();
        //ok
    }

    protected void login() {
        out.println("Choose your username\n");
        username = in.nextLine();
        getClient().sendMessage(new PreLoginMessage(-1, UID, username));

    }

    public void firstSetter(int gameID) {
        out.println("Choose number of players");
        int numPlayers = -1;
        while (numPlayers < 2 || numPlayers > 4) {
            numPlayers = in.nextInt();
            if (numPlayers < 2 || numPlayers > 4) {
                out.println("Number of players not valid, please enter a number between 2 and 4");
            }
        }
        getClient().sendMessage(new SetMessage(numPlayers, gameID, UID));
        //ok
    }

    @Override
    public void firstResponse(FirstResponse firstResponse) {
        gameID = firstResponse.getGameID();
        firstSetter(gameID);

    }

    @Override
    public void reFirstResponse(ReFirstResponse reFirstResponse) {
        gameID = reFirstResponse.getGameID();
        firstSetter(gameID);
        //ok
    }

    @Override
    public void setResponse(SetResponse setResponse) {
        out.println("Setting finished correctly\nWaiting...");

        //ok
    }

    @Override
    public void preLoginResponse(PreLoginResponse preLoginResponse) {
        out.println("Waiting for a game...");
        //ok
    }


    @Override
    public void loginResponse(LoginResponse loginResponse) {
        out.println("Successful login\nWaiting...");
        gameID = loginResponse.getGameID();
    }

    @Override
    public void usernameError(UsernameError usernameError) {
        gameID = usernameError.getGameID();
        out.println("Username already exists\nPlease enter new one");
        username = in.nextLine();
        getClient().sendMessage(new LoginMessage(username, gameID, UID));
        //ok
    }

    @Override
    public void initResponse(InitResponse initResponse) {

        out.println("The game is loading...");
        colors = new String[3];
        for (int i = 0; i < 3; i++) {
            colors[i] = ColourTile.FREE.toString();
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                shelf[i][j] = ColourTile.FREE;
            }
        }
        //ok
    }

    @Override
    public void cardsResponse(CardsResponse cardsResponse) {
        this.cardCommonTargets = cardsResponse.getCommonTargets();
        this.cardPersonalTarget = cardsResponse.getCardPersonalTarget();
        //ok

    }

    @Override
    public void wakeUp(WakeMessage wakeMessage) {
        for (int i = 0; i < 3; i++) {
            colors[i] = ColourTile.FREE.toString();
        }
        getClient().sendMessage(new BoardMessage(username, gameID, UID));
        Thread timerThread = new Thread(() -> startTimer());
        timerThread.start();
    }

    public void startTimer() {
        while (board == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        removeChoice();
        Thread.currentThread().interrupt();

    }

    public void displayRemove(boolean remove) {
        if (remove) {
            remove();
        }
    }

    public void displayTurn(boolean turn) {
        if (turn) {
            turn();
        }
    }

    public void turnChoice() {
        boolean loop = true;
        while (loop) {
            loop = display(false);
            displayTurn(!loop);
        }
    }

    public void removeChoice() {
        boolean loop = true;
        while (loop) {
            loop = display(true);
            displayRemove(!loop);
        }
    }


    public boolean display(boolean type) {
        out.println("What do you want to do?\n1: View common cards\n2: View personal card\n3: View board\n4: View endGameToken\n5: View shelf ");
        if (type) {
            out.println("6: Remove tiles from board");
        } else {
            out.println("6: Insert in column");
        }

        int num = -1;
        while (num <= 0 || num > 6) {
            num = in.nextInt();
            if (num <= 0 || num > 6) {
                out.println("Enter a valid number PLEASE");
            }
        }
        if (num == 1) {
            printCommonTargets(cardCommonTargets.get(0), cardCommonTargets.get(1));
            return true;
        } else if (num == 2) {
            printPersonalTargets(cardPersonalTarget);
            return true;
        } else if (num == 3) {
            printBoard(board);
            return true;
        } else if (num == 4) {
            if (endGameToken) {
                out.println("EndGameToken already taken");
            } else {
                out.println("EndGameToken still in game");
            }
            return true;
        } else if (num == 5) {
            printShelf(shelf);
            return true;
        } else return false;
    }


    public void remove() {
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        int freeColumnSpace = 0;
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (shelf[j][i] == ColourTile.FREE) {
                    count++;
                }
            }
            if (count > freeColumnSpace) {
                freeColumnSpace = count;
            }
            count = 0;
        }
        if(freeColumnSpace>3){
            freeColumnSpace = 3;
        }
        for (int i = 0; i < freeColumnSpace; i++) {
            if (i != 0) {
                out.println("Do you want to remove other cards?");
                if (in.nextLine().equals("no")) {
                    break;
                }
            }
            out.println("Insert the coordinates of the cards you want to remove, in order with respect to column insertion in the shelf :\ninsert row:\n");

            int x = in.nextInt();
            out.println("Insert column:");
            int y = in.nextInt();
            in.nextLine();
            while (true) {
                System.out.println("Are you sure? Answer yes or no");
                String no = in.nextLine();
                if (no.equals("no")) {
                    out.println("Do you want to change row or column?");
                    String change = in.nextLine();
                    if (change.equals("row")) {
                        out.println("Insert your new row");
                        x = in.nextInt();
                    } else {
                        out.println("Insert your new column");
                        y = in.nextInt();
                    }
                }
                if (no.equals("yes")) {
                    coordinates.add(new Coordinates(x, y));
                    break;
                }
            }
            colors[i] = board[x][y].toString();
        }

        tempCoordinates = coordinates;
        getClient().sendMessage(new RemoveMessage(coordinates, gameID, UID, username));

    }


    @Override
    public void updateBoard(BoardResponse boardMessage) {
        out.println("\n\n mi è arrivata la board \n\n");

        board = boardMessage.getBoard();
        commonTokens = boardMessage.getCommonTokens();
        endGameToken = boardMessage.isEndGameToken();
    }

    public void turn() {
        out.println("Choose the column:");
        int column = in.nextInt();
        getClient().sendMessage(new TurnMessage(gameID, UID, column, username, colors));
    }

    @Override
    public void removeResponse(RemoveResponse removeResponse) {
        if (removeResponse.isInvalidSequence()) {
            out.println("The tile sequence you provided is invalid");
            remove();
            return;
        }
        out.println("Remove successful");
        for (Coordinates tempCoordinate : tempCoordinates) {
            board[tempCoordinate.getRow()][tempCoordinate.getColumn()] = ColourTile.FREE;
        }

        turnChoice();
        //ok
    }

    @Override
    public void turnResponse(TurnResponse turnResponse) {
        if (turnResponse.getStatus() == -1) {
            out.println("The selected column has too many tiles");
            turn();
            return;
        }
        out.println("Insert tile successful");
        shelf = turnResponse.getShelf();
        int j = 0;
        for (int i = 0; i < 6; i++) {
            if (shelf[i][column] != ColourTile.FREE && !(colors[j].equals(ColourTile.FREE.toString()))) {
                shelf[i][column] = ColourTile.valueOf(colors[j]);
                j++;
            }
        }

    }

    @Override
    public void endGame(EndMessage endGameMessage) {
        out.println("The game is finished! \nThe winner is " + endGameMessage.getWinner());
    }

    public void printCommonTargets(CardCommonTarget cardCommonTarget0, CardCommonTarget cardCommonTarget1) {
        printCommon(cardCommonTarget0.getCommonType().getId(), 0);
        printCommon(cardCommonTarget1.getCommonType().getId(), 1);
    }

    public void printCommon(int id, int i) {
        out.println("            Token: " + commonTokens[i] + "\n\n\n");
        switch (id) {
            case 4: {
                out.println("""
                           ------------------------------
                           |           six               |
                           |         couples             |
                           |            of               |
                           |     the same colour         |
                           |  (each couple can be of     |
                           |     a different colour)     |
                           -------------------------------
                        """);
                break;
            }
            case 8: {
                out.println("""
                           ------------------------------
                           |           on                |
                           |          each               |
                           |          angles             |
                           |         must be             |
                           |       the same colour       |
                           |                             |
                           -------------------------------
                        """);
                break;
            }
            case 3: {
                out.println("""
                           ------------------------------
                           |          four               |
                           |         groups              |
                           |            of               |
                           |     the same colour         |
                           |  (each group can be of      |
                           |     a different colour)     |
                           -------------------------------
                        """);
                break;
            }
            case 1: {
                out.println("""
                           ------------------------------
                           |           two               |
                           |         groups of           |
                           |     the same colour         |
                           |  that create a square 2x2   |
                           |  (each group have to be of  |
                           |     the same colour)        |
                           -------------------------------
                        """);
                break;
            }
            case 5: {
                out.println("""
                           ------------------------------
                           |           three             |
                           |        columns of           |
                           |     one, two or three       |
                           |     different  colours      |
                           |  (each column can have      |
                           |    different colours)       |
                           -------------------------------
                        """);
                break;
            }
            case 9: {
                out.println("""
                           ------------------------------
                           |           eight             |
                           |         tails of            |
                           |     the same colour         |
                           |                             |
                           |  (the position is not       |
                           |          relevant)          |
                           -------------------------------
                        """);
                break;
            }
            case 11: {
                out.println("""
                           ------------------------------
                           |           five              |
                           |         tails of            |
                           |     the same colour         |
                           |      in diagonal            |
                           |                             |
                           |                             |
                           -------------------------------
                        """);
                break;
            }
            case 7: {
                out.println("""
                           ------------------------------
                           |           four              |
                           |        rows of              |
                           |     one, two or three       |
                           |     different  colours      |
                           |  (each row can have         |
                           |    different colours)       |
                           -------------------------------
                        """);
                break;
            }
            case 2: {
                out.println("""
                           ------------------------------
                           |           two               |
                           |        columns of           |
                           |         all six             |
                           |     different  colours      |
                           |                             |
                           |                             |
                           -------------------------------
                        """);
                break;
            }
            case 6: {
                out.println("""
                           ------------------------------
                           |           two               |
                           |          rows of            |
                           |          five               |
                           |     different  colours      |
                           |                             |
                           |                             |
                           -------------------------------
                        """);
                break;
            }
            case 10: {
                out.println("""
                        ------------------------------
                           |           five              |
                           |         tails of the        |
                           |        same colour          |
                           |         that create         |
                           |             a X             |
                           |                             |
                           -------------------------------
                        """);
                break;
            }
            case 12: {
                out.println("""
                           ------------------------------
                           |        five columns         |
                           |      in descending or       |
                           |      in growing  order      |
                           |      (each column have      |
                           |  one less tail or one more  |
                           |  tail of the previous one)  |
                           -------------------------------
                        """);
                break;
            }
        }
    }


    public void printBoard(ColourTile[][] colourTiles) {
        for (int i = 0; i < 11; i++) {
            System.out.print(" " + i + "  ");
        }
        System.out.print("\n");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(getColorCode(colourTiles[i][j]) + "*** " + ANSI_RESET);
            }
            System.out.print(" " + i + "\n");
        }
        //ok
    }

    public void printPersonalTargets(CardPersonalTarget cardPersonalTarget) {
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {
                int found = 0;
                for (int z = 0; z < 6; z++) {
                    if (cardPersonalTarget.personalCardTiles()[z].coordinates().getRow() == i &&
                            cardPersonalTarget.personalCardTiles()[z].coordinates().getColumn() == j) {
                        System.out.print(getColorCode(cardPersonalTarget.personalCardTiles()[z].colourTile()) + "*** " + ANSI_RESET);
                        found = 1;
                    } /*else if (cardPersonalTarget.personalCardTiles()[z].coordinates().getRow() >= i &&
                            cardPersonalTarget.personalCardTiles()[z].coordinates().getColumn() < j) {
                        System.out.print(TEXT_BLACK + "*** " + ANSI_RESET);/*
                    /*if(cardPersonalTarget.personalCardTiles()[z].coordinates().getColumn()== 4){
                        System.out.print(getColorCode(cardPersonalTarget.personalCardTiles()[z].colourTile()) + "\n" + ANSI_RESET);*/

                }
                if (found == 0) {
                    System.out.print(TEXT_BLACK + "*** " + ANSI_RESET);

                }

            }
            System.out.print("\n");

        }
    }


    //ok


    public void printShelf(ColourTile[][] colourTiles) {
        for (int i = 0; i < 5; i++) {
            out.print(" " + i + "  ");
        }
        out.print("\n");
        for (int i = 5; i >=0; i--) {
            for (int j = 0; j < 5; j++) {
                System.out.print(getColorCode(colourTiles[i][j]) + "*** " + ANSI_RESET);
            }
            out.print("\n");
        }
    }
}

/*
    private void updateShelf(TurnResponse turnResponse) {
        ColourTile[][] turnShelf = turnResponse.getShelf();
        emptyGridPane(shelfMask);
        for (int row = SHELF_ROWS - 1; row >= 0; row--) {
            for (int col = 0; col < SHELF_COLUMNS; col++) {
                int adjustedRow = SHELF_ROWS - 1 - row;

                if (!turnShelf[adjustedRow][col].equals(ColourTile.FREE)) {
                    ImageView tile = createShelfTile(turnShelf[adjustedRow][col], adjustedRow, col);
                    shelfMask.add(tile, col, row);
                }
            }
        }
    }

     */