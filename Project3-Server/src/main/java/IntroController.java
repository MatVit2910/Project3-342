import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroController implements Initializable {

    // FXML injected UI components
    @FXML
    private TextField portInput;
    @FXML
    private Button toggleButton;
    @FXML

    // Internal state tracking
    private boolean isServerRunning = false;
    private String savedPort;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleToggleButton() {
        if (isServerRunning) {
            // STOP state logic (Simulate stopping the server)
            isServerRunning = false;

            // Update UI for STOPPED state
            toggleButton.setText("START");
            toggleButton.getStyleClass().remove("stop-button");
            toggleButton.getStyleClass().add("start-button");

            portInput.setDisable(false);
            portInput.clear();

            System.out.println("SERVER: Server stopped.");

        } else {
            // START state logic (Simulate starting the server)
            String port = portInput.getText();
            if (savedPort == null && (port == null || port.isEmpty())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing port number");
                alert.setHeaderText("Please add a port number (e.g. 88888)");
                alert.setContentText("Port number is missing, please add a port number");
                alert.showAndWait();
            }
            else{
                isServerRunning = true;
                if (savedPort == null) {
                    savedPort = port;
                }
                toggleButton.setText("STOP");
                toggleButton.getStyleClass().remove("start-button");
                toggleButton.getStyleClass().add("stop-button");

                // Switch from input field to status label
                portInput.setDisable(true);
                portInput.setText("Running on port: " + savedPort);

                System.out.println("SERVER: Server started on port " + savedPort);
            }
        }
    }

    public void handleControl(ActionEvent e) throws IOException {
        System.out.println("Control Btn Clicked");
    }

    public void handleClients(ActionEvent e) throws IOException {
        System.out.println("Client Btn Clicked");
    }

    public void handleLog() {
        System.out.println("Server Log button clicked!");
    }
}