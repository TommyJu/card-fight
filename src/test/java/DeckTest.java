import ca.bcit.comp2522.termproject.cards.Card;
import ca.bcit.comp2522.termproject.cards.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DeckTest {
    private static final int RESERVE_SIZE = 15;
    private static final int HAND_SIZE = 5;
    private Deck testDeck;
    private Card firstCardFromReserve;
    private Card firstCardFromHand;
    private Card middleCardFromHand;
    private Card lastCardFromHand;
    private int firstCardFromHandIndex;
    private int middleCardFromHandIndex;
    private int lastCardFromHandIndex;

    @BeforeEach
    public void setUp() {
        testDeck = new Deck();
        firstCardFromReserve = testDeck.getReserve().getFirst();

        firstCardFromHand = testDeck.getHand().getFirst();
        middleCardFromHand = testDeck.getHand().get(2);
        lastCardFromHand = testDeck.getHand().getLast();

        firstCardFromHandIndex = 0;
        middleCardFromHandIndex = 2;
        lastCardFromHandIndex = 4;
    }
    @Test
    public void dealsFirstCardFromReserveAfterSelectingFirstCardInHand() {
        Card expected = firstCardFromReserve;
        Card actual = testDeck.dealNewCard(firstCardFromHand, firstCardFromHandIndex);
        assertEquals(expected, actual);
    }

    @Test
    public void dealsFirstCardFromReserveAfterSelectingMiddleCardInHand() {
        Card expected = firstCardFromReserve;
        Card actual = testDeck.dealNewCard(middleCardFromHand, middleCardFromHandIndex);
        assertEquals(expected, actual);
    }

    @Test
    public void dealsFirstCardFromReserveAfterSelectingLastCardInHand() {
        Card expected = firstCardFromReserve;
        Card actual = testDeck.dealNewCard(lastCardFromHand, lastCardFromHandIndex);
        assertEquals(expected, actual);
    }
    @Test
    public void dealNewCardDiscardsFirstCardFromHand() {
        Card actual = firstCardFromHand;
        testDeck.dealNewCard(firstCardFromHand, firstCardFromHandIndex);
        Card expected = testDeck.getDiscardedCards().getLast();
        assertEquals(expected, actual);
    }
    @Test
    public void dealNewCardDiscardsMiddleCardFromHand() {
        Card actual = middleCardFromHand;
        testDeck.dealNewCard(middleCardFromHand, middleCardFromHandIndex);
        Card expected = testDeck.getDiscardedCards().getLast();
        assertEquals(expected, actual);
    }
    @Test
    public void dealNewCardDiscardsLastCardFromHand() {
        Card actual = lastCardFromHand;
        testDeck.dealNewCard(lastCardFromHand, lastCardFromHandIndex);
        Card expected = testDeck.getDiscardedCards().getLast();
        assertEquals(expected, actual);
    }
    @Test
    public void shuffleDeckDistributesCardsToReserve() {
        testDeck.dealNewCard(firstCardFromHand, firstCardFromHandIndex);
        testDeck.dealNewCard(middleCardFromHand, middleCardFromHandIndex);
        testDeck.dealNewCard(middleCardFromHand, middleCardFromHandIndex);
        testDeck.shuffle();
        int reserveSize = testDeck.getReserve().size();
        assertEquals(RESERVE_SIZE, reserveSize);
    }
    @Test
    public void shuffleDeckDistributesCardsToHand() {
        testDeck.dealNewCard(firstCardFromHand, firstCardFromHandIndex);
        testDeck.dealNewCard(middleCardFromHand, middleCardFromHandIndex);
        testDeck.dealNewCard(middleCardFromHand, middleCardFromHandIndex);
        testDeck.shuffle();
        int handSize = testDeck.getHand().size();
        assertEquals(HAND_SIZE, handSize);
    }

    @Test
    public void shuffleDeckRandomizesDeck() {
        int deckBeforeShuffleHashCode = testDeck.hashCode();
        testDeck.dealNewCard(firstCardFromHand, firstCardFromHandIndex);
        testDeck.dealNewCard(middleCardFromHand, middleCardFromHandIndex);
        testDeck.dealNewCard(middleCardFromHand, middleCardFromHandIndex);
        testDeck.shuffle();
        int deckAfterShuffleHashCode = testDeck.hashCode();
        assertNotEquals(deckBeforeShuffleHashCode, deckAfterShuffleHashCode);
    }
}
