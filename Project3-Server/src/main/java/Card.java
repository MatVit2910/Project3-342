//a class that represents a card
public class Card {
    //data members
    private String suit;
    private String rank;

    //constructor
    public Card(String suit, String rank){
        this.suit = suit;
        this.rank = rank;
    }

    //getters
    public String getSuit() {
        return suit;
    }
    public String getRank(){
        return rank;
    }

    //a function that transforms card ranks into integers
    //for comparison purposes
    public int getRankValue(){
        try {
            return Integer.parseInt(rank);
        }
        catch(Exception e){
            switch (rank){
                case "A":
                    return 14;
                case "J":
                    return 11;
                case "Q":
                    return 12;
                case "K":
                    return 13;
                default:
                    return -1;
            }
        }
    }

    //setters for testing purposes
    public void setSuit(String suit){
        this.suit = suit;
    }
    public void setRank(String rank){
        this.rank = rank;
    }
}
