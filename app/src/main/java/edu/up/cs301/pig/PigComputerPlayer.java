package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.GameState;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // TODO  You will implement this method
        sleep(2000);
        PigGameState aiState;
        if(info instanceof PigGameState) {
            aiState = new PigGameState((PigGameState) info);
            if(aiState.getPlayerNum() == playerNum){
                int action = (int) (1 + Math.random()*2);
                switch (action){
                    case 1: //hold
                        PigHoldAction hold = new PigHoldAction(PigComputerPlayer.this);
                        game.sendAction(hold);
                    case 2: //roll
                        PigRollAction roll = new PigRollAction(PigComputerPlayer.this);
                        game.sendAction(roll);
                }
            }
            else{
                return;
            }
        }
    }//receiveInfo

}
