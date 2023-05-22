package it.polimi.ingsw.Network;

import it.polimi.ingsw.Utils.Coordinates;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClient extends Remote {
    public int getNum() throws RemoteException;

    public String getNickname() throws RemoteException;

    public String getString() throws RemoteException;

    public Coordinates getTilePosition() throws RemoteException;

    public boolean isMyTurn() throws RemoteException;
    public void registration(Client client) throws RemoteException;
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