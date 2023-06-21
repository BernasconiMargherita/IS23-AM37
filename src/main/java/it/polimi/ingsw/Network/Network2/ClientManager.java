package it.polimi.ingsw.Network.Network2;

import it.polimi.ingsw.Network.Messages.Message;

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

        Client client=new Client(communicationProtocol);

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
            client.close();
        } catch (Exception e) {
        }
        client = null;
    }
}
