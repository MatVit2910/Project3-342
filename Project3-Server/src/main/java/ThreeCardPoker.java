import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ThreeCardPoker {

    // Define the server's IP and port
    private static final String SERVER_IP = "127.0.0.1"; // CHANGE THIS to your server IP
    private static final int SERVER_PORT = 5555;      // CHANGE THIS to your server port

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("✅ Connected to the server: " + SERVER_IP + ":" + SERVER_PORT);



            //RECEIVE DATA FROM SERVER
            PokerInfo receivedInitialData = (PokerInfo) in.readObject();
            System.out.println("\n--- Initial Server Data Received ---");
            System.out.println("Player Hand: ");
            for (Card c : receivedInitialData.getPlayerHand()){
                System.out.println(c.getRank() + " " + c.getSuit());
            }
            System.out.println("Dealer Hand: ");
            for (Card c : receivedInitialData.getDealerHand()){
                System.out.println(c.getRank() + " " + c.getSuit());
            }
            System.out.println("Player Hand Strength: " + receivedInitialData.getPlayerStr());
            System.out.println("Dealer Hand Strength: " + receivedInitialData.getDealerStr());






            //CLIENT SENDS DATA
            PokerInfo sendBackData = new PokerInfo();
            int anteBet = 10;
            int playBet = 10;
            int pairPlusBet = 5;
            boolean fold = false;
            sendBackData.setAnteBet(anteBet);
            sendBackData.setPlayBet(playBet);
            sendBackData.setPairPlus(pairPlusBet);
            sendBackData.setFold(fold);


            out.writeObject(sendBackData);
            System.out.println("Sent client decision object back to server.");








            //READ FROM SERVER FOR RESULTS
            PokerInfo receivedFinalData = (PokerInfo) in.readObject();

            System.out.println("\n--- Final Server Results Received ---");
            System.out.println("Winnings Amount (Ante/Play): $" + receivedFinalData.getWinningsAmt());
            System.out.println("Pair Plus Winnings: $" + receivedFinalData.getPairPlus());
            System.out.println("Dealer Hand Strength (Final): " + receivedFinalData.getDealerStr());
            System.out.println("Player Hand Strength (Final): " + receivedFinalData.getPlayerStr());









            //IGNORE THIS SHI
            TimeUnit.SECONDS.sleep(10);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Client error: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Helper function to format the hand for display
    private static String handToString(ArrayList<Card> hand) {
        if (hand == null) return "N/A";
        StringBuilder sb = new StringBuilder();
        for (Card card : hand) {
            sb.append(card).append(" ");
        }
        return sb.toString().trim();
    }
}

// ⚠️ You must also have the Card and PokerInfo classes compiled in the client's path!
// Since the Card class was not provided, this example assumes a working Card class 
// that is Serializable and has a meaningful toString() method.