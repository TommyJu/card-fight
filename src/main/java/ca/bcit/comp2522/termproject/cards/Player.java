package ca.bcit.comp2522.termproject.cards;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an abstract player.
 */
public abstract class Player {
    private String name;
    private Deck deck;
    private final Map<String, Integer> roundWins;

    /**
     * Constructs a new player.
     * @param name the name of the player
     * @param deck the player's deck
     */
    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        this.roundWins = new HashMap<>();
        resetRoundWins();

    }

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

    /**
     * Resets the rounds won for each element.
     */
    public void resetRoundWins() {
        this.roundWins.clear();
        // Create key value pairs for each element
        for(String element: Card.ALL_ELEMENTS) {
            this.roundWins.put(element, 0);
        }
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
