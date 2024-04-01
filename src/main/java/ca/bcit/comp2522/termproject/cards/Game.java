package ca.bcit.comp2522.termproject.cards;

public class Game {
    private final Player player1;
    private final Player player2;
    Game(Player humanPlayer) {
        this.player1 = humanPlayer;
        this.player2 = new AIPlayer("Ninja Rat");
    }
    public Player getPlayer1() {
        return this.player1;
    }
    public Player getPlayer2() {
        return this.player2;
    }

}
