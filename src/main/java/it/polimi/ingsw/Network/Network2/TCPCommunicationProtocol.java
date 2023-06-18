package it.polimi.ingsw.Network.Network2;


import com.google.gson.Gson;
import it.polimi.ingsw.Network.Messages.ErrorMessage;
import it.polimi.ingsw.Network.Messages.Message;

import java.io.*;
import java.net.*;

public class TCPCommunicationProtocol implements CommunicationProtocol {
    private String serverIp;
    private int serverPort;

    public TCPCommunicationProtocol(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public Message sendMessage(Message message) {
        try {

            Socket socket = new Socket(serverIp, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Gson gson = new Gson();
            String jsonMessage = gson.toJson(message);

            out.println(jsonMessage);
            String jsonResponse = in.readLine();


            Message response = gson.fromJson(jsonResponse, Message.class);

            in.close();
            out.close();
            socket.close();

            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorMessage("Error occurred during TCP communication");
        }
    }
}
