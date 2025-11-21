import java.io.Serializable;
import java.util.ArrayList;

public class PokerInfo implements Serializable {
    ArrayList<Card> player;
    ArrayList<Card> dealer;
    int anteBet;
    int playBet;
    int winningsAmt;
    int pairPlus;
    boolean fold;
    public PokerInfo(){
        player = null;
        dealer = null;
        anteBet = 0;
        playBet = 0;
        winningsAmt = 0;
        pairPlus = 0;
        fold = false;
    }

    //setters
    public void setPlayerHand(ArrayList<Card> player){
        this.player = player;
    }
    public void setDealerHand(ArrayList<Card> dealer){
        this.dealer = dealer;
    }
    public void setWinningsAmt(int winningsAmt){
        this.winningsAmt = winningsAmt;
    }
    public void setPairPlus(int pairPlus){
        this.pairPlus = pairPlus;
    }
    public void setFold(boolean fold){
        this.fold = fold;
    }
    public void setAnteBet(int anteBet){
        this.anteBet = anteBet;
    }
    public void setPlayBet(int playBet){
        this.playBet = playBet;
    }

    //getters
    public ArrayList<Card> getPlayerHand(){
        return player;
    }
    public ArrayList<Card> getDealerHand(){
        return dealer;
    }
    public int getWinningsAmt(){
        return winningsAmt;
    }
    public int getPairPlus(){
        return pairPlus;
    }
    public boolean getFold(){
        return fold;
    }
    public int getAnteBet(){
        return anteBet;
    }
    public int getPlayBet(){
        return playBet;
    }
}
