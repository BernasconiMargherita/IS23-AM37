package it.polimi.ingsw.Network;
import it.polimi.ingsw.model.Player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

//descrive i metodi che possono essere invocati sull'oggetto remoto
//ogni metodo deve dichiarare java.rmi.remoteexception
public interface RemoteController extends Remote {

    void registerPlayer(Player player) throws RemoteException;

    void setNumPlayers(int numPlayers) throws RemoteException;
    void startGame() throws RemoteException;
    void removeFromBoard() throws RemoteException;
    void addCardInColumn() throws RemoteException;
    void checkPersonal() throws RemoteException;
    void checkCommon() throws RemoteException;
    void checkWin();
    Player getCurrentPlayer();
    boolean isLastTurn();
}