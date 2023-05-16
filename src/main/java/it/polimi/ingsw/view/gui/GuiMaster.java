package it.polimi.ingsw.view.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GuiMaster {
    private LoginSceneController loginSceneController;
    private GameSceneController gameSceneController;


    public static <T> T setLayout(Scene scene, String path) {
        FXMLLoader loader = new FXMLLoader(GuiMaster.class.getClassLoader().getResource(path));

        Pane pane;
        try {
            pane = loader.load();
            scene.setRoot(pane);
        } catch (IOException e) {

            return null;
        }

        return loader.getController();
    }

    public static GuiMaster getInstance() {
        return null;
    }

    public void closeConnection() {
    }
}
