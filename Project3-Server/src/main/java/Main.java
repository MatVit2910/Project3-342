import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;


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
            Game g = new Game();
            g.startGame();
            System.out.println("Player Hand:");
            for (Card c : g.getPlayerHand()){
                System.out.println(c.getRank() + " " + c.getSuit());
            }
            System.out.println("Dealer Hand:");
            for (Card c : g.getDealerHand()){
                System.out.println(c.getRank() + " " + c.getSuit());
            }
            System.out.println("Amt:" + g.compareHands(g.getDealerHand(), g.getPlayerHand(), 5, 5));
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
