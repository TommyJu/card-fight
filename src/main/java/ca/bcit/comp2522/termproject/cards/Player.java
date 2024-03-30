package ca.bcit.comp2522.termproject.cards;

public abstract class Player {
    private String name;
    private Deck deck;

    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
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
}
