package sample;

import javafx.scene.shape.Rectangle;

public class Players {
    private Rectangle[] players;
    private int playersNumber;
    private int playersAnswer;

    public int getPlayersAnswer() {
        return playersAnswer;
    }

    public void setPlayersAnswer(int playersAnswer) {
        this.playersAnswer = playersAnswer;
    }

    public void setPlayers(Rectangle[] players) {
        this.players = players;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    public Rectangle[] getPlayers() {
        return players;
    }
}
