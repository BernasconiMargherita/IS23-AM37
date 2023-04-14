package it.polimi.ingsw.Network;
import it.polimi.ingsw.model.Player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

//descrive i metodi che possono essere invocati sull'oggetto remoto
//ogni metodo deve dichiarare java.rmi.remoteexception
public interface RemoteController extends Remote {


    void initGame(int gameID) throws RemoteException;

    void startGame() throws RemoteException;
    void placeInShelf(int gameID) throws RemoteException;

    abstract int registerPlayer(Player player, int gameID) throws RemoteException;
    public int getCurrentGameID() throws RemoteException;
    public Player getCurrentPlayer(int gameID) throws RemoteException;
}