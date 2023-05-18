package it.polimi.ingsw.view.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginSceneController implements Initializable {
    @FXML
    private ImageView imageView;

    @FXML
    private GridPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        rootPane.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        rootPane.getRowConstraints().add(rowConstraints);

        Image image = new Image("/assets/Publisher material/Title 2000x2000px.png");
        imageView.setImage(image);

        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");
    }
}

