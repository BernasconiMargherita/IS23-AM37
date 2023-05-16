package it.polimi.ingsw.Network;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class MyServer {
    /**
     * The main method creates a RemoteControllerImpl object, creates a registry on port 1099,
     * and binds the RemoteControllerImpl object to the registry with the name "RemoteController".
     * This starts the server and prints a message to indicate that the server is running.
     *
     * @param args The command line arguments passed to the main method.
     * @throws Exception If an error occurs while creating the registry or binding the object to it.
     */
    public static void main(String[] args) throws Exception {

        int portNumber;
        String hostName;
        Gson gson = new Gson();


        try {
            FileReader filePort = new FileReader("ServerPort.json");
            portNumber = gson.fromJson(filePort, Integer.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        RemoteController server = new RemoteControllerImpl();
        Registry registry = LocateRegistry.createRegistry(portNumber);
        registry.rebind("RemoteController", server);
        System.out.println("RMI server is running...");


        // Creare un socket server TCP
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Il server TCP è in esecuzione...");

        while (true) {
            // Accettare le connessioni dei client TCP in ingresso
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connessione del client TCP accettata da " + clientSocket.getInetAddress());

            // Gestire le richieste del client TCP
            new Thread(new TcpHandler(clientSocket)).start();
        }
    }

    private static class TcpHandler implements Runnable {
        private Socket clientSocket;

        public TcpHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Leggere la richiesta del client TCP
                String request = in.readLine();

                // Gestire la richiesta del client TCP
                String response = handleTcpRequest(request);

                // Inviare la risposta del client TCP
                out.println(response);

                // Pulire le risorse
                out.close();
                in.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String handleTcpRequest(String request) {
            // TODO: Implementare la logica per gestire le richieste del client TCP
            return "TODO: Implementare la logica per gestire le richieste del client TCP";
        }
    }
}

