package it.polimi.ingsw.view.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.InputStream;

public class GuiMaster {
    private LoginSceneController loginSceneController;
    private GameSceneController gameSceneController;


    public static <T> T setLayout(Scene scene, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(GuiMaster.class.getResource(path));
        Pane pane = loader.load();
        scene.setRoot(pane);
        return loader.getController();
    }

    public static GuiMaster getInstance() {
        return null;
    }

    public void closeConnection() {
    }
}
