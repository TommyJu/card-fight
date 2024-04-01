package ca.bcit.comp2522.termproject.cards;

import java.util.Random;

public class AIPlayer extends Player {
    public AIPlayer(String name) {
        super(name, null); // For now, a deck is not needed as a random card is generated
    }

    /**
     * Generates a random card that is different from the user's choice
     * @param userSelectedCard the card that the user has selected
     * @return the card that the AI has chosen
     */
    public Card pickRandomCard(Card userSelectedCard) {
        Card newCard = null;
        Random generator = new Random();
        while (userSelectedCard.equals(newCard) || newCard == null) {
            int randomElementIndex = generator.nextInt(Card.ALL_ELEMENTS.size());
            int randomAttackValue = generator.nextInt(Card.MAX_ATTACK_VALUE) + 1;
            newCard = new Card(Card.ALL_ELEMENTS.get(randomElementIndex), randomAttackValue);
        }
        return newCard;
    }
}
