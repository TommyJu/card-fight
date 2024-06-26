package ca.bcit.comp2522.termproject.cards;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an abstract player.
 *
 * @author Tommy Ju
 * @version 2024
 */
public abstract class Player implements Serializable {
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

    /**
     * Gets the total games played.
     * @return an int representing games played
     */
    public int getGamesPlayed() {
        return this.gamesPlayed;
    }

    /**
     * Gets the player's win rate.
     * @return a double representing the win rate
     */
    public double getWinRate() { return this.winRate; }

    /**
     * Gets the player's total wins.
     * @return an int representing total wins
     */
    public int getTotalWins() {
        return this.totalWins;
    }

    /**
     * Increments the number of games played.
     */
    public void incrementGamesPlayed() {
        this.gamesPlayed += 1;
    }

    /**
     * Increments the total wins.
     */
    public void incrementTotalWins() {
        this.totalWins += 1;
    }

    /**
     * Calculates and updates the player's win rate.
     */
    public void calculateWinRate() {
        winRate = ( this.totalWins / (double)this.gamesPlayed * 100);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", deck=" + deck +
                ", gamesPlayed=" + gamesPlayed +
                ", totalWins=" + totalWins +
                ", winRate=" + winRate +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Player player = (Player) object;
        return gamesPlayed == player.gamesPlayed && totalWins == player.totalWins && Double.compare(winRate, player.winRate) == 0 && Objects.equals(name, player.name) && Objects.equals(deck, player.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, deck, gamesPlayed, totalWins, winRate);
    }
} // end of class
