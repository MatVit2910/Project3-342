import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    @FXML
    private Label clientsLabel;

    private ScreenChanger manager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setScreenChanger(ScreenChanger manager) {
        this.manager = manager;
    }

    public void handleControl(ActionEvent e) throws IOException {
        manager.controlScreen();
    }

    public void handleClients(ActionEvent e) throws IOException {
        manager.clientsScreen();
    }

    public void handleLog() {
        manager.logScreen();
    }
}