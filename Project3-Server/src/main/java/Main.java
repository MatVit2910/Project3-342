import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            // Read file fxml and draw interface.
            ViewManager manager = new ViewManager();

            Scene scene = new Scene(manager.getRoot());
            primaryStage.setScene(scene);
            primaryStage.setTitle("3 Card Poker - Server");
            primaryStage.setMaximized(true);
            primaryStage.show();

            // Start with view 1
            manager.showView1();

        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
