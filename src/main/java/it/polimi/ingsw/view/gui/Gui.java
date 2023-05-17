package it.polimi.ingsw.view.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        InputStream is = Gui.class.getClassLoader().getResourceAsStream("17_MyShelfie_BGA/Publisher material/Icon 50x50px.png");
        if (is != null) {
            stage.getIcons().add(new Image(is));
        }

        Scene scene = new Scene(new Pane());
        GuiMaster.setLayout(scene, "/fxml/loginScene.fxml");
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void stop() {
        GuiMaster.getInstance().closeConnection();
        System.exit(0);
    }
}