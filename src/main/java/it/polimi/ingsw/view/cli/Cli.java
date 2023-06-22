package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Network2.ClientManager;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

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
    private static final int ROWS = 6;
    private static final int COLS = 5;
    private static final String[] COLORS = { "Vuota", "Rosa", "Giallo", "Azzurro", "Blu", "Verde", "Bianco" };
    private String[][] matrix;


    public Cli(){
        super();

        this.in = new Scanner(System.in);
        this.out = new MyShelfiePrintStream();
        out.println("Welcome!\n");
        out.println("\n Write connection type (TCP or RMI):");
        protocol = in.nextLine();
        if(protocol.equals("TCP")||protocol.equals("tcp") ){
            createConnection("TCP");

        }
        if(protocol.equals("RMI")||protocol.equals("rmi") ){
            createConnection("RMI");

        }
        UID = getClient().getUID();

    }


    protected void login (){
        out.println("Choose your username\n");
        username=in.nextLine();
        getClient().sendMessage(new LoginMessage(username, protocol, UID));

    }

    @Override
    public void loginResponse(LoginResponse loginResponse) {
        if(loginResponse.isUsernameError()){
            out.println("Not valid username");
            login();
            return;
        }
        if(loginResponse.isFirst()){
            out.println("Choose number of players\n");
            numPlayers=in.nextInt();
            gameID=loginResponse.getGameID();
            getClient().sendMessage(new SetMessage(numPlayers,gameID));
            return;
        }
        if(loginResponse.isInit()){
            getClient().sendMessage(new InitMessage(gameID));
            return;
        }

    }

    @Override
    public void initResponse(InitResponse initResponse) {
        out.println("The game is loading...");
        cardPersonalTarget =initResponse.getCardPersonalTarget();
        cardCommonTargets = initResponse.getCommonTargets();

    }

    @Override
    public void updateBoard(BoardResponse boardMessage) {
        matrix = new String[ROWS][COLS];

    }

    @Override
    public void removeResponse(RemoveResponse removeResponse) {

    }

    @Override
    public void turnResponse(TurnResponse turnResponse) {

    }

    @Override
    public void endGame(EndMessage endGameMessage) {

    }

    @Override
    public void wakeUp(WakeMessage wakeMessage) {


    }

    @Override
    public void setResponse(SetResponse setResponse) {

    }
}
