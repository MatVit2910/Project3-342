import java.util.ArrayList;

public class Game {
    private final Deck deck;
    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerHand;

    public Game() {
        deck = new Deck();
    }

    public void startGame(){
        deck.shuffle();
        playerHand = deck.dealHand();
        dealerHand = deck.dealHand();
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }
    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

}
