package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Network2.ClientManager;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.CommonCards.CommonList;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Tile.ColourTile;
import it.polimi.ingsw.view.cli.ColorCodes;

import static it.polimi.ingsw.model.CommonCards.CommonList.FOUR_EQUALS_ANGLES;
import static it.polimi.ingsw.model.CommonCards.CommonList.SIX_GROUPS_OF_TWO;
import static it.polimi.ingsw.view.cli.ColorCodes.getColorCode;
import it.polimi.ingsw.model.CommonCards.CommonList.*;

public class Cli extends ClientManager {

    private String protocol;
    private Scanner in;
    private String message;
    private MyShelfiePrintStream out;
    private String username;
    private long UID;
    private int numPlayers;
    private int gameID;
    private CardPersonalTarget cardPersonalTarget;
    //private ArrayList<CardCommonTarget> cardCommonTargets;
    private String winner;
    private String colours;
    private ColourTile[][] colourTiles;
    private final String ANSI_RESET = "\u001B[0m";
    private CommonList cardCommonTarget0;
    private CommonList cardCommonTarget1;
    private int column;

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
        getClient().sendMessage(new LoginMessage(username, gameID, UID));

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
            UID = loginResponse.getUID();
            getClient().sendMessage(new SetMessage(numPlayers, gameID, UID));
            return;
        }
        if (loginResponse.isInit()) {
            getClient().sendMessage(new InitMessage(gameID, UID));
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

        colourTiles = boardMessage.getBoard();
        //BOARD
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(getColorCode(colourTiles[i][j]) + "***" + ANSI_RESET);

            }
            System.out.print(getColorCode(colourTiles[i][10]) + "***\n" + ANSI_RESET);
        }

        cardCommonTarget0 = boardMessage.getCommonTargets().get(0).getCommonType();
        cardCommonTarget1 = boardMessage.getCommonTargets().get(1).getCommonType();
        public void printCommonTargets (CommonList cardCommonTarget0, CommonList cardCommonTarget1){
            switch (cardCommonTarget1) {
                case :
                    SIX_GROUPS_OF_TWO
                    out.println("------------------------------" +
                            "   |           six               |" +
                            "   |         couples             |" +
                            "   |            of               |" +
                            "   |     the same colour         |" +
                            "   |  (each couple can be of     |" +
                            "   |     a different colour)     |" +
                            "   -------------------------------");
                case :
                    FOUR_EQUALS_ANGLES
                    out.println("------------------------------" +
                            "   |           on                |" +
                            "   |          each               |" +
                            "   |          angles             |" +
                            "   |         must be             |" +
                            "   |       the same colour       |" +
                            "   |                             |" +
                            "   -------------------------------");
                case :
                    FOUR_GROUPS_OF_FOUR {
                    out.println("------------------------------" +
                            "   |          four               |" +
                            "   |         groups              |" +
                            "   |            of               |" +
                            "   |     the same colour         |" +
                            "   |  (each group can be of      |" +
                            "   |     a different colour)     |" +
                            "   -------------------------------");
                }
                case :
                    TWO_GROUPS_IN_SQUARE {
                    out.println("------------------------------" +
                            "   |           two               |" +
                            "   |         groups of           |" +
                            "   |     the same colour         |" +
                            "   |  that create a square 2x2   |" +
                            "   |  (each group have to be of  |" +
                            "   |     the same colour)        |" +
                            "   -------------------------------");
                }
                case :
                    THREE_COLUMNS_THREE_DIFFERENT_TYPES {
                    out.println("------------------------------" +
                            "   |           three             |" +
                            "   |        columns of           |" +
                            "   |     one, two or three       |" +
                            "   |     different  colours      |" +
                            "   |  (each column can have      |" +
                            "   |    different colours)       |" +
                            "   -------------------------------");
                }
                case :
                    EIGHT_EQUALS {
                    out.println("------------------------------" +
                            "   |           eight             |" +
                            "   |         tails of            |" +
                            "   |     the same colour         |" +
                            "   |                             |" +
                            "   |  (the position is not       |" +
                            "   |          relevant)          |" +
                            "   -------------------------------");
                }
                case :
                    FIVE_IN_DIGONAL {
                    out.println("------------------------------" +
                            "   |           five              |" +
                            "   |         tails of            |" +
                            "   |     the same colour         |" +
                            "   |      in diagonal            |" +
                            "   |                             |" +
                            "   |                             |" +
                            "   -------------------------------");
                }
                case :
                    FOUR_ROWS_THREE_DIFFERENT_TYPES {
                    out.println("------------------------------" +
                            "   |           four              |" +
                            "   |        rows of              |" +
                            "   |     one, two or three       |" +
                            "   |     different  colours      |" +
                            "   |  (each row can have         |" +
                            "   |    different colours)       |" +
                            "   -------------------------------");
                }
                case :
                    TWO_COLUMNS_ALL_DIFFERENT {
                    out.println("------------------------------" +
                            "   |           two               |" +
                            "   |        columns of           |" +
                            "   |         all six             |" +
                            "   |     different  colours      |" +
                            "   |                             |" +
                            "   |                             |" +
                            "   -------------------------------");
                }
                CASE -> TWO_ROWS_ALL_DIFFERENT {
                    out.println("------------------------------" +
                            "   |           two               |" +
                            "   |          rows of            |" +
                            "   |          five               |" +
                            "   |     different  colours      |" +
                            "   |                             |" +
                            "   |                             |" +
                            "   -------------------------------");
                }
                case :
                    FIVE_IN_A_X {
                    out.println("------------------------------" +
                            "   |           five              |" +
                            "   |         tails of the        |" +
                            "   |        same colour          |" +
                            "   |         that create         |" +
                            "   |             a X             |" +
                            "   |                             |" +
                            "   -------------------------------");
                }
                case :
                    IN_DESCENDING_ORDER {
                    out.println("------------------------------" +
                            "   |        five columns         |" +
                            "   |      in descending or       |" +
                            "   |      in growing  order      |" +
                            "   |      (each column have      |" +
                            "   |  one less tail or one more  |" +
                            "   |  tail of the previous one)  |" +
                            "   -------------------------------");
                }
            }

        }

                //PERSONAL
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
        column = turnResponse.getColumn();
        getClient().sendMessage(new TurnMessage(gameID, UID, column, username, colours));

        }

        @Override
        public void endGame (EndMessage endGameMessage){
        out.println("The game ended!");
        winner = endGameMessage.getWinner();
        gameID = endGameMessage.getGameID();
        UID = endGameMessage.getUID();
        System.out.printf("Congratulations %s!\n", winner);
        getClient().sendMessage(new EndMessage(winner, gameID,UID));

        }

        @Override
        public void wakeUp (WakeMessage wakeMessage){


        }

        @Override
        public void setResponse (SetResponse setResponse){
           out.println("ciao");
        }

    @Override
    public void firstResponse(FirstResponse firstResponse) {

    }

    @Override
    public void preLoginResponse(PreLoginResponse preLoginResponse) {

    }

    @Override
    public void usernameError(UsernameError usernameError) {

    }

    @Override
    public void cardsResponse(CardsResponse cardsResponse) {

    }

    @Override
    public void reFirstResponse(ReFirstResponse reFirstResponse) {

    }
}