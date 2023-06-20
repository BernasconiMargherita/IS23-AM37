package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network.MyClient;
import it.polimi.ingsw.Network.Network2.Client;
import it.polimi.ingsw.Network.Network2.CommunicationProtocol;
import it.polimi.ingsw.Network.Network2.RMICommunicationProtocol;
import it.polimi.ingsw.Network.Network2.TCPCommunicationProtocol;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GuiMaster {

    private LoginSceneController loginSceneController;
    private GameSceneController gameSceneController;

    private static GuiMaster instance = null;
    private static Client client;

    public static <T> T setLayout(Scene scene, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(GuiMaster.class.getResource(path));
        Parent root = loader.load();
        scene.setRoot(root);
        return loader.getController();
    }


    public static GuiMaster getInstance() {
        if (instance==null){
            instance=new GuiMaster();
        }
        return instance;
    }

    public void setLoginSceneController(LoginSceneController loginSceneController) {
        this.loginSceneController = loginSceneController;
    }

    public void setGameSceneController(GameSceneController gameSceneController) {
        this.gameSceneController = gameSceneController;
    }

    public static void setClient(Client client) {
        GuiMaster.client = client;
    }

    public static Client getClient() {
        return client;
    }

    public static void createConnection(String connection) {

        CommunicationProtocol communicationProtocol;

        if (connection.equalsIgnoreCase("TCP")) {
            communicationProtocol = new TCPCommunicationProtocol("localhost", 8082);

        } else {
            communicationProtocol = new RMICommunicationProtocol("RemoteController");
        }

        Client client=new Client(communicationProtocol);

        setClient(client);

    }

    public void setConnectionSceneController(ConnectionSceneController connectionSceneController) {
    }
}
