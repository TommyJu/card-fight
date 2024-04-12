package ca.bcit.comp2522.termproject.cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    public final int MAX_DECK_SIZE = 20;
    /**
     * The maximum number of cards a player can have in hand
     */
    public final int MAX_CARDS_IN_HAND = 5;
    private List<Card> reserve;
    private List<Card> hand;
    private List<Card> discardedCards;
    private Card cardSelected;

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
        this.cardSelected = null;
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
     * Gets the card that the user has selected last.
     * @return the card that has been selected
     */
    public Card getCardSelected() {
        return this.cardSelected;
    }

    /**
     * Sets the card that the user has selected.
     * @param card the card that has been selected
     */
    public void setCardSelected(Card card) {
        this.cardSelected = card;
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

        // Replace cards in hand
        for (int i = 0; i < MAX_CARDS_IN_HAND; i++) {
            Card cardInHand = getHand().get(i);
            dealNewCard(cardInHand, i);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return MAX_DECK_SIZE == deck.MAX_DECK_SIZE && MAX_CARDS_IN_HAND == deck.MAX_CARDS_IN_HAND && Objects.equals(reserve, deck.reserve) && Objects.equals(hand, deck.hand) && Objects.equals(discardedCards, deck.discardedCards) && Objects.equals(cardSelected, deck.cardSelected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MAX_DECK_SIZE, MAX_CARDS_IN_HAND, reserve, hand, discardedCards, cardSelected);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "MAX_DECK_SIZE=" + MAX_DECK_SIZE +
                ", MAX_CARDS_IN_HAND=" + MAX_CARDS_IN_HAND +
                ", reserve=" + reserve +
                ", hand=" + hand +
                ", discardedCards=" + discardedCards +
                ", cardSelected=" + cardSelected +
                '}';
    }
} // end of class
