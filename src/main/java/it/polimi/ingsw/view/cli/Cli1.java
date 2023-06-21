package it.polimi.ingsw.view.cli;
import it.polimi.ingsw.Network2.ClientManager;
import it.polimi.ingsw.Network2.Messages.EndMessage;
import it.polimi.ingsw.Network2.Messages.TurnResponse;
import it.polimi.ingsw.Network2.Messages.WakeMessage;

import java.util.*;
import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState;
import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.Utils.*;
import java.io.*;
import java.io.PrintStream;
import java.util.Scanner;



public class Cli1 extends ClientManager {
    private String username;
    private boolean tcpSelected;
    private int numPlayers;
    private String[][] board;
    private List<String> personalGoals;
    private List<String> commonGoals;
    private List<String[][]> opponentBoards;
    private List<String> chatMessages;



    private Scanner in;
    private MyShelfiePrintStream out;

    public Cli1() {
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
        String MyShelfieLogo =  "Welcome to MyShelfie Board Game\n" +
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
     * @return
     */
    private String askUsername() {
        boolean firstError = true;
        String username = null;

        out.println("Enter your username:");

        try{
            out.print(">>> ");

            username = in.nextLine();

            if( username==null){
                throw new UsernameException("Il nome utente inserito non è valido");

            }
        }  catch (UsernameException e) {
            System.out.println("Errore:" + e.getMessage());
        }

        return username;
    }

    private void doConnection() {
        boolean validConnection = false;
        boolean firstError = true;
        int connection = -1;

        String username = askUsername();
        out.printf("Welcome %s!%n", username);

        out.println("\n Choose connection type ( 0 = TCP or 1 = RMI):");

        do {
            out.print(">>> ");

            if (in.hasNextInt()) {
                connection = in.nextInt();
                in.nextLine();

                if (connection >= 0 && connection <= 1) {
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
        }
    }

    Scanner scanner = new Scanner(System.in);
    // Seleziona il numero di giocatori
    out.println("Seleziona il numero di giocatori (da 1 a 4):");
    int numberPlayers = in.nextInt();
    scanner.nextLine(); // Consuma il newline rimanente

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
}

    private boolean isUsernameTaken(String username) {
        // Logica per verificare se l'username è già in uso
        // Restituisci true se è già in uso, altrimenti false
        return false;
    }

    private void displayBoard() {
        // Logica per visualizzare la board
        // Utilizza il contenuto dell'array bidimensionale "board"
    }

    private void selectCards(Scanner scanner) {
        // Logica per selezionare le carte nella board
        // Puoi chiedere all'utente di inserire le coordinate delle carte da selezionare
        // e aggiornare l'array bidimensionale "board" di conseguenza
    }

    private void displayPersonalGoals() {
        // Logica per visualizzare i personal goal
        // Utilizza la lista "personalGoals"
    }

    private void displayCommonGoals() {
        // Logica per visualizzare i common goal
        // Utilizza la lista "commonGoals"
    }

    private void displayOpponentBoards(Scanner scanner) {
        // Logica per visualizzare le board degli avversari
        // Puoi chiedere all'utente di selezionare l'avversario di cui visualizzare la board
        // Utilizza la lista "opponentBoards"
    }

    private void displayChat() {
        // Logica per visualizzare la chat
        // Utilizza la lista "chatMessages"
    }

    private void writeMessage(Scanner scanner) {
        // Logica per scrivere un messaggio nella chat
        // Puoi chiedere all'utente di inserire il messaggio e aggiungerlo alla lista "chatMessages"
    }

    @Override
    public void updateBoard(BoardResponse boardMessage) {

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
}