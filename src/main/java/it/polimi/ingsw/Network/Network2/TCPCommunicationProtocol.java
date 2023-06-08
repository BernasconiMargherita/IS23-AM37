package it.polimi.ingsw.Network.Network2;


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

    public String sendMessage(Message message) {
        try {
            Socket socket = new Socket(serverIp, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(message.getMessage());
            String response = in.readLine();

            in.close();
            out.close();
            socket.close();

            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred during TCP communication";
        }
    }
}

