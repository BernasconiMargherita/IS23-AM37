package it.polimi.ingsw.Network;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MyClient {





    public static void main(String[] args) throws Exception {


        System.out.println("RMI o TCP ? ");
        Scanner scanner = new Scanner(System.in);

        if(scanner.next().equals("RMI")){
            int portNumber;
            String hostName;
            Gson gson = new Gson();


            try {
                FileReader filePort = new FileReader("src/main/resources/ServerPort.json");
                portNumber = gson.fromJson(filePort, Integer.class);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                FileReader fileName = new FileReader("src/main/resources/ServerHostName.json");
                hostName = gson.fromJson(fileName, String.class);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Registry registry = LocateRegistry.getRegistry(hostName, portNumber);
            RemoteController server = null;
            try {
                server = (RemoteController) registry.lookup("RemoteController");
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }

            Client client = new RMIClient(server);
            client.rmiRegistration();

        }




        else{
            Socket serverSocket = new Socket("localhost", 8080);
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            Client client = new TCPClient(serverSocket, in , out );
            while(true){

            }



        }





    }


}