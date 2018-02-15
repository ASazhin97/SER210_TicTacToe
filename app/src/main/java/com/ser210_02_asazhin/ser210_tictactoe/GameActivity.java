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
    ImageButton _button00;
    ImageButton _button01;
    ImageButton _button02;
    ImageButton _button10;
    ImageButton _button11;
    ImageButton _button12;
    ImageButton _button20;
    ImageButton _button21;
    ImageButton _button22;
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
        _button00 = (ImageButton) findViewById(R.id.button00);
        _button01 = (ImageButton) findViewById(R.id.button01);
        _button02 = (ImageButton) findViewById(R.id.button02);
        _button10 = (ImageButton) findViewById(R.id.button10);
        _button11 = (ImageButton) findViewById(R.id.button11);
        _button12 = (ImageButton) findViewById(R.id.button12);
        _button20 = (ImageButton) findViewById(R.id.button20);
        _button21 = (ImageButton) findViewById(R.id.button21);
        _button22 = (ImageButton) findViewById(R.id.button22);
        TTTboard = new TicTacToe();

        _name = getIntent().getStringExtra("playerName");
        Log.d("name of player", "name is" + _name);
        _promptView.setText("Hello "+ _name + "! Click to play!");


        //set up array

        buttonArray = new ImageButton[9];
        buttonArray[0] = _button00;
        buttonArray[1] = _button01;
        buttonArray[2] = _button02;
        buttonArray[3] = _button10;
        buttonArray[4] = _button11;
        buttonArray[5] = _button12;
        buttonArray[6] = _button20;
        buttonArray[7] = _button21;
        buttonArray[8] = _button22;

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

    public void changePrompt(){
        if(_isPlayerTurn){
            _promptView.setText("It is YOur turn");
        } else {
            _promptView.setText("It is the computers turn!");
        }
    }

    public void onButtonClick(View view){
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

    public void playStep(int playerMove) {
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

    @Override
    public void onStop(){
        super.onStop();
    }
    public void onStart(){
        super.onStart();
    }
}
