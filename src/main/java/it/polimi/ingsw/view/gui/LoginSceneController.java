package it.polimi.ingsw.view.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginSceneController implements Initializable {
    @FXML
    private ImageView logoImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Carica l'immagine dell'icona nella ImageView
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("17_MyShelfie_BGA/misc/sfondo parquet.jpg")));
        logoImage.setImage(logo);
    }

}

