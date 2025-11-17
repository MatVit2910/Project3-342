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

public class LogController implements Initializable {

    // FXML injected UI components
    private ViewManager manager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setManager(ViewManager manager) {
        this.manager = manager;
    }

    public void handleControl(ActionEvent e) throws IOException {
        manager.showView1();
    }

    public void handleClients(ActionEvent e) throws IOException {
        manager.showView2();
    }

    public void handleLog() {
        manager.showView3();
    }
}