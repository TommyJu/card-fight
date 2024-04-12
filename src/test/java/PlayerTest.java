import ca.bcit.comp2522.termproject.cards.HumanPlayer;
import ca.bcit.comp2522.termproject.cards.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private final static String DEFAULT_PLAYER_NAME = "PLAYER";
    private HumanPlayer testHumanPlayer;
    @BeforeEach
    public void setUp() {
        Deck playerDeck = new Deck();
        testHumanPlayer = new HumanPlayer(DEFAULT_PLAYER_NAME, playerDeck);
    }

    @Test
    public void calculatesWinRateZero() {
        double expected = 0;
        testHumanPlayer.incrementGamesPlayed();
        testHumanPlayer.calculateWinRate();
        double actual = testHumanPlayer.getWinRate();
        assertEquals(expected, actual);
    }

    @Test
    public void calculatesWinRateOneHundred() {
        double expected = 100;
        testHumanPlayer.incrementGamesPlayed();
        testHumanPlayer.incrementTotalWins();
        testHumanPlayer.calculateWinRate();
        double actual = testHumanPlayer.getWinRate();
        assertEquals(expected, actual);
    }

    @Test
    public void calculatesWinRateIrrational() {
        double expected = (1.0 / 3.0) * 100;
        testHumanPlayer.incrementGamesPlayed();
        testHumanPlayer.incrementGamesPlayed();
        testHumanPlayer.incrementGamesPlayed();
        testHumanPlayer.incrementTotalWins();
        testHumanPlayer.calculateWinRate();
        double actual = testHumanPlayer.getWinRate();
        assertEquals(expected, actual);
    }

} // end of class
