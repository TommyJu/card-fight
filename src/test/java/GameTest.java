import ca.bcit.comp2522.termproject.cards.Card;
import ca.bcit.comp2522.termproject.cards.Deck;
import ca.bcit.comp2522.termproject.cards.Game;
import ca.bcit.comp2522.termproject.cards.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Deck testDeck;
    private Player testPlayer;
    private Game testGame;
    private Card fire10;
    private Card fire1;
    private Card water10;
    private Card water1;
    private Card grass10;
    private Card grass1;

    @BeforeEach
    public void setUp() {
        testDeck = new Deck();
        testPlayer = new Player("Tester", testDeck) {
            @Override
            public Card getSelectedCard() {
                return super.getSelectedCard();
            }
        };
        testGame = new Game(testPlayer);
        fire10 = new Card("fire", 10);
        fire1 = new Card("fire", 1);
        water10 = new Card("water", 10);
        water1 = new Card("water", 1);
        grass10 = new Card("grass", 10);
        grass1 = new Card("grass", 1);
    }
    @Test
    public void getsCorrectElementFire() {
        assertEquals(1, testGame.getCardElementValue(fire10));
    }
    @Test
    public void getsCorrectElementGrass() {
        assertEquals(3, testGame.getCardElementValue(grass10));
    }
    @Test
    public void getsCorrectElementWater() {
        assertEquals(2, testGame.getCardElementValue(water10));
    }

    @Test
    public void getPlayer1test() {assertEquals(testGame.getPlayer1(), testPlayer);}


}
