public class GameHistory {
    private int anteBet;
    private int playBet;
    private int pairPlus;
    private String result;
    private int winAmt;
    public GameHistory(int anteBet, int playBet, int pairPlus, String result, int winAmt) {
        this.anteBet = anteBet;
        this.playBet = playBet;
        this.pairPlus = pairPlus;
        this.result = result;
        this.winAmt = winAmt;
    }

    public int getAnteBet() {
        return anteBet;
    }
    public int getPlayBet() {
        return playBet;
    }
    public int getPairPlus() {
        return pairPlus;
    }
    public String getResult() {
        return result;
    }
    public int getWinAmt() {
        return winAmt;
    }
}
