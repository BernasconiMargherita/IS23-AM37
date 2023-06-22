package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Network2.ClientManager;
import it.polimi.ingsw.Network2.Messages.*;

import java.util.Scanner;

public class Cli extends ClientManager {

    private String protocol;
    private Scanner in;
    private MyShelfiePrintStream out;
    private String username;
    private int UID;


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

    }


    protected void Login (){
        out.println("Choose your username\n");
        username=in.nextLine();



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
