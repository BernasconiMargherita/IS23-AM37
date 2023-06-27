package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Network2.ClientListener;
import it.polimi.ingsw.Network2.ClientManager;
import it.polimi.ingsw.Network2.ClientUpdateListener;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.CommonCards.CommonList;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;
import it.polimi.ingsw.model.Tile.ColourTile;

import java.util.ArrayList;
import java.util.Scanner;

import static it.polimi.ingsw.view.cli.ColorCodes.getColorCode;

public class Cli extends ClientManager implements Runnable, ClientListener, ClientUpdateListener {

    private String protocol;
    ColourTile[][] shelf;
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
    private  boolean endGameToken;
    private String[] colors;
    private Scanner in ;

    public Cli(Scanner scanner) {
        super();
        in = scanner;

    }

    @Override
    public void run() {
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
        //ok
    }

    public void firstSetter(int gameID){
        out.println("Choose number of players");
        int numPlayers = -1;
        while (numPlayers<2 || numPlayers>4){
            numPlayers = in.nextInt();
            if(numPlayers<2 || numPlayers>4){
                out.println("Number of players not valid, please enter a number between 2 and 4");
            }
        } getClient().sendMessage(new SetMessage(numPlayers, gameID, UID));
        //ok
    }

    @Override
    public void firstResponse(FirstResponse firstResponse) {
        gameID = firstResponse.getGameID();
        firstSetter(gameID);
        //ok
    }
    @Override
    public void reFirstResponse(ReFirstResponse reFirstResponse) {
        gameID = reFirstResponse.getGameID();
        firstSetter(gameID);
        //ok
    }

    @Override
    public void setResponse (SetResponse setResponse){
        out.println("Setting finished correctly");
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
        //ok
    }
    @Override
    public void usernameError(UsernameError usernameError) {
        out.println("Username already exists\nPlease enter new one");
        username = in.nextLine();
        getClient().sendMessage(new LoginMessage(username, gameID, UID));
        //ok
    }

    @Override
    public void initResponse(InitResponse initResponse) {
        out.println("The game is loading...");
        colors = new String[3];
        for(int i = 0; i<3 ; i++){
            colors[i] = ColourTile.FREE.toString();
        }
        for(int i = 0; i<6; i++){
            for(int j = 0; j<5; j++){
                shelf[i][j] = ColourTile.FREE;
            }
        }
        //ok
    }
    @Override
    public void cardsResponse(CardsResponse cardsResponse) {
        cardCommonTargets = cardsResponse.getCommonTargets();
        cardPersonalTarget = cardsResponse.getCardPersonalTarget();
        //ok

    }
    @Override
    public void wakeUp (WakeMessage wakeMessage){
        for(int i = 0; i<3 ; i++){
            colors[i] = ColourTile.FREE.toString();
        }
        getClient().sendMessage(new BoardMessage(username, gameID, UID));
        try {
            wait(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        display();
    }


    public void display(){
        out.println("What do you want to see?\n1: Common cards\n2: Personal card\n3: Board\n4: EndGameToken\n5:shelf \nIf you want remove tiles write 6 ");
        int num = -1;
        while(num<=0 || num>6){
            num = in.nextInt();
            if(num<=0 || num>6){
                out.println("Enter a valid number PLEASE");
            }
        }
        if( num ==1 ){
            printCommonTargets(cardCommonTargets.get(0).getCommonType(), cardCommonTargets.get(1).getCommonType());
        } else if ( num == 2){
            printPersonalTargets(cardPersonalTarget);
        } else if( num == 3){
            printBoard(board);
        } else if( num == 4){
            if(!endGameToken){
                out.println("EndGameToken already taken");
            } else {
                out.println("EndGameToken still in game");
            }
        } else if (num == 5) {
            printShelf(shelf);
        }else remove();
    }


    public void remove(){
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        for(int i=0; i<3; i++){
                if(i!=1){
                    out.println("Do you want to remove other cards?");
                    if(in.nextLine().equals("no")){
                        break;
                    }
                }
                out.println("Insert the coordinates of the cards you want to remove, in order with respect to column insertion in the shelf :\ninsert row:\n");

                int x = in.nextInt();
                out.println("Insert column:");
                int y = in.nextInt();
                while(true){
                    out.println("Are your sure? Answer yes or no");
                    String no = in.nextLine();
                    if (no.equals("no")){
                        out.println("Do you want to change row or column?");
                        String change = in.nextLine();
                        if(change.equals("row")){
                            out.println("Insert your new row");
                            x = in.nextInt();
                        } else {
                            out.println("Insert your new column");
                            y = in.nextInt();
                        }
                    }
                    if(no.equals("yes")){
                        coordinates.add(new Coordinates(x, y));
                        break;
                    }
                }
                colors[i] = board[x][y].toString();
            }

            getClient().sendMessage(new RemoveMessage(coordinates, gameID, UID, username));

    }


    @Override
    public void updateBoard(BoardResponse boardResponse){
        board = boardResponse.getBoard();
        commonTokens = boardResponse.getCommonTokens();
        endGameToken = boardResponse.isEndGameToken();
    }

    public void turn(){
        out.println("Choose the column:");
        int column = in.nextInt();
        getClient().sendMessage(new TurnMessage(gameID, UID, column,username, colors ));
    }

    @Override
    public void removeResponse (RemoveResponse removeResponse){
        if(removeResponse.isInvalidSequence()){
            out.println("The tile sequence you provided is invalid");
            remove();
            return;
        }
        out.println("Remove successful");
        turn();
        //ok
    }

    @Override
    public void turnResponse (TurnResponse turnResponse){
        if(turnResponse.getStatus()==-1){
            out.println("The selected column has too many tiles");
            turn();
            return;
        }
        out.println("Insert tile successful");
        int j=0;
        for(int i = 0; i < 6; i++){
            if(shelf[i][column] != ColourTile.FREE && !(colors[j].equals(ColourTile.FREE.toString()))){
                shelf[i][column] = ColourTile.valueOf(colors[j]);
                j++;
            }
        }

    }

    @Override
    public void endGame (EndMessage endGameMessage){
        out.println("The game is finished! \nThe winner is " + endGameMessage.getWinner());
    }
    public void printCommonTargets(CommonList cardCommonTarget0, CommonList cardCommonTarget1){
        printCommon(cardCommonTarget0, 0);
        printCommon(cardCommonTarget1, 1);
    }

    public void printCommon(CommonList cardCommonTarget, int i){
        out.println("            Token: " + commonTokens[i] + "\n\n\n");
        switch (cardCommonTarget) {
            case SIX_GROUPS_OF_TWO:
                out.println("------------------------------" +
                        "   |           six               |" +
                        "   |         couples             |" +
                        "   |            of               |" +
                        "   |     the same colour         |" +
                        "   |  (each couple can be of     |" +
                        "   |     a different colour)     |" +
                        "   -------------------------------");
            case FOUR_EQUALS_ANGLES:
                out.println("------------------------------" +
                        "   |           on                |" +
                        "   |          each               |" +
                        "   |          angles             |" +
                        "   |         must be             |" +
                        "   |       the same colour       |" +
                        "   |                             |" +
                        "   -------------------------------");
            case FOUR_GROUPS_OF_FOUR :
                out.println("------------------------------" +
                        "   |          four               |" +
                        "   |         groups              |" +
                        "   |            of               |" +
                        "   |     the same colour         |" +
                        "   |  (each group can be of      |" +
                        "   |     a different colour)     |" +
                        "   -------------------------------");

            case TWO_GROUPS_IN_SQUARE :
                out.println("------------------------------" +
                        "   |           two               |" +
                        "   |         groups of           |" +
                        "   |     the same colour         |" +
                        "   |  that create a square 2x2   |" +
                        "   |  (each group have to be of  |" +
                        "   |     the same colour)        |" +
                        "   -------------------------------");

            case THREE_COLUMNS_THREE_DIFFERENT_TYPES:
                out.println("------------------------------" +
                        "   |           three             |" +
                        "   |        columns of           |" +
                        "   |     one, two or three       |" +
                        "   |     different  colours      |" +
                        "   |  (each column can have      |" +
                        "   |    different colours)       |" +
                        "   -------------------------------");

            case EIGHT_EQUALS :
                out.println("------------------------------" +
                        "   |           eight             |" +
                        "   |         tails of            |" +
                        "   |     the same colour         |" +
                        "   |                             |" +
                        "   |  (the position is not       |" +
                        "   |          relevant)          |" +
                        "   -------------------------------");

            case FIVE_IN_DIGONAL :
                out.println("------------------------------" +
                        "   |           five              |" +
                        "   |         tails of            |" +
                        "   |     the same colour         |" +
                        "   |      in diagonal            |" +
                        "   |                             |" +
                        "   |                             |" +
                        "   -------------------------------");

            case FOUR_ROWS_THREE_DIFFERENT_TYPES :
                out.println("------------------------------" +
                        "   |           four              |" +
                        "   |        rows of              |" +
                        "   |     one, two or three       |" +
                        "   |     different  colours      |" +
                        "   |  (each row can have         |" +
                        "   |    different colours)       |" +
                        "   -------------------------------");

            case TWO_COLUMNS_ALL_DIFFERENT :
                out.println("------------------------------" +
                        "   |           two               |" +
                        "   |        columns of           |" +
                        "   |         all six             |" +
                        "   |     different  colours      |" +
                        "   |                             |" +
                        "   |                             |" +
                        "   -------------------------------");

            case TWO_ROWS_ALL_DIFFERENT :
                out.println("------------------------------" +
                        "   |           two               |" +
                        "   |          rows of            |" +
                        "   |          five               |" +
                        "   |     different  colours      |" +
                        "   |                             |" +
                        "   |                             |" +
                        "   -------------------------------");

            case FIVE_IN_A_X :
                out.println(   "------------------------------\n" +
                        "   |           five              |\n" +
                        "   |         tails of the        |\n" +
                        "   |        same colour          |\n" +
                        "   |         that create         |\n" +
                        "   |             a X             |\n" +
                        "   |                             |\n" +
                        "   -------------------------------\n");
            case IN_DESCENDING_ORDER :
                out.println("   ------------------------------\n" +
                        "   |        five columns         |\n" +
                        "   |      in descending or       |\n" +
                        "   |      in growing  order      |\n" +
                        "   |      (each column have      |\n" +
                        "   |  one less tail or one more  |\n" +
                        "   |  tail of the previous one)  |\n" +
                        "   -------------------------------\n");

        }
    }




    public void printBoard(ColourTile[][] colourTiles) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(getColorCode(colourTiles[i][j]) + "***" + ANSI_RESET);
            }
            System.out.print("\n");
        }
        //ok
    }
    public void printPersonalTargets( CardPersonalTarget cardPersonalTarget){
        for(int i=5; i>=0; i--){
            for(int j=0; j<4; j++){
                for(int z=0; z<6; z++){
                    if(cardPersonalTarget.personalCardTiles()[z].coordinates().getRow()==i &&
                            cardPersonalTarget.personalCardTiles()[z].coordinates().getColumn() == j){
                        System.out.println(getColorCode(cardPersonalTarget.personalCardTiles()[z].colourTile()) + "***" + ANSI_RESET);
                    }
                    if(cardPersonalTarget.personalCardTiles()[z].coordinates().getColumn()== 4){
                        System.out.println(getColorCode(cardPersonalTarget.personalCardTiles()[z].colourTile()) + "***\n" + ANSI_RESET);
                    }
                }
            }
        }
        //ok
    }

    public void printShelf(ColourTile[][] colourTiles){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(getColorCode(colourTiles[i][j]) + "***" + ANSI_RESET);
            }
            System.out.print("\n");
        }
    }
}