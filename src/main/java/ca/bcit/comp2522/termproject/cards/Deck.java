package ca.bcit.comp2522.termproject.cards;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a deck of cards comprised of the player's hand and reserve pool.
 *
 * @author Tommy Ju
 * @version 2024
 */
public class Deck implements Serializable {
    /**
     * The maximum number of cards in a deck
     */
    public static final int MAX_DECK_SIZE = 20;
    /**
     * The maximum number of cards a player can have in hand
     */
    public final int MAX_CARDS_IN_HAND = 5;
    private final List<Card> reserve;
    private final List<Card> hand;
    private final List<Card> discardedCards;

    /**
     * Constructs a deck with random cards
     */
    public Deck() {
        this.hand = new ArrayList<>();
        // NOTE TO MATTHEW: Classes that implement List share a lot of similarities
        // I was able to use a LinkedList where I originally had an ArrayList
        // Good example of the substitution principle
        this.reserve = new LinkedList<>();
        this.discardedCards = new ArrayList<>();

        Random generator = new Random();
        // Create random cards for the deck
        for(int i = 0; i < MAX_DECK_SIZE; i++) {
            int randomElementIndex = generator.nextInt(Card.ALL_ELEMENTS.size());
            int randomAttackValue = generator.nextInt(Card.MAX_ATTACK_VALUE) + 1;
            Card newCard = new Card(Card.ALL_ELEMENTS.get(randomElementIndex), randomAttackValue);
            // Distribute deck between the player's hand and their reserve
            if (i < MAX_CARDS_IN_HAND) {
                this.hand.add(newCard);
            } else {
                this.reserve.add(newCard);
            }
        }
    }

    public Deck(List<Card> cards) {
        this.hand = new ArrayList<>();
        this.reserve = new LinkedList<>();
        this.discardedCards = new ArrayList<>();

        for(int i = 0; i < MAX_DECK_SIZE; i++) {
            Card newCard = cards.get(i);
            if (i < MAX_CARDS_IN_HAND) {
                this.hand.add(newCard);
            } else {
                this.reserve.add(newCard);
            }
        }
    }

    /**
     * Gets the reserve pool of cards.
     * @return a List containing the player's reserve cards
     */
    public List<Card> getReserve() {
        return this.reserve;
    }

    /**
     * Gets the hand.
     * @return A list representing cards in hand
     */
    public List<Card> getHand() {
        return this.hand;
    }

    /**
     * Gets the discarded cards pile.
     * @return A list representing used cards
     */
    public List<Card> getDiscardedCards() {
        return this.discardedCards;
    }

    /**
     * Takes a new card from the reserve, and replaces a card in the player's hand.
     *
     * @param oldCard the Card in hand to replace
     * @return The new card that has been dealt
     */
    public Card dealNewCard(final Card oldCard, int oldCardIndex) {
        // Take card from reserve
        Card newCard = reserve.getFirst();
        reserve.remove(newCard);

        // Discard the old card and replace with a new one
        this.discardedCards.add(oldCard);
        hand.set(oldCardIndex, newCard);
        return newCard;
    }

    /**
     * Resets the deck and shuffles all cards.
     */
    public void shuffle() {
        // Round up all the cards in the deck
        reserve.addAll(hand);
        reserve.addAll(discardedCards);
        discardedCards.clear();
        Collections.shuffle(reserve);

        // Deal cards to hand
        for (int i = 0; i < MAX_CARDS_IN_HAND; i++) {
            Card cardInHand = getHand().get(i);
            dealNewCard(cardInHand, i);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return  Objects.equals(reserve, deck.reserve)
                && Objects.equals(hand, deck.hand)
                && Objects.equals(discardedCards, deck.discardedCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MAX_DECK_SIZE, MAX_CARDS_IN_HAND, reserve, hand, discardedCards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "MAX_DECK_SIZE=" + MAX_DECK_SIZE +
                ", MAX_CARDS_IN_HAND=" + MAX_CARDS_IN_HAND +
                ", reserve=" + reserve +
                ", hand=" + hand +
                ", discardedCards=" + discardedCards +
                '}';
    }
} // end of class
