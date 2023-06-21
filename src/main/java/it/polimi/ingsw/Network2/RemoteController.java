package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;
import it.polimi.ingsw.Utils.Coordinates;

import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteController extends Remote {
    void addTcpCl(long UID, Socket socket) throws RemoteException;

    void onMessage(Message message) throws RemoteException;

    void playClient(int client, int gameID) throws RemoteException;


    void initGame(int gameID) throws RemoteException;

    void addRmiCl(long UID, CommunicationProtocol protocol) throws RemoteException;

    int startGame() throws RemoteException;

    void remove(int gameID, Coordinates[] positions) throws RemoteException;

    void turn(int gameID ,String[] colors, int column,String nickname) throws RemoteException;

    void registerPlayer(Message message) throws RemoteException;

    String getWinner(int gameID) throws RemoteException;
    public void sendBoard(int gameID, String nickname) throws RemoteException

    void setMaxPlayers(Message message) throws RemoteException;
}
