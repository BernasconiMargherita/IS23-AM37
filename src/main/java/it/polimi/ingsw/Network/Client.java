package it.polimi.ingsw.Network;

import it.polimi.ingsw.Utils.Coordinates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.RemoteException;

// Classe astratta base per i client
public abstract class Client  implements Serializable {
    public int getNum() {
        return 0;
    };
    public void playTCP() throws IOException {

    }

    public void setMaxPlayers(int maxPlayers) throws RemoteException{

    }

    public String getNickname() throws RemoteException {
        return "null";
    };

    public boolean imTCP(){
        return false;
    }

    public BufferedReader getIn(){
        return null;
    }

    public PrintWriter getOut(){
        return null;
    }

    public String getString() {
        return "null";
    };

    public Coordinates getTilePosition() throws RemoteException{
        return new Coordinates(0, 0);
    };

    public boolean isMyTurn() throws RemoteException{
        return false;
    };

    public int getGameID() throws RemoteException{
        return 0;
    };
    public void rmiRegistration() throws Exception {


    }
    public void sendMessage(String message) {


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

    public boolean setNickname(String nickname) throws IOException{
        return false;
    };

    public boolean imFirstPlayer() throws IOException {
        return false;
    }

    public boolean initMess() throws IOException {
        return false;
    }

    public void setNumOfPlayers(int numOfPlayers) throws IOException {

    }

    public boolean yourTurn() throws IOException {
        return false;
    }

    public String removeTCP(Coordinates[] coordinates) throws IOException {
        return "null";
    }

    public String columnInsertion(int column) throws IOException {
        return "null";
    }

    public void setFirst(){

    }

    public void setInit(){

    }

}

