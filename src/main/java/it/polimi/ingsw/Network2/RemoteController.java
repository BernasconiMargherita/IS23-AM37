package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;
import it.polimi.ingsw.Utils.Coordinates;

import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteController extends Remote {

    void addTcpCl(long UID, Socket socket) throws RemoteException;

    Message onMessage(Message message) throws RemoteException;

    void playClient(int client, int gameID) throws RemoteException;

    Message initGame(int gameID) throws RemoteException;

    void addRmiCl(long UID, CommunicationProtocol protocol) throws RemoteException;

    int startGame() throws RemoteException;

    Message remove(int gameID, Coordinates[] positions) throws RemoteException;

    Message turn(int gameID, String[] colour, int column, String nickname) throws RemoteException;

    Message registerPlayer(Message message) throws RemoteException;

    String getWinner(int gameID) throws RemoteException;

    Message setMaxPlayers(Message message) throws RemoteException;

}