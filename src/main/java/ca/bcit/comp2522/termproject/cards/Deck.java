package ca.bcit.comp2522.termproject.cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.*;

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
    public final int MAX_CARDS_IN_HAND = 5;
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
     *
     * @return
     */
    public List<ImageView> createCardsInHand() {
        List<ImageView> cards = new ArrayList<>(); // Stores the resulting images here

        List<Card> cardsInHand = this.getHand(); // Create an iterator for cards in hand
        Iterator<Card> cardsIterator = cardsInHand.iterator();

        while (cardsIterator.hasNext()) { // Iterates through cards in hand
            Card cardInHand = cardsIterator.next();
            Image cardImage = new Image(
                    cardInHand.getImage(),
                    Card.CARD_IMAGE_WIDTH,
                    Card.CARD_IMAGE_WIDTH,
                    true,
                    true);
            ImageView cardImageView = new ImageView(cardImage);
            // event handler for the cards in hand
            cardImageView.setOnMouseClicked((MouseEvent e) -> {
                this.discardCard(cardInHand);
                Card newCard = this.dealNewCard(cardInHand);
                // Update the image for the card that has been dealt
                cardImageView.setImage(
                        new Image(newCard.getImage(), Card.CARD_IMAGE_WIDTH, Card.CARD_IMAGE_WIDTH, true, true));
            });

            cards.add(cardImageView);
        }
        return cards;
    }

    /**
     * Deals a card by replacing a card in hand with reserve card.
     *
     * @param card the Card in hand to replace
     * @return The new card that has been dealt
     */
    public Card dealNewCard(final Card card) { // rework this method to remove the card at a given index so we can replace the specific card in hand
        List<Card> cardsInHand = this.getHand();
        int cardToReplaceIndex = cardsInHand.indexOf((card));
        Card newCard = reserve.getFirst();
        reserve.remove(newCard);
        hand.set(cardToReplaceIndex, newCard);
        return newCard;
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
        for (int i = 0; i < MAX_CARDS_IN_HAND; i++) {
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
