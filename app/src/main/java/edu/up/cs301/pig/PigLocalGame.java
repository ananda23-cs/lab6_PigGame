package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {
    private PigGameState pigGameState;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        //TODO  You will implement this constructor
        pigGameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        if(playerIdx == pigGameState.getPlayerNum()){
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //TODO  You will implement this method
        if(action instanceof PigHoldAction){
            if(pigGameState.getPlayerNum() == 0) {
                pigGameState.setPlayer0Score(pigGameState.getRunningTotal() + pigGameState.getPlayer0Score());
                pigGameState.setMessage(playerNames[0] + " added " + pigGameState.getRunningTotal() + " points to the final score.");
            }
            else if(pigGameState.getPlayerNum() == 1){
                pigGameState.setPlayer1Score(pigGameState.getRunningTotal() + pigGameState.getPlayer1Score());
                pigGameState.setMessage(playerNames[1] + " added " + pigGameState.getRunningTotal() + " points to the final score.");
            }

            pigGameState.setRunningTotal(0);
            if(players.length > 1){
                if(pigGameState.getPlayerNum() == 0) {
                    pigGameState.setPlayerNum(1);
                }
                else if(pigGameState.getPlayerNum() == 1){
                    pigGameState.setPlayerNum(0);
                }
            }
            return true;
        }
        else if(action instanceof PigRollAction){
            pigGameState.setDieValue((int) (1 + Math.random()*6));
            if(pigGameState.getDieValue() == 1){
                pigGameState.setRunningTotal(0);
                if(players.length > 1){
                    if(pigGameState.getPlayerNum() == 0) {
                        pigGameState.setMessage("Oh no! " + playerNames[0] + " rolled a 1 and lost everything! :(");
                        pigGameState.setPlayerNum(1);
                    }
                    else if(pigGameState.getPlayerNum() == 1){
                        pigGameState.setMessage("Oh no! " + playerNames[1] + " rolled a 1 and lost everything! :(");
                        pigGameState.setPlayerNum(0);
                    }
                }
                else{
                    pigGameState.setMessage("Oh no! " + playerNames[0] + " rolled a 1 and lost everything! :(");
                }
            }
            else{
                pigGameState.setRunningTotal(pigGameState.getRunningTotal()+pigGameState.getDieValue());
            }
            return true;
        }
        else { return false; }
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        PigGameState copy = new PigGameState(pigGameState);
        p.sendInfo(copy);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        if(pigGameState.getPlayer0Score() >= 50){
            return "Player 0 won with a score of " + pigGameState.getPlayer0Score() + "!";
        }
        else if(pigGameState.getPlayer1Score() >= 50){
            return "Player 1 won with a score of " + pigGameState.getPlayer1Score() + "!";
        }
        return null;
    }

}// class PigLocalGame
