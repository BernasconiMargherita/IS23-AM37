package it.polimi.ingsw.Network;

import com.google.gson.Gson;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.Player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;


// Classe concreta per il client TCP
public class TCPClient extends Client  implements Serializable {


    private String nickname = null;
    private Socket socket = null;
    private Player player;
    PrintWriter out = null;
    BufferedReader in = null;
    PrintWriter serverOut = null;
    BufferedReader serverIn = null;
    public TCPClient(Socket socket, BufferedReader in, PrintWriter out) throws IOException {

        Gson gson = new Gson();

        Scanner scanner = new Scanner(System.in);
        this.out = out;
        this.in = in;
        this.socket = socket;
        while(true){
            System.out.println("Enter your Nickname : ");
            nickname = scanner.next();
            this.player = new Player(nickname);
            String jsonPlayer = gson.toJson(player);
            sendMessage(jsonPlayer);
            if(in.readLine().equals("NicknameOk")){
                break;
            } else{
                System.out.println("Nickname gia occupato!!!");
            }
        }

        System.out.println(in.readLine());
        System.out.println("How many players ?");
        int numOfPlayers = scanner.nextInt();
        System.out.println("numero di giocatori : " + numOfPlayers);
        out.println(numOfPlayers);
        out.flush();




    }

    public TCPClient(String nickname){
        this.player = new Player(nickname);
    }



    public boolean imTCP(){
        return true;
    }
    public int getNum() {
        return 0;
    };

    public String getNickname() throws RemoteException{
        return player.getNickname();
    };

    public String getString() {
        return "null";
    };

    public Coordinates getTilePosition() throws RemoteException{
        return new Coordinates(0,0);
    };

    public boolean isMyTurn() throws RemoteException{
        return false;
    };

    public int getGameID() throws RemoteException{
        return 0;
    };

    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    };

    public int getPositionInArrayServer() throws RemoteException{
        return 0;
    };

    public void remove() {

    };

    public void turn() {

    };

    public void printMessage(String message) throws RemoteException{

    };

    public void pong() throws RemoteException{

    };

    public int setMaxPlayers() throws RemoteException{
        return 0;
    };

    public void setNickname() throws RemoteException{

    };

    public BufferedReader getIn(){
        return in;
    }

    public PrintWriter getOut(){
        return out;
    }

}
