package ca.bcit.comp2522.termproject.cards;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents an abstract player.
 */
public abstract class Player {
    private String name;
    private Deck deck;
    private int gamesPlayed;
    private int totalWins;
    private double winRate;

    /**
     * Constructs a new player.
     * @param name the name of the player
     * @param deck the player's deck
     */
    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        this.gamesPlayed = 0;
        this.totalWins = 0;


    }

    /**
     * Gets the card selected by player.
     * @return Card selected by player
     */
    public Card getSelectedCard(){
        return this.getDeck().getCardSelected();
    }

    /**
     * Gets the name of the player.
     * @return the player name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the player.
     * @param name the player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the player's deck.
     * @return the player's deck
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Sets the player's deck.
     * @param deck the player's deck
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getGamesPlayed() {
        return this.gamesPlayed;
    }
    public double getWinRate() { return this.winRate; }
    public void incrementGamesPlayed() {
        this.gamesPlayed += 1;
    }
    public int getTotalWins() {
        return this.totalWins;
    }
    public void incrementTotalWins() {
        this.totalWins += 1;
    }
    public void calculateWinRate() {
        winRate = ( this.totalWins / (double)this.gamesPlayed * 100);
    }


}
