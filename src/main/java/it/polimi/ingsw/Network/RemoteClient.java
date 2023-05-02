package it.polimi.ingsw.Network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClient extends Remote {
    public boolean isMyTurn() throws RemoteException;
    public int getGameID() throws RemoteException;
    public void sendMessage(String message) throws RemoteException;
    public int getPositionInArrayServer() throws RemoteException;
    public void remove() throws RemoteException;
    public void turn() throws RemoteException;
    public void printMessage(String message) throws RemoteException;
    public void pong() throws RemoteException;
    public int setMaxPlayers() throws RemoteException;
    public void setNickname() throws RemoteException;

}
