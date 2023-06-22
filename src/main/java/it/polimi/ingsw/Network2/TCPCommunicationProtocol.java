package it.polimi.ingsw.Network2;


import com.google.gson.Gson;
import it.polimi.ingsw.Network2.Messages.ErrorMessage;
import it.polimi.ingsw.Network2.Messages.Message;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TCPCommunicationProtocol extends UnicastRemoteObject implements CommunicationProtocol,Runnable {
    private final String serverIp;
    private final int serverPort;
    private final Thread messageReceiver;
    private PrintWriter out;
    private BufferedReader in;
    private long UID;
    private Socket socket = null;
    private final ArrayList<Message> messageList;

    public TCPCommunicationProtocol(String serverIp, int serverPort) throws RemoteException {
        super();
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.messageList = new ArrayList<>();

        messageReceiver = new Thread(this);
        messageReceiver.start();
        startCommunication();
    }

    private void startCommunication() {

        try {
            socket = new Socket(serverIp, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMessage(Message message) {
        Gson gson = new Gson();
        String jsonMessage = gson.toJson(message);
        out.println(jsonMessage);
    }

    @Override
    public void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void setup() {
        try {
            UID = in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getUID() {
        return UID;
    }

    @Override
    public void onMessage(Message message) {
        messageList.add(message);
    }

    @Override
    public ArrayList<Message> getMessages() {
        ArrayList<Message> copy;

        copy = new ArrayList<>(List.copyOf(messageList));
        messageList.clear();

        return copy;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String response = in.readLine();
                if (response != null) {
                    synchronized (messageList) {
                        Gson gson=new Gson();
                        Message message=gson.fromJson(response, Message.class);

                        onMessage(message);
                    }
                }
            }catch (IOException e){
                closeConnection();
            }
        }
    }
}
