package ca.bcit.comp2522.termproject.cards;

public class HumanPlayer extends Player {
    private int gamesPlayed;
    private int totalWins;
    public HumanPlayer(String name, Deck deck) {
        super(name, deck);
    }
}
