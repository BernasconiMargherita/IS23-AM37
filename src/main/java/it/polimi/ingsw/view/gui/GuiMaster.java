package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network.ClientImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GuiMaster {

    private LoginSceneController loginSceneController;
    private GameSceneController gameSceneController;

    private static GuiMaster instance = null;
    private ClientImpl client;

    public static <T> T setLayout(Scene scene, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(GuiMaster.class.getResource(path));
        Pane pane = loader.load();
        scene.setRoot(pane);
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
    public void createConnection(String connection, String username, GuiMaster instance) throws Exception {
        client = new ClientImpl(username,connection);
    }
}
