import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.text.Font;

import java.io.IOException;

public class ViewManager {

    private final BorderPane root = new BorderPane();

    private Node controlScreen;
    private Node clientsScreen;
    private Node logScreen;

    private IntroController c1;
    private ClientsController c2;
    private LogController c3;

    public ViewManager() throws IOException {
        // load all views once
        FXMLLoader f1 = new FXMLLoader(getClass().getResource("/FXML/Control Screen.fxml"));
        controlScreen = f1.load();
        c1 = f1.getController();
        c1.setManager(this);

        FXMLLoader f2 = new FXMLLoader(getClass().getResource("/FXML/Clients Screen.fxml"));
        clientsScreen = f2.load();
        c2 = f2.getController();
        c2.setManager(this);

        FXMLLoader f3 = new FXMLLoader(getClass().getResource("/FXML/Log Screen.fxml"));
        logScreen = f3.load();
        c3 = f3.getController();
        c3.setManager(this);

        Font.loadFont(getClass().getResourceAsStream("/fonts/IrishGrover-Regular.ttf"), 10);
    }

    public BorderPane getRoot() {
        return root;
    }

    public void showView1() {
        root.setCenter(controlScreen);
        root.getStylesheets().add("/styles/control.css");
    }

    public void showView2() {
        root.setCenter(clientsScreen);
        root.getStylesheets().add("/styles/clients.css");
    }

    public void showView3() {
        root.setCenter(logScreen);
        root.getStylesheets().add("/styles/log.css");
    }
}
