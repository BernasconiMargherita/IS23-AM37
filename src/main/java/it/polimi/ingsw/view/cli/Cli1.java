package it.polimi.ingsw.view.cli;
import java.util.*;


public class Cli1 {
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

    public Cli() {
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
        String MyShelfieLogo = "
                "Welcome to MyShelfie Board Game\n" +
                "Before starting playing you need to setup some things:\n";

        out.println(MyShelfieLogo);
    }

    /**
     * asks username
     * @return
     */
    private String askUsername() {
        boolean firstError = true;
        String username = null;

        out.println("Enter your username:");

        do {
            out.print(">>> ");

            if (in.hasNextLine()) {
                final String currentUsername = in.nextLine();
            }
            else {
                in.nextLine();
                firstError = promptInputError(firstError, INVALID_STRING);
            }
        } while (username == null);

        CliPrinter.clearConsole(out);
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
                    firstError = promptInputError(firstError, "Invalid selection!");
                }
            } else {
                in.nextLine();
                firstError = promptInputError(firstError, "Invalid integer!");
            }
        } while (!validConnection);

        CliPrinter.clearConsole(out);

        if (connection == 0) {
            out.println("You chose Socket connection\n");
        } else {
            out.println("You chose RMI connection\n");
        }
    }


    // Seleziona il numero di giocatori
    out.println("Seleziona il numero di giocatori (da 1 a 4):");
    numPlayers = in.nextInt();
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

        int command = scanner.nextInt();
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
}