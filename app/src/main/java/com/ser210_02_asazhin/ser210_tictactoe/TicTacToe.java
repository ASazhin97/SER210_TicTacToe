package com.ser210_02_asazhin.ser210_tictactoe;

/**
 * Created by alexa on 2/10/2018.
 */

public class TicTacToe implements ITicTacToe {

    // The game board and the game status
    private static final int ROWS = 3, COLS = 3; // number of rows and columns
    private int[][] board = new int[ROWS][COLS]; // game board in 2D array
    private int[] bestMoves = new int[9]; //moves in prefered order

    /**
     * clear board and set current player
     */
    public TicTacToe(){

    }

    /*
    this cleares the array to start new match
     */
    @Override
    public void clearBoard() {
        // TODO Auto-generated method stub
        board = new int[ROWS][COLS];
    }

    /*
    this transforms the board array into a single array
    to give to the front end for saving
     */
    public int[] getBoard(){
        int[] boardArray = new int[9];
        int a = 0;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                boardArray[a] = board[i][j];
                a++;
            }
        }

        return boardArray;
    }

    /*
    This takes the single array from the save,
    and turns it in to the two dimensional array
    to bring back the game
     */
    public void setBoard(int[] newBoard){
        int a = 0;
        for(int i = 0; i<3; i++){
            for(int j = 0; j < 3 ; j++){
                board[i][j] = newBoard[a];
                a++;
            }
        }
    }

    /*
    This sets the players move into the array
     */
    @Override
    public void setMove(int player, int location) {
        // TODO Auto-generated method stub
        int row = location/3;
        //System.out.println(row);
        int col = location%3;
        //System.out.println(col);

        if(board[row][col] == 0){
            if(player == 1){
                board[row][col] = 1;
            }

            if(player == 2){
                board[row][col] = 2;
            }
        }
    }

    /*
    This returns if the cell is empty or not, used
    to tell if another element can be put there
     */
    public boolean getCellStatus(int location){
        boolean cellEmpty = true;
        int row = location/3;
        int col = location%3;

        if(board[row][col] != 0){
            cellEmpty = false;
        }


        return cellEmpty;
    }

    /*
    this return the single 0-8 location
    from being the 2 dimensional loc
     */
    public int returnLocation(int a, int b){
        int Loc = 0;

        for(int i = 0; i < 9; i++){
            int row = i/3;
            int col = i%3;

            if(a == row && b == col){
                Loc = i;
                break;
            }
        }


        return Loc;
    }

    /*
    This calculates the move of the computer
    it checks if the player is about to win and stops it.
    If the player is not allowed to win it follows an
    optimal pattern
     */
    @Override
    public int getComputerMove() {
        // TODO Auto-generated method stub
        int row = 0;
        int col = 0;
        int moveLocation = 0;
        boolean moveFound = false;
        int count = 0;
        int count2=0;

        //check if oponnet is winning
        //right
        count = 0;
        if(moveFound == false){
            for(int i = 0; i < 3; i++){
                if(board[i][2-i] == 1){
                    count++;
                }

                if(board[i][2-i] == 0){
                    moveLocation = returnLocation(i, 2-i);
                }
            }

            if(count == 2 && getCellStatus(moveLocation)){
                moveFound = true;
            }

        }


        //left
        if(moveFound == false){
            count = 0;
            for(int i = 0; i < 3; i++){
                if(board[i][i] == 1){
                    count++;
                }

                if(board[i][i] == 0){
                    moveLocation = returnLocation(i, i);
                }
            }


            if(count == 2 && getCellStatus(moveLocation)){
                moveFound = true;
            }
        }



        //rows
        count = 0;
        count2 = 0;
        if(moveFound == false){
            for(int i = 0; i < 3; i++){
                count = 0;
                for(int j = 0; j < 3; j++){
                    if(board[i][j] == 1){
                        count++;
                    }

                }

                if(count == 2){
                    for(int j = 0; j<3; j++){
                        if(board[i][j]== 2){
                            count2++;
                        }

                        if(board[i][j] == 0){
                            moveLocation = returnLocation(i,j);
                        }
                    }

                }

                if(count == 2 & count2 == 0 && getCellStatus(moveLocation)){
                    moveFound = true;
                    break;
                }

            }
        }


        //columns
        count = 0;
        count2 = 0;
        if(moveFound == false){
            for(int i = 0; i < 3; i++){
                count = 0;
                for(int j = 0; j < 3; j++){
                    if(board[j][i] == 1){
                        count++;
                    }

                }

                if(count == 2){
                    for(int j = 0; j<3; j++){
                        if(board[j][i]== 2){
                            count2++;
                        }

                        if(board[j][i] == 0){
                            moveLocation = returnLocation(j,i);
                        }
                    }

                }

                if(count == 2 & count2 == 0 && getCellStatus(moveLocation)){
                    moveFound = true;
                    break;
                }

            }
        }



        //do prefered moves if the previous options dont yeild a move
        //set the best preferred moves


        if(moveFound == false){
            bestMoves[0] = 4;
            bestMoves[1] = 0;
            bestMoves[2] = 5;
            bestMoves[3] = 6;
            bestMoves[4] = 1;
            bestMoves[5] = 7;
            bestMoves[6] = 2;
            bestMoves[7] = 8;
            bestMoves[8] = 3;

            int i = 0;

            while(!moveFound && i<9){
                boolean cellStatus = getCellStatus(bestMoves[i]);
                //System.out.println(cellStatus);
                if(cellStatus){
                    moveLocation = bestMoves[i];
                    //System.out.println(moveLocation);
                    moveFound = true;
                }

                i++;
            }
        }

        System.out.println(moveLocation);
        return moveLocation;

    }

    /*
    Checks for the winner of the match,
    and returns a draw if no winner and the board is full
     */
    @Override
    public int checkForWinner() {

        int winner = 0;


        int count1 = 0;
        int count2 = 0;
        //check diagonal left
        for(int i = 0; i < 3; i++){
            if(board[i][i] == 1) count1++;
            if(board[i][i] == 2) count2++;
        }

        if(count1 == 3) winner = 2;
        if(count2 == 3) winner = 3;


        //check diagonal right
        count1 = 0;
        count2 = 0;
        int t = 0;
        for(int i = 2; i >= 0; i--){
            if(board[i][t] == 1) count1++;
            if(board[i][t] == 2) count2++;
            t++;
        }

        if(count1 == 3) winner = 2;
        if(count2 == 3) winner = 3;


        //row
        //System.out.println("row");

        for(int i = 0; i<3; i++){
            count1 = 0;
            count2 = 0;
            for(int j = 0; j<3; j++){
                if(board[i][j] == 1) count1++;
                if(board[i][j] == 2) count2++;
            }


            if(count1 == 3) {
                winner = 2;
                break;
            } else if(count2 == 3) {
                winner = 3;
                break;
            }


        }



        //check each column

        for(int i = 0; i<3; i++){
            count1 = 0;
            count2 = 0;
            for(int j = 0; j<3; j++){
                if(board[j][i] == 1) count1++;
                if(board[j][i] == 2) count2++;
            }

            if(count1 == 3) {
                winner = 2;
                break;
            } else if(count2 == 3) {
                winner = 3;
                break;
            }

        }

        //if no winner found, check if the board is full
        int slotsLeft = 9;
        if(winner == 0) {
            for (int i = 0; i < 9; i++) {
                if (!getCellStatus(i)) {
                    slotsLeft--;
                }
            }

            if (slotsLeft == 0) {
                winner = 1;
            }
        }

        return winner;
    }


}
