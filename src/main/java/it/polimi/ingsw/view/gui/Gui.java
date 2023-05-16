package it.polimi.ingsw.view.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;

public class Gui extends Application {
    @Override
    public void start(Stage stage) {

        InputStream is = Gui.class.getClassLoader().getResourceAsStream("img/icon.png");
        if (is != null) {
            stage.getIcons().add(new Image(is));
        }

        stage.setScene(new Scene(new Pane()));

        GuiMaster.setLayout(stage.getScene(), "fxml/menuScene.fxml");
        stage.show();
    }

    @Override
    public void stop() {
        GuiMaster.getInstance().closeConnection();
        System.exit(0);
    }
}