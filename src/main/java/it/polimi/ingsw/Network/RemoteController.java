package it.polimi.ingsw.Network;
import java.rmi.Remote;
import java.rmi.RemoteException;

//descrive i metodi che possono essere invocati sull'oggetto remoto
//ogni metodo deve dichiarare java.rmi.remoteexception
public interface RemoteController extends Remote {
    public void doSomething() throws RemoteException;
}