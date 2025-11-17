import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/FXML/Control Screen.fxml"));

            primaryStage.setTitle("3 Card Poker - Server");
            Scene s1 = new Scene(root);
            s1.getStylesheets().add("/styles/control.css");
            Font.loadFont(getClass().getResourceAsStream("/fonts/IrishGrover-Regular.ttf"), 10);
            primaryStage.setScene(s1);
            primaryStage.setMaximized(true); //full screen because otherwise it looks ugly :)
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
