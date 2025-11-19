import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards;

    //constructor
    public Deck(){
        cards = new ArrayList<>();
        String[] ranks = {
               "A", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "J", "Q", "K"
        };

        String[] suits = {
                "Clubs", "Diamonds", "Hearts", "Spades"
        };

        //populate deck
        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }

    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            hand.add(cards.get(0));
            cards.remove(cards.get(0));
        }
        return hand;
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
    public  ArrayList<Card> getCards(){
        return cards;
    }

}
