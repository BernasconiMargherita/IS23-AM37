package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Client;
import it.polimi.ingsw.Network2.Messages.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.util.Objects;



public class LoginSceneController {
    public GridPane gridPane;
    public Button communication;
    public Button loginButton;
    public Label usernameLabel;
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
    private GuiMaster guiMaster;

    @FXML
    public void initialize() {
        guiMaster = GuiMaster.getInstance();
        guiMaster.setLoginSceneController(this);
        createScene();
    }

    public void createScene() {

        Image image = new Image("/assets/Publisher material/Title 2000x2000px.png");
        imageView.setImage(image);

        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");

        usernameField.setVisible(false);
        usernameLabel.setVisible(false);

        toggleGroup=new ToggleGroup();
        RMI.setToggleGroup(toggleGroup);
        TCP.setToggleGroup(toggleGroup);
    }

    public void login() {
        username = usernameField.getText();
        if (username == null || username.trim().isEmpty()) {
            usernameError.setText("Inserisci un username!");
        } else {
            Client client = guiMaster.getClient();
            client.sendMessage(new PreLoginMessage(-1,client.getUID(),username));
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
        usernameLabel.setVisible(true);
    }

    public void firstResponse(FirstResponse firstResponse) {
        GuiMaster.getInstance().getClient().setGameID(firstResponse.getGameID());
        Scene scene=gridPane.getScene();
        GuiMaster.setLayout(scene, "/fxml/firstConnectionScene.fxml");
    }


    public void preLoginResponse(PreLoginResponse preLoginResponse) {
        Scene scene=gridPane.getScene();
        GuiMaster.setLayout(scene, "/fxml/connectionScene.fxml");
    }

}

