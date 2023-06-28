package it.polimi.ingsw.Network2;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.Network2.Messages.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
//
/**
 * MANCA
 */
public class MyServer extends UnicastRemoteObject implements ServerInterface {
    private static final RemoteController server;

    static {
        try {
            server = new RemoteControllerImpl();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructs a MyServer object and exports it as a remote object.
     *
     * @throws RemoteException if a communication-related exception occurs
     */
    protected MyServer() throws RemoteException {
        super();
    }

    /**
     * The main method of the server that starts the RMI server and listens for TCP socket connections.
     *
     * @param args command line arguments
     * @throws Exception if an exception occurs
     */
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
        ServerSocket serverSocket = new ServerSocket(8083);
        System.out.println("Il server TCP è in esecuzione...");

        try {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                long UID = System.currentTimeMillis();
                server.addTcpCl(UID, clientSocket);
                Message uidResponse=new UIDResponse(UID);
                String json = gson.toJson(uidResponse);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(json);
                out.flush();
                ClientHandler clientHandler = new ClientHandler(clientSocket, UID, myServer);
                clientHandler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Called when a message is received from a client.
     *
     * @param message the received message
     * @throws RemoteException if a communication-related exception occurs
     */
    public void onMessage(Message message) throws RemoteException {
        server.onMessage(message);
    }

    @Override
    public void disconnect() throws RemoteException {

    }


    /**
     * Adds an RMI client to the server.
     *
     * @param protocol the communication protocol of the RMI client
     * @return the unique ID assigned to the RMI client
     * @throws RemoteException if a communication-related exception occurs
     */
    public long addRmiClient(CommunicationProtocol protocol) throws RemoteException{
        long rmiId = System.currentTimeMillis();
        try {
            server.addRmiCl(rmiId, protocol);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return rmiId;
    }
}

/**
 * The ClientHandler class represents a thread that handles communication with a TCP socket client.
 */
class ClientHandler extends Thread {
    private long socketId;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    private ServerInterface myServer;

    /**
     * Constructs a ClientHandler object with the specified client socket, socket ID, and server interface.
     *
     * @param socket    the client socket
     * @param socketId  the socket ID
     * @param myServer  the server interface
     */
    public ClientHandler(Socket socket,long socketId,ServerInterface myServer) {
        this.clientSocket = socket;
        this.myServer=myServer;
        this.socketId = socketId;
    }

    /**
     * Runs the thread and handles communication with the client.
     */
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            Gson gson = new Gson();
            while(true){
                String request = in.readLine();
                onMessage(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles a message received from the client.
     *
     * @param request the received message
     */
    private void onMessage(String request) {
        try {
            Gson gson = new Gson();
            JsonElement rootElement = JsonParser.parseString(request);
            JsonObject jsonObject = rootElement.getAsJsonObject();
            String type = jsonObject.get("typeMessage").getAsString();
            System.out.println(type);
            switch (type) {
                case "PreLoginMessage" -> myServer.onMessage(gson.fromJson(request, PreLoginMessage.class));
                case "LoginMessage" -> myServer.onMessage(gson.fromJson(request, LoginMessage.class));
                case "SetMessage" -> myServer.onMessage(gson.fromJson(request, SetMessage.class));
                case "RemoveMessage" -> myServer.onMessage(gson.fromJson(request, RemoveMessage.class));
                case "TurnMessage" -> myServer.onMessage(gson.fromJson(request, TurnMessage.class));
                case "BoardMessage" -> myServer.onMessage(gson.fromJson(request, BoardMessage.class));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
