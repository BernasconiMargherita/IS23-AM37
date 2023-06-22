package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.*;

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
            case "LoginResponse" -> handleLoginResponse((LoginResponse) message);
            case "InitResponse" -> handleInitResponse((InitResponse) message);
            case "BoardResponse" -> handleBoardResponse((BoardResponse) message);
            case "RemoveResponse" -> handleRemoveResponse((RemoveResponse) message);
            case "WakeMessage" -> handleWakeMessage((WakeMessage) message);
            case "TurnResponse" -> handleTurnResponse((TurnResponse) message);
            case "EndMessage" -> handleEndMessage((EndMessage) message);
            case "SetResponse"->handleSetResponse((SetResponse)message);
        }
    }

    private void handleSetResponse(SetResponse message) {
        queue.add(()->setResponse(message));
    }

    private void handleRemoveResponse(RemoveResponse message) {
        queue.add(()->removeResponse(message));
    }

    private void handleInitResponse(InitResponse message) {
        queue.add(()->initResponse(message));
    }

    private void handleLoginResponse(LoginResponse message) {
        queue.add(()->loginResponse(message));
    }

    private void handleBoardResponse(BoardResponse message) {
        queue.add(()->updateBoard(message));
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
        client.setup();
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
        client.closeConnection();
        client = null;
    }

}
