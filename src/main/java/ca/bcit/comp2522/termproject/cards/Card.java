package ca.bcit.comp2522.termproject.cards;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A game card with an element and attack value.
 *
 * @author Tommy Ju
 * @version 2024
 */
public class Card implements Serializable {
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
    private String element;
    private int attack;
    private String imageFile;
//    private final Image image;

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

        this.imageFile = this.element + this.attack + ".png";
//        this.image = new Image(this.imageFile);
    }

    /**
     * Gets the card element type
     * @return a String representing the card element
     */
    public String getElement() {
        return this.element;
    }

    /**
     * Sets the card element type and updates the card image.
     * @return a String representing the card element
     */
    public void setElement(String element) {
        if (ALL_ELEMENTS.contains(element)) {
            this.element = element;
            // Update image
            this.imageFile = element + this.attack + ".png";
        }
    }
    /**
     * Sets the card attack and updates the card image.
     * @return a String representing the card attack
     */
    public void setAttack(int attack) {
        if (attack <= MAX_ATTACK_VALUE && attack >= MIN_ATTACK_VALUE) {
            this.attack = attack;
            // Update image
            this.imageFile = this.element + attack + ".png";
        }
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
    public String getImageFile() {
        return this.imageFile;
    }

    /**
     * Gets the Image for the card.
     * @return a String representing the card image file
     */
    public Image getImage() {
        return new Image(this.imageFile);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return attack == card.attack && element.equals(card.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, attack, imageFile);
    }
} // end of class
