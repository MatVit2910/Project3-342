import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    @FXML
    private Label clientsLabel;
    public static Label clientsLabelRef;

    @FXML
    private GridPane clientsGrid;
    public static GridPane clientsGridRef;

    private ScreenChanger manager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientsLabelRef = clientsLabel;
        clientsGridRef = clientsGrid;
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