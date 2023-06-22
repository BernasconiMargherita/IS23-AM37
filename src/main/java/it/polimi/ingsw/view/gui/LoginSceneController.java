package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Client;
import it.polimi.ingsw.Network2.Messages.LoginMessage;
import it.polimi.ingsw.Network2.Messages.LoginResponse;
import it.polimi.ingsw.Network2.Messages.Message;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import java.io.IOException;
import java.util.Objects;
import static it.polimi.ingsw.view.gui.GuiMaster.getClient;


public class LoginSceneController {
    public GridPane gridPane;
    public Button communication;
    public Button loginButton;
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
    private String connection;
    private String username;

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

        usernameField.setVisible(false);

        toggleGroup=new ToggleGroup();
        RMI.setToggleGroup(toggleGroup);
        TCP.setToggleGroup(toggleGroup);
    }

    public void login() {
        username = usernameField.getText();
        if (username == null || username.trim().isEmpty()) {
            usernameError.setText("Inserisci un username!");
        } else {
            Client client = getClient();
            client.sendMessage(new LoginMessage(username, connection, client.getUID()));
        }
    }


    public void loginResponse(LoginResponse loginResponse) {

        if (!loginResponse.isUsernameError()) {
            try {
                Client client = GuiMaster.getClient();
                client.setUsername(username);
                Scene scene=gridPane.getScene();

                if (loginResponse.isFirst()) client.setFirst();
                if (loginResponse.isInit()) client.setInit();

                client.setGameID(loginResponse.getGameID());
                GuiMaster.setLayout(scene, "/fxml/connectionScene.fxml");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            usernameError.setText("username già preso!");
        }
    }


    public void communicationChoice(MouseEvent mouseEvent) {

        RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();

        if (!RMI.isSelected() && !TCP.isSelected()) {
            protocolError.setText("Seleziona un protocollo!");
        }

        connection = selected.getText();
        GuiMaster.getInstance().createConnection(connection);

        communication.setVisible(false);
        TCP.setVisible(false);
        RMI.setVisible(false);

        loginButton.setVisible(true);
        usernameField.setVisible(true);
    }
}

