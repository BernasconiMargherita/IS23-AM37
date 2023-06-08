package it.polimi.ingsw.Network;

import com.google.gson.Gson;
import it.polimi.ingsw.Network.Messages.ErrorMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.OkMessage;
import it.polimi.ingsw.Network.Messages.RequestMessage;
import it.polimi.ingsw.Network.Network2.ServerInterface;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MyServer extends UnicastRemoteObject implements ServerInterface {
    private static final RemoteController server;

    static {
        try {
            server = new RemoteControllerImpl();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    protected MyServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws Exception {

        int portNumber;
        String hostName;

        Gson gson = new Gson();


        try{
            FileReader filePort = new FileReader("src/main/resources/json/ServerPort.json");
            portNumber = gson.fromJson(filePort, Integer.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ServerInterface myServer = new MyServer();
        Registry registry = LocateRegistry.createRegistry(portNumber);
        registry.rebind("RemoteController", myServer);
        System.out.println("RMI server is running...");


        // Creare un socket server TCP
        ServerSocket serverSocket = new ServerSocket(8082);
        System.out.println("Il server TCP è in esecuzione...");

        try {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, myServer);
                clientHandler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public Message onMessage(Message message) throws RemoteException {
        return new OkMessage("Response from server");
    }
}

 class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    private ServerInterface myServer;

    public ClientHandler(Socket socket,ServerInterface myServer) {
        this.clientSocket = socket;
        this.myServer=myServer;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String request = in.readLine();
            Message response = onMessage(new RequestMessage(request));
            out.println(response.getMessage());

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Message onMessage(Message request) {
        try {
            return myServer.onMessage(request);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new ErrorMessage("errore");
        }
    }
}
