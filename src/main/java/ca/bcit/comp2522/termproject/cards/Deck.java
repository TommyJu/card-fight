package ca.bcit.comp2522.termproject.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

/**
 * Represents a deck of cards comprised of the player's hand and reserve pool.
 *
 * @author Tommy Ju
 * @version 2024
 */
public class Deck {
    /**
     * The maximum number of cards in a deck
     */
    public final int MAX_DECK_SIZE = 20;
    /**
     * The maximum number of cards a player can have in hand
     */
    public final int MAX_HAND_SIZE = 5;
    private List<Card> reserve;
    private List<Card> hand;
    private List<Card> discardedCards;

    /**
     * Constructs a deck with random cards
     */
    public Deck() {
        this.hand = new ArrayList<>();
        this.reserve = new ArrayList<>();
        this.discardedCards = new ArrayList<>();

        Random generator = new Random();
        // Create random cards for the deck
        for(int i = 0; i < MAX_DECK_SIZE; i++) {
            int randomElementIndex = generator.nextInt(Card.ALL_ELEMENTS.size());
            int randomAttackValue = generator.nextInt(Card.MAX_ATTACK_VALUE) + 1;
            Card newCard = new Card(Card.ALL_ELEMENTS.get(randomElementIndex), randomAttackValue);
            // Distribute deck between the player's hand and their reserve
            if (i < MAX_HAND_SIZE) {
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
     * Deals a card by removing from the reserve and adding to the hand.
     */
    public void dealCard() {
        hand.addFirst(reserve.getFirst());
        reserve.removeFirst();
    }

    /**
     * Resets the deck and shuffles all cards.
     */
    public void shuffle() {
        // Add the cards in hand to the reserve
        reserve.addAll(hand);
        reserve.addAll(discardedCards);
        hand.clear();
        discardedCards.clear();

        Collections.shuffle(reserve);
        // Deal cards
        for (int i = 0; i < MAX_HAND_SIZE; i++) {
            dealCard();
        }
    }

    /**
     * Discards the specified card from the hand.
     * @param card the Card to be removed from the hand discarded
     */
    public void discardCard(Card card) {
        int cardIndex = hand.indexOf(card);
        discardedCards.add(hand.get(cardIndex));
        hand.remove(cardIndex);
    }
} // end of class
