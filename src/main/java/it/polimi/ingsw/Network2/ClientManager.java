package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.RemoteException;

public class ClientManager  implements ClientListener, ClientUpdateListener, Runnable {

    private ClientUpdate clientUpdater;
    private Client client;

    @Override
    public void onUpdate(Message message) {

    }

    @Override
    public void run() {

    }

    @Override
    public void updateBoard() {

    }

    public void createConnection(String connection) {

        CommunicationProtocol communicationProtocol;

        if (connection.equalsIgnoreCase("TCP")) {
            communicationProtocol = new TCPCommunicationProtocol("localhost", 8082);

        } else {
            communicationProtocol = new RMICommunicationProtocol("RemoteController");

        }

        communicationProtocol.setup();

        Client client= null;
        try {
            client = new Client(communicationProtocol);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        this.client=client;

    }

    private void startUpdater() {
        clientUpdater = new ClientUpdate(client, this);
    }

    public void closeConnection() {
        if (clientUpdater != null) {
            clientUpdater.stop();
            clientUpdater = null;
        }

        try {
            client.closeConnection();
        } catch (Exception e) {
        }
        client = null;
    }
}
