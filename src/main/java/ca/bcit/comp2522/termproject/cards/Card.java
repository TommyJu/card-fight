package ca.bcit.comp2522.termproject.cards;
/**
 * A game card.
 *
 * @author Tommy Ju
 * @version 2024
 */
public class Card {
    /**
     * Represents the default element type.
     */
    public final String DEFAULT_ELEMENT = "fire";
    /**
     * Represents the default attack power.
     */
    public final int DEFAULT_ATTACK = 5;
    private String element;
    private int attack;
    private String image;

    /**
     * Constructs a game card.
     * @param element represents the element type of the card
     * @param attack represents the attack power of the card
     */
    public Card(final String element, final int attack) {
        if (element.equals("water") || element.equals("fire") || element.equals("grass")) {
            this.element = element;
        } else {
            this.element = DEFAULT_ELEMENT;
        }

        if (attack >= 1 && attack <= 10) {
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
