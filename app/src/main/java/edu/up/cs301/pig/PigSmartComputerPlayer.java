package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

public class PigSmartComputerPlayer extends GameComputerPlayer {
    /**
     * ctor does nothing extra
     */
    public PigSmartComputerPlayer(String name) {
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
        sleep(1000);
        PigGameState smartAiState;
        if(info instanceof PigGameState){
            smartAiState = new PigGameState((PigGameState) info);
            if(smartAiState.getPlayerNum() == playerNum) {
                while (smartAiState.getRunningTotal() < 20) {
                    PigRollAction roll = new PigRollAction(PigSmartComputerPlayer.this);
                    game.sendAction(roll);
                }
                PigHoldAction hold = new PigHoldAction(PigSmartComputerPlayer.this);
                game.sendAction(hold);
            }
            else{
                return;
            }
        }
    }
}
