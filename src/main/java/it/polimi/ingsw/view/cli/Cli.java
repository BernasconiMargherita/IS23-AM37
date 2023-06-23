package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Network2.ClientManager;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import it.polimi.ingsw.model.*
public class Cli extends ClientManager {

    private String protocol;
    private Scanner in;
    private MyShelfiePrintStream out;
    private String username;
    private long UID;
    private int numPlayers;
    private int gameID;
    private CardPersonalTarget cardPersonalTarget;
    private ArrayList<CardCommonTarget> cardCommonTargets;
    private static final int ROWS = 9;
    private static final int COLS = 9;
    private static final String[] COLORS = {"Vuota", "Rosa", "Giallo", "Azzurro", "Blu", "Verde", "Bianco"};
    private String[][] matrix;
    private String winner;
    private String[] colours;


    public Cli() {
        super();

        this.in = new Scanner(System.in);
        this.out = new MyShelfiePrintStream();
        out.println("Welcome!\n");
        out.println("\n Write connection type (TCP or RMI):");
        protocol = in.nextLine();
        if (protocol.equals("TCP") || protocol.equals("tcp")) {
            createConnection("TCP");

        }
        if (protocol.equals("RMI") || protocol.equals("rmi")) {
            createConnection("RMI");

        }
        UID = getClient().getUID();
        login();

    }


    protected void login() {
        out.println("Choose your username\n");
        username = in.nextLine();
        getClient().sendMessage(new LoginMessage(username, protocol, UID));

    }

    @Override
    public void loginResponse(LoginResponse loginResponse) {
        if (loginResponse.isUsernameError()) {
            out.println("Not valid username");
            login();
            return;
        }
        if (loginResponse.isFirst()) {
            out.println("Choose number of players\n");
            numPlayers = in.nextInt();
            gameID = loginResponse.getGameID();
            getClient().sendMessage(new SetMessage(numPlayers, gameID));
            return;
        }
        if (loginResponse.isInit()) {
            getClient().sendMessage(new InitMessage(gameID));
            return;
        }

    }

    @Override
    public void initResponse(InitResponse initResponse) {
        out.println("The game is loading...");
        cardPersonalTarget = initResponse.getCardPersonalTarget();
        cardCommonTargets = initResponse.getCommonTargets();

    }

    @Override
    public void updateBoard(BoardResponse boardMessage) {
        //board
        //if giocatori 2
        System.out.printf("                       ______  ______" +
                "                                 |     ||     |    " +
                "                                 |     ||     |" +
                "                                 ______  ______  ______" +
                "                                 |     ||      ||      |" +
                "                                 |%col || %col ||      |" +
                "                                 ______  ______  _______"  +
                "                         ______  ______ ______  ______ ______" +
                "                        |      ||     ||      ||      ||      |" +
                "                        |      ||%col || %col || %col ||      |" +
                "                         ______ ______  ______  ______  ______ "  +
                "                         ______  ______ ______  ______ ______" +
                "                        |      ||     ||      ||      ||      |" +
                "                        |      ||%col || %col || %col ||      |" +
                "                         ______ ______  ______  ______  ______ "  +""


        );}





        CASE ->  SIX_GROUPS_OF_TWO{
            out.println("------------------------------" +
                    "   |           six               |" +
                    "   |         couples             |" +
                    "   |            of               |" +
                    "   |     the same colour         |" +
                    "   |  (each couple can be of     |" +
                    "   |     a different colour)     |" +
                    "   -------------------------------" );
        }
        CASE ->  FOUR_EQUALS_ANGLES{
            out.println("------------------------------" +
                    "   |           on                |" +
                    "   |          each               |" +
                    "   |          angles             |" +
                    "   |         must be             |" +
                    "   |       the same colour       |" +
                    "   |                             |" +
                    "   -------------------------------" );
        }
        CASE -> FOUR_GROUPS_OF_FOUR {
            out.println("------------------------------" +
                    "   |          four               |" +
                    "   |         groups              |" +
                    "   |            of               |" +
                    "   |     the same colour         |" +
                    "   |  (each group can be of      |" +
                    "   |     a different colour)     |" +
                    "   -------------------------------" );
        }
        CASE ->  TWO_GROUPS_IN_SQUARE{
            out.println("------------------------------" +
                    "   |           two               |" +
                    "   |         groups of           |" +
                    "   |     the same colour         |" +
                    "   |  that create a square 2x2   |" +
                    "   |  (each group have to be of  |" +
                    "   |     the same colour)        |" +
                    "   -------------------------------" );
        }
        CASE -> THREE_COLUMNS_THREE_DIFFERENT_TYPES{
            out.println("------------------------------" +
                    "   |           three             |" +
                    "   |        columns of           |" +
                    "   |     one, two or three       |" +
                    "   |     different  colours      |" +
                    "   |  (each column can have      |" +
                    "   |    different colours)       |" +
                    "   -------------------------------" );
        }
        CASE -> EIGHT_EQUALS{
            out.println("------------------------------" +
                    "   |           eight             |" +
                    "   |         tails of            |" +
                    "   |     the same colour         |" +
                    "   |                             |" +
                    "   |  (the position is not       |" +
                    "   |          relevant)          |" +
                    "   -------------------------------" );
        }
        CASE -> FIVE_IN_DIGONAL{
            out.println("------------------------------" +
                    "   |           five              |" +
                    "   |         tails of            |" +
                    "   |     the same colour         |" +
                    "   |      in diagonal            |" +
                    "   |                             |" +
                    "   |                             |" +
                    "   -------------------------------" );
        }
        CASE -> FOUR_ROWS_THREE_DIFFERENT_TYPES{
            out.println("------------------------------" +
                    "   |           four              |" +
                    "   |        rows of              |" +
                    "   |     one, two or three       |" +
                    "   |     different  colours      |" +
                    "   |  (each row can have         |" +
                    "   |    different colours)       |" +
                    "   -------------------------------" );
        }
        CASE ->  TWO_COLUMNS_ALL_DIFFERENT{
            out.println("------------------------------" +
                    "   |           two               |" +
                    "   |        columns of           |" +
                    "   |         all six             |" +
                    "   |     different  colours      |" +
                    "   |                             |" +
                    "   |                             |" +
                    "   -------------------------------" );
        }
        CASE -> TWO_ROWS_ALL_DIFFERENT{
            out.println("------------------------------" +
                    "   |           two               |" +
                    "   |          rows of            |" +
                    "   |          five               |" +
                    "   |     different  colours      |" +
                    "   |                             |" +
                    "   |                             |" +
                    "   -------------------------------" );
        }
        CASE ->  FIVE_IN_A_X{
            out.println("------------------------------" +
                    "   |           five              |" +
                    "   |         tails of the        |" +
                    "   |        same colour          |" +
                    "   |         that create         |" +
                    "   |             a X             |" +
                    "   |                             |" +
                    "   -------------------------------" );
        }
        CASE ->  IN_DESCENDING_ORDER{
            out.println("------------------------------" +
                    "   |        five columns         |" +
                    "   |      in descending or       |" +
                    "   |      in growing  order      |" +
                    "   |      (each column have      |" +
                    "   |  one less tail or one more  |" +
                    "   |  tail of the previous one)  |" +
                    "   -------------------------------" );
        }







    }


        @Override
        public void removeResponse (RemoveResponse removeResponse){

        }

        @Override
        public void turnResponse (TurnResponse turnResponse){
        out.println("It's you turn");
        username = turnResponse.getNickname();
        gameID = turnResponse.getGameID();
        colours = turnResponse.getColours();
        getClient().sendMessage(new TurnMessage(gameID, username, colours,));

        }

        @Override
        public void endGame (EndMessage endGameMessage){
        out.println("The game ended!");
        winner = endGameMessage.getWinner();
        System.out.printf("Congratulations %s!\n", winner);
        getClient().sendMessage(new EndMessage(winner));

        }

        @Override
        public void wakeUp (WakeMessage wakeMessage){


        }

        @Override
        public void setResponse (SetResponse setResponse){
           out.println("ciao");
        }
}