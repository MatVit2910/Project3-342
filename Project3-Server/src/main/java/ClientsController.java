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

public class ClientsController implements Initializable {

    // FXML injected UI components
    @FXML
    private Label clientsLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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