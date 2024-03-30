package ca.bcit.comp2522.termproject.cards;

public class HumanPlayer extends Player {
    private int gamesPlayed;
    private int totalWins;
    public HumanPlayer(String name, Deck deck) {
        super(name, deck);
    }
    public int getGamesPlayed() {
        return this.gamesPlayed;
    }
    public void incrementGamesPlayed() {
        this.gamesPlayed += 1;
    }
    public int getTotalWins() {
        return this.totalWins;
    }
    public void incrementTotalWins() {
        this.totalWins += 1;
    }
    public float calculateWinRate() {
        float winRate;
        winRate = this.gamesPlayed / this.totalWins;
        return winRate;
    }
}
