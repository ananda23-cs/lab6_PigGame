package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
    private int playerNum;
    private int player0Score, player1Score;
    private int dieValue;
    private int runningTotal;
    private String message;

    public PigGameState(){
        this.playerNum = 0;
        this.player0Score = 0;
        this.player1Score = 0;
        this.runningTotal = 0;
        this.dieValue = 0;
        this.message = "";
    }

    public PigGameState(PigGameState pig){
        this.playerNum = pig.playerNum;
        this.player0Score = pig.player0Score;
        this.player1Score = pig.player1Score;
        this.runningTotal = pig.runningTotal;
        this.dieValue = pig.dieValue;
        this.message = pig.message;
    }

    public int getDieValue() {
        return dieValue;
    }

    public int getPlayer0Score() {
        return player0Score;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public int getRunningTotal() {
        return runningTotal;
    }

    public String getMessage() {
        return message;
    }

    public void setDieValue(int dieValue) {
        this.dieValue = dieValue;
    }

    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public void setRunningTotal(int runningTotal) {
        this.runningTotal = runningTotal;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
