package ca.bcit.comp2522.termproject.cards;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeckBuildSceneController {
    private static Scene scene;
    private static List<Card> newDeck;
    private static int currentCardIndex;
    @FXML
    Button saveButton, backButton, previousCard, nextCard;
    @FXML
    ImageView selectFireCard, selectWaterCard, selectGrassCard;
    @FXML
    Slider attackSlider;
    @FXML
    Label currentTotalAttack;
    @FXML
    ImageView cardPreview;

    List<ImageView> allCardIcons;
    List<Label> allCardAttacks;
    @FXML
    ImageView cardIcon1, cardIcon2, cardIcon3, cardIcon4, cardIcon5, cardIcon6, cardIcon7, cardIcon8, cardIcon9,
            cardIcon10, cardIcon11, cardIcon12, cardIcon13, cardIcon14, cardIcon15, cardIcon16, cardIcon17,
            cardIcon18, cardIcon19, cardIcon20;
    @FXML
    Label cardAttack1, cardAttack2, cardAttack3, cardAttack4, cardAttack5, cardAttack6, cardAttack7, cardAttack8,
            cardAttack9, cardAttack10, cardAttack11, cardAttack12, cardAttack13, cardAttack14, cardAttack15,
            cardAttack16, cardAttack17, cardAttack18, cardAttack19, cardAttack20;

    @FXML
    public void initialize() {
        newDeck = new ArrayList<>();
        newDeck.addAll(MainApplication.player1.getDeck().getReserve());
        newDeck.addAll(MainApplication.player1.getDeck().getHand());

        currentCardIndex = 0;
        allCardIcons = new ArrayList<>();
        allCardIcons.addAll(Arrays.asList(
                cardIcon1, cardIcon2, cardIcon3, cardIcon4, cardIcon5, cardIcon6, cardIcon7, cardIcon8, cardIcon9,
                cardIcon10, cardIcon11, cardIcon12, cardIcon13, cardIcon14, cardIcon15, cardIcon16, cardIcon17,
                cardIcon18, cardIcon19, cardIcon20));
        allCardAttacks = new ArrayList<>();
        allCardAttacks.addAll(Arrays.asList(
                cardAttack1, cardAttack2, cardAttack3, cardAttack4, cardAttack5, cardAttack6, cardAttack7, cardAttack8,
                cardAttack9, cardAttack10, cardAttack11, cardAttack12, cardAttack13, cardAttack14, cardAttack15,
                cardAttack16, cardAttack17, cardAttack18, cardAttack19, cardAttack20));
    }
    public void switchToStartScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        scene = new Scene(root);
        StartSceneController.stage.setScene(scene);
        StartSceneController.stage.show();
    }
    public void updateIndexImages() {
        for(int i = 0; i < Deck.MAX_DECK_SIZE; i++ ){
            String IconFile = newDeck.get(i).getElement() + ".png";

        }
    }
    public void updateHighlightedIcon(){
        for (ImageView icon : allCardIcons) {
            icon.setEffect(null);
        }
        allCardIcons.get(currentCardIndex).setEffect(new SepiaTone());
    }
    public void updateCardPreview(){
        Card newCard = newDeck.get(currentCardIndex);
        cardPreview.setImage(newCard.getImage());
    }

    public void previousCard() {
        currentCardIndex--;
        updateCardPreview();
    }

    public void nextCard() {
        currentCardIndex++;
        updateCardPreview();
    }
}
