package com.ser210_02_asazhin.ser210_tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameActivity extends Activity {

    //VARIABLES
    Boolean _isPlayerTurn;
    TextView _promptView;

    int _move;
    TicTacToe TTTboard;
    ImageButton[] buttonArray;
    String _name;

    int[] board;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        //SET UP YOUR VARIABLES
        _promptView = (TextView) findViewById(R.id.prompt);
        TTTboard = new TicTacToe();

        _name = getIntent().getStringExtra("playerName");
        Log.d("name of player", "name is" + _name);
        _promptView.setText("Hello "+ _name + "! Click to play!");


        //set up array

        buttonArray = new ImageButton[9];
        buttonArray[0] = (ImageButton) findViewById(R.id.button00);
        buttonArray[1] = (ImageButton) findViewById(R.id.button01);
        buttonArray[2] = (ImageButton) findViewById(R.id.button02);
        buttonArray[3] = (ImageButton) findViewById(R.id.button10);
        buttonArray[4] = (ImageButton) findViewById(R.id.button11);
        buttonArray[5] = (ImageButton) findViewById(R.id.button12);
        buttonArray[6] = (ImageButton) findViewById(R.id.button20);
        buttonArray[7] = (ImageButton) findViewById(R.id.button21);
        buttonArray[8] = (ImageButton) findViewById(R.id.button22);

        /*
        This saved instance makes sure that the screen stays
        when the phone is flipped
         */
        if(savedInstanceState != null){
            TTTboard.setBoard(savedInstanceState.getIntArray("boardArray"));
            board = TTTboard.getBoard();
            for(int i = 0; i<board.length; i++){
                if(board[i] == 1){
                    buttonArray[i].setBackgroundResource(R.drawable.cross);
                }

                if(board[i] == 2){
                    buttonArray[i].setBackgroundResource(R.drawable.circle);
                }
            }
        }
    }


    public void onButtonClick(View view){
        /*
        When clicked in valid spot change image and report to the TTTboard
        to do the back end
         */
        if(TTTboard.checkForWinner() == 0) {
            for (int i = 0; i < 9; i++) {
                if (view == buttonArray[i] && TTTboard.getCellStatus(i)) {
                    buttonArray[i].setBackgroundResource(R.drawable.cross);
                    _move = i;
                    playStep(_move);
                }
            }


        }

    }

    public void onResetClick(View view){
        /*
        If clicked on reset
        and the board is full
        put a an array of all zeores into the
        board array
        and change all the images to white
         */
        int[] nullBoard = new int[9];
        if(TTTboard.checkForWinner() != 0) {
            for (int i = 0; i < 9; i++) {
                buttonArray[i].setBackgroundResource(R.drawable.white);
            }
            TTTboard.setBoard(nullBoard);
            _promptView.setText("Play again!");
        }

    }

    public void playStep(int playerMove) {
        /*
        Every time the a button is pressed in a valid grid
        you make the computer play its own move
        then check for the winner again
         */
        int compMove = 0;
        TTTboard.setMove(1, playerMove);


        if (TTTboard.checkForWinner() == 0) {
            compMove = TTTboard.getComputerMove();
            TTTboard.setMove(2, compMove);
            //_promptView.setText(compMove);
            Log.d("ComputerMove", Integer.toString(compMove));

            buttonArray[compMove].setBackgroundResource(R.drawable.circle);

        }
        if (TTTboard.checkForWinner() == 1) {
            _promptView.setText("It is a tie");
        }
        if(TTTboard.checkForWinner() == 2) {
            _promptView.setText("Cross Won!");
        }
        if(TTTboard.checkForWinner() == 3){
            _promptView.setText("Naught Won!");
        }
    }

    //making the board stay when flipped
    @Override
    public void onSaveInstanceState(Bundle savedInstantceState){
        super.onSaveInstanceState(savedInstantceState);
        board = TTTboard.getBoard();
        savedInstantceState.putIntArray("boardArray", board);
    }

}
