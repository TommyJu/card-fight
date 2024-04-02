package ca.bcit.comp2522.termproject.cards;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles Match logic.
 */
public class Game {


    public final static Player splinter = new AIPlayer("splinter");
    public final int winCondition = 3;
    private final Player player1;
    private final Player player2;

    private HashMap<Integer, ArrayList<Card>> P1Wins = new HashMap<>();
    private HashMap<Integer, ArrayList<Card>> P2Wins = new HashMap<>();
    private static final boolean[] P1winsArray = {false, true, false};
    public Game(Player player1){
        this.player1 = player1;
        this.player2 = splinter;

        for (int i = 1; i < (winCondition+1); i++) {
            P1Wins.put(i, new ArrayList<>());
            P2Wins.put(i, new ArrayList<>());
        }
    }

    /**
     * Run a round of a match.
     */
    public void playMatch() {
        Card P1card;
        Card P2card;
        P1card = player1.getSelectedCard();
        P2card = player2.getSelectedCard();
        determineRound(P1card, P2card);
        if (isWinner(getP1Wins())) {
            // do things for winning
            System.out.println("You win");
        }
        if (isWinner(getP2Wins())) {
            // things for AI winning
            System.out.println("you lose");
        }
    }

    /**
     * Determines if there is a winner(3 of an element).
     * @param wins Hashmap representing a players win count.
     * @return true if there is an element with 3 wins, else false
     */
    public boolean isWinner(HashMap<Integer, ArrayList<Card>> wins){
        for(Integer i : wins.keySet()) {
            if (wins.get(i).size() == 3){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines result of round.
     * @param P1card Card representing the card player 1 played.
     * @param P2card card representing the card player 2 played
     */
    public void determineRound(Card P1card, Card P2card){
        if (sameElement(P1card, P2card)){ // checking elements
            if (isSameAttack(P1card, P2card)){
                System.out.println("its a draw");
                //neither players will receive a win
            } else {
                if (P1WinsByAttack(P1card, P2card)) {
                    P1Wins.get(getCardElementValue(P1card)).add(P1card);
                } else {
                    P2Wins.get(getCardElementValue(P1card)).add(P2card);
                }
            }
        } else { // round determined by type
            if (P1WinsByElement(P1card, P2card)) {
                P1Wins.get(getCardElementValue(P1card)).add(P1card);
            } else {
                P2Wins.get(getCardElementValue(P1card)).add(P2card);
            }
        }
    }

    /**
     * Determimes if player1 wins by type.
     * @param P1card represents the card played by player 1
     * @param P2card represents the card played by player2
     * @return boolean value, true if P1 wins
     */
    public boolean P1WinsByElement(Card P1card, Card P2card) {
        int result = (getCardElementValue(P1card) - getCardElementValue(P2card));
        if (result>0){
            return P1winsArray[result];
        } else  {
            return P1winsArray[P1winsArray.length + result];
        }
    }

    /**
     * Determines if player 1 wins through attack value.
     * @param P1card represents the card played by player 1
     * @param P2card represents the card played by player 2
     * @return true if player 1 wins the round.
     */
    public boolean P1WinsByAttack(Card P1card, Card P2card) {
        int result = P1card.getAttack() - P2card.getAttack();
        return result > 0;
    }

    /**
     * Determines if both players played the same element.
     * @param P1card represents the card played by player 1
     * @param P2card represent ths card played by player 2
     * @return true if same otherwise false.
     */
    public boolean sameElement(Card P1card, Card P2card){
        return P1card.getElement().equals(P2card.getElement());
    }

    /**
     * Determines if round should be draw due to same Value Cards being played.
     * @param P1card represents the card played by player 1
     * @param P2card represents the card played by player 2
     * @return boolean value, true if draw should be called
     */
    public boolean isSameAttack(Card P1card, Card P2card) {
       int result = P1card.getAttack() - P2card.getAttack();
        return result == 0;
    }



    /**
     * Helper function that converts element into integer. Fire is 1, water is 2, and grass is 3.
     *
     * @param card represents a selected card to determine element in int form.
     * @return integer representing the element.
     * @throws IllegalArgumentException if card does not have a valid element.
     */
    public int getCardElementValue(Card card){
        switch (card.getElement()){
            case "fire":
                return 1;
            case "water":
                return 2;
            case "grass":
                return 3;
            default:
                throw new IllegalArgumentException("card has invalid element type" + card.getElement());
        }
    }

    /**
     * Gets player1 in a match.
     * @return a Player representing the player1
     */
    public Player getPlayer1(){
        return this.player1;
    }

    /**
     * Gets player2 im a match.
     * @return a Player representing the player2
     */
    public Player getPlayer2(){
        return this.player2;
    }

    /**
     * Gets player 1 winning cards.
     * @return 2d array of Cards 3x3
     */
    public HashMap<Integer, ArrayList<Card>> getP1Wins(){
        return this.P1Wins;
    }

    /**
     * Gets player 2 winning Cards.
     * @return 2d array of Cards 3x3
     */
    public HashMap<Integer, ArrayList<Card>> getP2Wins(){
        return this.P2Wins;
    }
}