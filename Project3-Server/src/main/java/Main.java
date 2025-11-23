import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;

//Main App
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            ScreenChanger mainScreen = new ScreenChanger();

            Scene scene = new Scene(mainScreen.getRoot());
            primaryStage.setScene(scene);
            primaryStage.setTitle("3 Card Poker - Server");
            primaryStage.setMaximized(true);
            primaryStage.show();
            mainScreen.controlScreen();

        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
