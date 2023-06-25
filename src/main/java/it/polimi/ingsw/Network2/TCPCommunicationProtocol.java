package it.polimi.ingsw.Network2;


import com.google.gson.Gson;
import it.polimi.ingsw.Network2.Messages.Message;
import it.polimi.ingsw.Network2.Messages.UIDResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TCPCommunicationProtocol extends UnicastRemoteObject implements CommunicationProtocol,Runnable {
    private final String serverIp;
    private final int serverPort;
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
        out.flush();
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
            Gson gson = new Gson();
            UIDResponse uidMessage = gson.fromJson(in.readLine(), UIDResponse.class);
            UID = uidMessage.getUID();
            Thread messageReceiver = new Thread(this);
            messageReceiver.start();
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
