package it.polimi.ingsw.view.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Gui extends Application {

    @Override
    public void start(Stage stage) {

        stage.setWidth(800);
        stage.setHeight(600);

        InputStream is = Gui.class.getClassLoader().getResourceAsStream("assets/Publisher material/Icon 50x50px.png");
        if (is != null) {
            stage.getIcons().add(new Image(is));
        }

        Scene scene = new Scene(new Pane());
        stage.setResizable(false);
        try {
            GuiMaster.setLayout(scene, "/fxml/loginScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void stop() {
        System.exit(0);
    }
}