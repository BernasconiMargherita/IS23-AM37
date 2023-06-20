package it.polimi.ingsw.Network2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements ClientCallback  {
    private String username;
    private CommunicationProtocol communicationProtocol;

    public Client(String username, CommunicationProtocol communicationProtocol) throws RemoteException {
        super();
        this.username = username;
        this.communicationProtocol = communicationProtocol;
    }

    @Override
    public void notify(String message) throws RemoteException {

    }
}

