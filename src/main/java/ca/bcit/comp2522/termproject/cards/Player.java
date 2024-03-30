package ca.bcit.comp2522.termproject.cards;

import java.util.Map;

public abstract class Player {
    private String name;
    private Deck deck;
    private Map<String, Integer> roundWins;

    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        // Create key value pairs for each round win using a specific element type
        for(String element: Card.ALL_ELEMENTS) {
           this.roundWins.put(element, 0);
        }
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Deck getDeck() {
        return this.deck;
    }
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    public Map<String, Integer> getRoundWins() {
        return this.roundWins;
    }
    public void setRoundWins(String key, Integer value) {
        this.roundWins.put(key, value);
    }

    public void incrementRoundWin(String element) {
        if (this.roundWins.containsKey(element)) {
            Integer roundWinsForElement = roundWins.get(element);
            this.roundWins.put(element, ++roundWinsForElement);
        } else {
            this.roundWins.put(element, 0);
        }
    }
}
