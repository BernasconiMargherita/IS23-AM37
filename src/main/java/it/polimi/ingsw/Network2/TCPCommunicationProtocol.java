package it.polimi.ingsw.Network2;


import com.google.gson.Gson;

import it.polimi.ingsw.Network2.Messages.CloseMessage;
import it.polimi.ingsw.Network2.Messages.ErrorMessage;
import it.polimi.ingsw.Network2.Messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPCommunicationProtocol implements CommunicationProtocol {
    private final String serverIp;
    private final int serverPort;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket = null;
    private final ArrayList<Message> messageList;

    public TCPCommunicationProtocol(String serverIp, int serverPort) {
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

    public Message sendMessage(Message message) {
        try {
            Gson gson = new Gson();
            String jsonMessage = gson.toJson(message);

            out.println(jsonMessage);
            String jsonResponse = in.readLine();

            Message response = gson.fromJson(jsonResponse, Message.class);

            if (response instanceof CloseMessage) closeConnection();

            return response;

        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorMessage("Error occurred during TCP communication");
        }
    }
    @Override
    public void closeConnection(){
        try {
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
}
