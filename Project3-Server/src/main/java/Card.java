public class Card {
    private final String suit;
    private final String rank;

    public Card(String suit, String rank){
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }
    public String getRank(){
        return rank;
    }
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
}
