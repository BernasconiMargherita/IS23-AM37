package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.EndMessage;
import it.polimi.ingsw.Network2.Messages.Message;
import it.polimi.ingsw.Network2.Messages.TurnResponse;
import it.polimi.ingsw.Network2.Messages.WakeMessage;

import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class ClientManager implements ClientListener, ClientUpdateListener, Runnable {

    private ClientUpdate clientUpdater;
    private Client client;
    private final Queue<Runnable> queue= new LinkedBlockingQueue<>();

    @Override
    public void onUpdate(Message message) {
        switch (message.typeMessage()) {
            case "WakeMessage" -> handleWakeMessage((WakeMessage) message);
            case "TurnResponse" -> handleTurnResponse((TurnResponse) message);
            case "EndMessage" -> handleEndMessage((EndMessage) message);
        }

    }

    private void handleEndMessage(EndMessage message) {
        queue.add(()->endGame(message));
    }

    private void handleTurnResponse(TurnResponse message) {
        queue.add(()->turnResponse(message));
    }

    private void handleWakeMessage(WakeMessage message) {
        queue.add(()->wakeUp(message));
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                queue.remove().run();
            }catch (NoSuchElementException e){

                Thread.currentThread().interrupt();

            }
        }
    }

    public void createConnection(String connection) {
        CommunicationProtocol communicationProtocol;

        if (connection.equalsIgnoreCase("TCP")) {
            communicationProtocol = new TCPCommunicationProtocol("localhost", 8082);

        } else {
            communicationProtocol = new RMICommunicationProtocol("RemoteController");
        }

        try {
            client = new Client(communicationProtocol);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        startUpdater();
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
