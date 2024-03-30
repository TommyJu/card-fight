package ca.bcit.comp2522.termproject.cards;

import javafx.scene.image.ImageView;

import java.util.List;

/**
 * A game card with an element and attack value.
 *
 * @author Tommy Ju
 * @version 2024
 */
public class Card {
    /**
     * Represents the width of the card's image
     */
    public final static int CARD_IMAGE_WIDTH = 220;
    /**
     * Represents the height of the card's image
     */
    public final static int CARD_IMAGE_HEIGHT = 250;
    /**
     * Represents the possible card elements
     */
    public final static List<String> ALL_ELEMENTS = List.of("water", "fire", "grass");
    /**
     * Represents the maximum attack value
     */
    public final static int MIN_ATTACK_VALUE = 1;
    /**
     * Represents the minimum attack value
     */
    public final static int MAX_ATTACK_VALUE = 10;
    /**
     * Represents the default element type.
     */
    public final static String DEFAULT_ELEMENT = "fire";
    /**
     * Represents the default attack power.
     */
    public final static int DEFAULT_ATTACK = 5;
    private final String element;
    private final int attack;
    private final String image;

    /**
     * Constructs a game card.
     * @param element represents the element type of the card
     * @param attack represents the attack power of the card
     */
    public Card(final String element, final int attack) {
        if (ALL_ELEMENTS.contains(element)) {
            this.element = element;
        } else {
            this.element = DEFAULT_ELEMENT;
        }

        if (attack >= MIN_ATTACK_VALUE && attack <= MAX_ATTACK_VALUE) {
            this.attack = attack;
        } else {
            this.attack = DEFAULT_ATTACK;
        }

        this.image = element + attack + ".png";

    }

    /**
     * Gets the card element type
     * @return a String representing the card element
     */
    public String getElement() {
        return this.element;
    }

    /**
     * Gets the card attack power.
     * @return an int representing the card attack value
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Gets the image file name for the card.
     * @return a String representing the card image file
     */
    public String getImage() {
        return this.image;
    }
} // end of class
