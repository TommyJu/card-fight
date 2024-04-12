import ca.bcit.comp2522.termproject.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

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
    private final static int WIN_CONDITION = 3;
    private final HashMap<Integer, ArrayList<Card>> expectedP1Wins = new HashMap<>();
    private final HashMap<Integer, ArrayList<Card>> expectedP2Wins = new HashMap<>();

    @BeforeEach
    public void setUp() {
        testDeck = new Deck();
        testPlayer = new Player("Tester", testDeck) {};
        testGame = new Game(testPlayer);
        fire10 = new Card("fire", 10);
        fire1 = new Card("fire", 1);
        water10 = new Card("water", 10);
        water1 = new Card("water", 1);
        grass10 = new Card("grass", 10);
        grass1 = new Card("grass", 1);
        for (int i = 1; i < (WIN_CONDITION + 1); i++) {
            expectedP1Wins.put(i, new ArrayList<>());
            expectedP2Wins.put(i, new ArrayList<>());
        }
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
    public void getSplinter() {
        assertEquals(testGame.getPlayer2(),
                new AIPlayer("Splinter")
                );
    }
    @Test
    public void getPlayer1test() {assertEquals(testGame.getPlayer1(), testPlayer);}
    @Test
    public void isSameAttackTrue() {
        assertTrue(testGame.isSameAttack(fire1, fire1));
    }
    @Test
    public void isSameAttackTrueDifferentElements() {
        assertTrue(testGame.isSameAttack(fire1, fire1));
    }

    @Test
    public void isSameAttackFalse() {
        assertFalse(testGame.isSameAttack(fire1, fire10));
    }
    @Test
    public void isSameAttackFalseDifferentElements() {
        assertFalse(testGame.isSameAttack(fire1, fire10));
    }

    @Test
    public void sameElementTrueGrass() {
        assertTrue(testGame.sameElement(grass1, grass10));
    }
    @Test
    public void sameElementTrueFire() {
        assertTrue(testGame.sameElement(fire1, fire10));
    }
    @Test
    public void sameElementTrueWater() {
        assertTrue(testGame.sameElement(water1, water10));
    }

    @Test
    public void sameElementFalseWF() {
        assertFalse(testGame.sameElement(water1, fire10));
    }
    @Test
    public void sameElementFalseFW() {
        assertFalse(testGame.sameElement(fire1, water10));
    }
    @Test
    public void sameElementFalseGF() {
        assertFalse(testGame.sameElement(grass10, fire10));
    }
    @Test
    public void sameElementFalseGQ() {
        assertFalse(testGame.sameElement(grass10, water10));
    }
    @Test
    public void P1WinsByAttackTrueFire() {
        assertTrue(testGame.P1WinsByAttack(fire10, fire1));
    }
    @Test
    public void P1WinsByAttackFalseFire() {
        assertFalse(testGame.P1WinsByAttack(fire1, fire10));
    }
    @Test
    public void P1WinsByAttackTrueWater() {
        assertTrue(testGame.P1WinsByAttack(water10, water1));
    }
    @Test
    public void P1WinsByAttackFalseWater() {
        assertFalse(testGame.P1WinsByAttack(water1, water10));
    }
    @Test
    public void P1WinsByAttackTrueGrass() {
        assertTrue(testGame.P1WinsByAttack(grass10, grass1));
    }
    @Test
    public void P1WinsByAttackFalseGrass() {
        assertFalse(testGame.P1WinsByAttack(grass1, grass10));
    }
    @Test
    public void P1WinsByElementTrueGrass() {
        assertTrue(testGame.P1WinsByElement(grass1, water10));
    }
    @Test
    public void P1WinsByElementFalseGrass() {
        assertFalse(testGame.P1WinsByElement(water1, grass10));
    }
    @Test
    public void P1WinsByElementTrueWater() {
        assertTrue(testGame.P1WinsByElement(water1, fire10));
    }
    @Test
    public void P1WinsByElementFalseWater() {
        assertFalse(testGame.P1WinsByElement(fire1, water10));
    }
    @Test
    public void P1WinsByElementTrueFire() {
        assertTrue(testGame.P1WinsByElement(fire1, grass10));
    }
    @Test
    public void P1WinsByElementFalseFire() {
        assertFalse(testGame.P1WinsByElement(grass10, fire1));
    }
    @Test
    public void determineRoundP1win(){
        assertTrue(testGame.determineRound(fire10, grass1));
        expectedP1Wins.get(testGame.getCardElementValue(fire10)).add(fire10);
        assertEquals(testGame.getP1Wins(), expectedP1Wins);
    }
    @Test
    public void determineRoundP2win(){
        assertFalse(testGame.determineRound(fire10, water10));
        expectedP2Wins.get(testGame.getCardElementValue(water10)).add(water10);
        assertEquals(testGame.getP2Wins(), expectedP2Wins);
    }
    @Test
    public void isWinnerTrue(){
        expectedP2Wins.get(testGame.getCardElementValue(water10)).add(water10);
        expectedP2Wins.get(testGame.getCardElementValue(water10)).add(water10);
        expectedP2Wins.get(testGame.getCardElementValue(water10)).add(water10);
        assertTrue(testGame.isWinner(expectedP2Wins));
    }
    @Test
    public void isWinnerFalse(){
        expectedP1Wins.get(testGame.getCardElementValue(water10)).add(water10);
        expectedP1Wins.get(testGame.getCardElementValue(water10)).add(water10);
        assertFalse(testGame.isWinner(expectedP1Wins));
    }
    @Test
    public void determineGameWinnerP1wins() {
        testGame.getP1Wins().get(testGame.getCardElementValue(water10)).add(water10);
        testGame.getP1Wins().get(testGame.getCardElementValue(water10)).add(water10);
        testGame.getP1Wins().get(testGame.getCardElementValue(water10)).add(water10);
        assertEquals(testGame.determineGameWinner(), testPlayer);
    }
    @Test
    public void determineGameWinnerP2wins() {
        testGame.getP2Wins().get(testGame.getCardElementValue(water10)).add(water10);
        testGame.getP2Wins().get(testGame.getCardElementValue(water10)).add(water10);
        testGame.getP2Wins().get(testGame.getCardElementValue(water10)).add(water10);
        assertEquals(testGame.determineGameWinner(), testGame.getPlayer2());
    }
    @Test
    public void determineGameWinnerNoWinner() {
        assertNull(testGame.determineGameWinner());
    }

}
