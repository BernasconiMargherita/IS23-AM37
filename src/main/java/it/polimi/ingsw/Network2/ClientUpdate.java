package it.polimi.ingsw.Network2;



import it.polimi.ingsw.Network2.Messages.Message;

import java.util.ArrayList;

public class ClientUpdate implements Runnable{
    private final Client client;
    private final ClientUpdateListener clientUpdateListener;
    private final Thread thread;

    public ClientUpdate(Client client, ClientUpdateListener clientUpdateListener) {
        this.client = client;
        this.clientUpdateListener = clientUpdateListener;
        this.thread = new Thread(this);
        this.thread.start();
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (client) {
                ArrayList<Message> messages;

                do {
                    messages = client.getMessages();
                    try {
                        client.wait(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } while (messages.isEmpty());

                messages.forEach(clientUpdateListener::onUpdate);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * stop the running process
     */
    public void stop() {
        this.thread.interrupt();
    }

    /**
     * start the process
     */
    public void start() {
        if (this.thread.isInterrupted()) {
            this.thread.start();
        }
    }
}

