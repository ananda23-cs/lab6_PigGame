package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    playerNameTextView  = null;
    private TextView    playerScoreTextView = null;
    private TextView    oppNameTextView     = null;
    private TextView    oppScoreTextView    = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private ImageButton dieImageButton      = null;
    private Button      holdButton          = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    private boolean holdButtonClicked = false;

    /**
     * constructor does nothing extra
     */
    public PigHumanPlayer(String name) { super(name); }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        //TODO You will implement this method to receive state objects from the game
        if(!(info instanceof PigGameState)){
            flash(Color.RED, 100);
            return;
        }
        else {
            if (playerNum == 0) {
                playerScoreTextView.setText(String.valueOf(((PigGameState) info).getPlayer0Score()));
                oppScoreTextView.setText(String.valueOf(((PigGameState) info).getPlayer1Score()));
            }
            if (playerNum == 1){
                playerScoreTextView.setText(String.valueOf(((PigGameState) info).getPlayer1Score()));
                oppScoreTextView.setText(String.valueOf(((PigGameState) info).getPlayer0Score()));
            }

            if(((PigGameState) info).getPlayerNum() == 0){
                dieImageButton.setBackgroundColor(Color.GREEN);
            }
            else if(((PigGameState) info).getPlayerNum() == 1){
                dieImageButton.setBackgroundColor(Color.BLUE);
            }

            if (((PigGameState) info).getDieValue() == 1) {
                dieImageButton.setImageResource(R.drawable.face1);
                messageTextView.setText(((PigGameState) info).getMessage());
            } else if (((PigGameState) info).getDieValue() == 2) {
                dieImageButton.setImageResource(R.drawable.face2);
            } else if (((PigGameState) info).getDieValue() == 3) {
                dieImageButton.setImageResource(R.drawable.face3);
            } else if (((PigGameState) info).getDieValue() == 4) {
                dieImageButton.setImageResource(R.drawable.face4);
            } else if (((PigGameState) info).getDieValue() == 5){
                dieImageButton.setImageResource(R.drawable.face5);
            } else {
                dieImageButton.setImageResource(R.drawable.face6);
            }

            if(holdButtonClicked) {
                messageTextView.setText(((PigGameState) info).getMessage());
            }
            turnTotalTextView.setText(String.valueOf(((PigGameState) info).getRunningTotal()));
        }
    }//receiveInfo

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        //TODO  You will implement this method to send appropriate action objects to the game
        if(button instanceof ImageButton){
            holdButtonClicked = false;
            game.sendAction(new PigRollAction(PigHumanPlayer.this));
        }
        else if(button instanceof Button){
            holdButtonClicked = true;
            game.sendAction(new PigHoldAction(PigHumanPlayer.this));
        }
    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        this.playerNameTextView  = (TextView)activity.findViewById(R.id.yourScoreText);
        this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppNameTextView     = (TextView)activity.findViewById(R.id.oppScoreText);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);

    }//setAsGui

    @Override
    protected void initAfterReady() {
        if(allPlayerNames.length == 1){
            playerNameTextView.setText(name + "'s score: ");
            oppNameTextView.setText("");
            oppScoreTextView.setVisibility(View.INVISIBLE);
        }
        else {
            if(playerNum == 0) {
                playerNameTextView.setText(allPlayerNames[0] + "'s score: ");
                oppNameTextView.setText(allPlayerNames[1] + "'s score: ");
            }
            else if(playerNum == 1){
                playerNameTextView.setText(allPlayerNames[1] + "'s score: ");
                oppNameTextView.setText(allPlayerNames[0] + "'s score: ");
            }
        }
        super.initAfterReady();
    }

}// class PigHumanPlayer
