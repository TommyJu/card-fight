package ca.bcit.comp2522.termproject.cards;

import java.util.Map;

/**
 * Represents an abstract player.
 */
public abstract class Player {
    private String name;
    private Deck deck;
    private Map<String, Integer> roundWins;

    /**
     * Constructs a new player.
     * @param name the name of the player
     * @param deck the player's deck
     */
    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        // Create key value pairs for each round win using a specific element type
        for(String element: Card.ALL_ELEMENTS) {
           this.roundWins.put(element, 0);
        }
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
     * Gets the rounds won with each element.
     * @return a Map to keep count of wins for each element
     */
    public Map<String, Integer> getRoundWins() {
        return this.roundWins;
    }

    /**
     * Sets the rounds won for an element
     * @param key the element
     * @param value the new value
     */
    public void setRoundWins(String key, Integer value) {
        this.roundWins.put(key, value);
    }

    /**
     * Increments the round wins of an element by 1 or creates a new key if not present.
     * @param element the element type that has won the round
     */
    public void incrementRoundWin(String element) {
        if (this.roundWins.containsKey(element)) {
            Integer roundWinsForElement = roundWins.get(element);
            this.roundWins.put(element, ++roundWinsForElement);
        } else {
            this.roundWins.put(element, 0);
        }
    }
}
