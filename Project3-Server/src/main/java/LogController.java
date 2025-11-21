import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogController implements Initializable {

    @FXML
    private ListView<String> log;
    public static ListView<String> logRef;

    private ScreenChanger manager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logRef = log;
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