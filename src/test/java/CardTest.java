import ca.bcit.comp2522.termproject.cards.*;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.image.Image;


import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class CardTest {
    private Card testFire;
    private Card testWater;
    private Card testGrass;
    private Card testCard;
    @BeforeEach
    public void setUp() {
        testFire = new Card("fire", 10);
        testWater = new Card("water", 10);
        testGrass = new Card("grass", 10);
    }
    @Test
    public void constructionTestFire () {
        testCard = new Card("fire", 5);
        assertEquals("fire", testCard.getElement());
        assertEquals(5, testCard.getAttack());
        assertEquals("fire5.png", testCard.getImageFile());
    }
    @Test
    public void constructionTestDefault () {
        testCard = new Card("hello", 11);
        assertEquals("fire", testCard.getElement());
        assertEquals(5, testCard.getAttack());
        assertEquals("fire5.png", testCard.getImageFile());
    }
    @Test
    public void elementgetterTestGrass () {
        assertEquals("grass", testGrass.getElement());
    }
    @Test
    public void elementgetterTestFire () {
        assertEquals("fire", testFire.getElement());
    }
    @Test
    public void elementgetterTestWater () {
        assertEquals("water", testWater.getElement());
    }
    @Test
    public void attackGetter() {
        assertEquals(10, testFire.getAttack());
    }
}
