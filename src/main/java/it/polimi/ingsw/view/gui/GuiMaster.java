package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network.Network2.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class GuiMaster extends ClientManager {

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


    public void setConnectionSceneController(ConnectionSceneController connectionSceneController) {
    }
}
