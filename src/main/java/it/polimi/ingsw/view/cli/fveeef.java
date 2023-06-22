package it.polimi.ingsw.view.cli;
import java.util.*;

import it.polimi.ingsw.Network2.Client;
import it.polimi.ingsw.Network2.ClientManager;

import java.util.Scanner;



public class fveeef extends ClientManager {
    private String username;
    private boolean tcpSelected;
    public int numPlayers;
    private String[][] board;
    private List<String> personalGoals;
    private List<String> commonGoals;
    private List<String[][]> opponentBoards;
    private List<String> chatMessages;
    private Client client;



    private Scanner in;
    private MyShelfiePrintStream out;


    public fveeef() {
        super();

        this.in = new Scanner(System.in);
        this.out = new MyShelfiePrintStream();
    }


    /**
     * Starts the view.cli
     */
    public void start() {
        printLogo();
        doConnection();
    }

    /**
     * Prints MyShelfie Logo
     */
    private void printLogo() {
        String MyShelfieLogo = "Welcome to MyShelfie Board Game\n" +
                "Before starting playing you need to setup your connection:\n";


        out.println(MyShelfieLogo);
    }

    private boolean inputError(boolean firstError, String errorMessage) {
        //*out.print(AnsiCode.CLEAR_LINE);
        //if (!firstError) {
        //   out.print(AnsiCode.CLEAR_LINE);
        // }

        out.println(errorMessage);
        return false;
    }

    /**
     * asks username
     *
     * @return
     */
    private String askUsername() {
        boolean firstError = true;
        String username = null;

        out.println("Enter your username:");

        try {
            out.print(">>> ");

            username = in.nextLine();

        return username;
    }

    private void doConnection() {

        out.printf("Welcome %s!\n", username);

        out.println("\n Write connection type (TCP or RMI):");

        do {


            if (in.hasNextInt()) {


                {
                    validConnection = true;
                } else {
                    firstError = inputError(firstError, "Invalid selection!");
                }
            } else {
                in.nextLine();
                firstError = inputError(firstError, "Invalid integer!");
            }
        } while (!validConnection);

        //CliPrinter.clearConsole(out);

        if (connection == 0) {
            out.println("You chose Socket connection\n");
            createConnection("TCP");
        } else {
            out.println("You chose RMI connection\n");
            createConnection("RMI");
        }
    }

    Scanner scanner = new Scanner(System.in);
    // Seleziona il numero di giocatori
    public int getNumPlayers() {
        out.println("Seleziona il numero di giocatori (da 1 a 4):");
        numPlayers = in.nextInt();
        scanner.nextLine(); // Consuma il newline rimanente
        return numPlayers;
    }

/*
    // Inizializza lo stato del gioco
    board = new String[6][5];
    personalGoals = new ArrayList<>();
    commonGoals = new ArrayList<>();
    opponentBoards = new ArrayList<>();
    chatMessages = new ArrayList<>();

    // Loop principale del gioco
    boolean running = true;
    while (running) {
        System.out.println("\n********** SCHERMATA PRINCIPALE **********");
        // Visualizza la board
        displayBoard();

        System.out.println("\nSeleziona un comando:");
        System.out.println("1. Seleziona le tue carte nella board");
        System.out.println("2. Guarda i tuoi personal goal");
        System.out.println("3. Guarda i common goal");
        System.out.println("4. Guarda la board degli avversari");
        System.out.println("5. Guarda la chat");
        System.out.println("6. Scrivi un messaggio nella chat");
        System.out.println("7. Torna indietro");

        int command = Scanner.nextInt();
        scanner.nextLine(); // Consuma il newline rimanente

        switch (command) {
            case 1:
                selectCards(scanner);
                break;
            case 2:
                displayPersonalGoals();
                break;
            case 3:
                displayCommonGoals();
                break;
            case 4:
                displayOpponentBoards(scanner);
                break;
            case 5:
                displayChat();
                break;
            case 6:
                writeMessage(scanner);
                break;
            case 7:
                running = false;
                break;
            default:
                System.out.println("Comando non valido. Riprova.");
                break;
        }
    }

    scanner.close();
*/

    private boolean isUsernameTaken(String username) {
        // Logica per verificare se l'username è già in uso
        // Restituisci true se è già in uso, altrimenti false
        return false;
    }

    private void displayBoard() {
        for (int i=0; i< 6 ; i++){
            for(int j=0; j<5; j++){
                System.out.print(board[i][j]+ "-");
            }
            System.out.println();
        }
        // Logica per visualizzare la board
        // Utilizza il contenuto dell'array bidimensionale "board"
    }



    @Override
    public void updateBoard(BoardMessage boardMessage) {

    }

    @Override
    public void loginResponse(LoginResponse loginResponse) {

    }

    @Override
    public void initResponse(InitResponse initResponse) {

    }

    @Override
    public void updateBoard(BoardResponse boardMessage) {

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