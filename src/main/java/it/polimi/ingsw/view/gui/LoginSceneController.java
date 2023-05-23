package it.polimi.ingsw.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Objects;




public class LoginSceneController {
    public GridPane gridPane;
    @FXML
    private TextField usernameField;
    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private RadioButton TCP;
    @FXML
    private RadioButton RMI;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private Label protocolError;
    @FXML
    private Label usernameError;

    @FXML
    public void initialize() {
        GuiMaster guiMaster = GuiMaster.getInstance();
        guiMaster.setLoginSceneController(this);
        createScene();

    }

    public void createScene() {


        Image image = new Image("/assets/Publisher material/Title 2000x2000px.png");
        imageView.setImage(image);

        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");

        toggleGroup=new ToggleGroup();
        RMI.setToggleGroup(toggleGroup);
        TCP.setToggleGroup(toggleGroup);
    }

    public void login() {
        String username = usernameField.getText();

        RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();


        if (username == null || username.trim().isEmpty()) {

            usernameError.setText("Inserisci un username!");

        } else if (!RMI.isSelected() && !TCP.isSelected()) {

            protocolError.setText("Seleziona un protocollo!");

        } else {

            String connection = selected.getText();

            try {
                GuiMaster.getInstance().createConnection(connection, username, GuiMaster.getInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {
                GuiMaster.setLayout(gridPane.getScene(), "/fxml/connectionScene.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

