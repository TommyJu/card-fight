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
     * Create the card GUI elements for the player's hand
     * @return a List containing the cards' ImageView
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
     * Takes a new card from the reserve, and replaces a card in the player's hand.
     *
     * @param cardToReplace the Card in hand to replace
     * @return The new card that has been dealt
     */
    public Card dealNewCard(final Card cardToReplace) {
        // current cards in the player's hand
        List<Card> cardsInHand = this.getHand();
        int cardToReplaceIndex = cardsInHand.indexOf((cardToReplace));

        Card newCard = reserve.getFirst();
        reserve.remove(newCard);

        hand.set(cardToReplaceIndex, newCard);
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
            dealNewCard(cardInHand);
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
