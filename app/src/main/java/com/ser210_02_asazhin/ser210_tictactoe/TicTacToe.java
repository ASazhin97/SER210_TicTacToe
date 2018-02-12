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
    @Override
    public void clearBoard() {
        // TODO Auto-generated method stub
        board = new int[ROWS][COLS];
    }

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

    public boolean getCellStatus(int location){
        boolean cellEmpty = true;
        int row = location/3;
        int col = location%3;

        if(board[row][col] != 0){
            cellEmpty = false;
        }


        return cellEmpty;
    }

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

    @Override
    public int getComputerMove() {
        // TODO Auto-generated method stub
        int row = 0;
        int col = 0;
        int moveLocation = 0;
        boolean moveFound = false;
        int count = 0;

        //check if oponnet is winning
        //right diagonal
        count = 0;
        for(int i = 0; i < 3; i++){
            if(board[i][2-i] == 1){
                count++;
            }

            if(board[i][2-i] == 0){
                moveLocation = returnLocation(i, 2-i);
            }
        }

        if(count == 2){
            moveFound = true;
        }


        //left diagonal
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
        }

        if(count == 2){
            moveFound = true;
        }



        //rows
        count = 0;
        int count2 = 0;
        if(!moveFound) {
            for (int i = 0; i < 3; i++) {
                count = 0;
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 1) {
                        count++;
                    }

                }

                if (count == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == 2) {
                            count2++;
                        }

                        if (board[i][j] == 0) {
                            moveLocation = returnLocation(i, j);
                        }
                    }

                }

                if (count == 2 & count2 == 0) {
                    moveFound = true;
                    break;
                }

            }
        }

        //columns
        count = 0;
        count2 = 0;
        if(!moveFound) {
            for (int i = 0; i < 3; i++) {
                count = 0;
                for (int j = 0; j < 3; j++) {
                    if (board[j][i] == 1) {
                        count++;
                    }

                }

                if (count == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (board[j][i] == 2) {
                            count2++;
                        }

                        if (board[j][i] == 0) {
                            moveLocation = returnLocation(j, i);
                        }
                    }

                }

                if (count == 2 & count2 == 0) {
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
        for(int i = 0; i<9; i++){
            if(!getCellStatus(i)){
                slotsLeft--;
            }
        }

        if(slotsLeft == 0){
            winner = 1;
        }

        return winner;
    }

    /**
     *  Print the game board
     */
//    public  void printBoard() {
//        for (int row = 0; row < ROWS; ++row) {
//            for (int col = 0; col < COLS; ++col) {
//                printCell(board[row][col]); // print each of the cells
//                if (col != COLS - 1) {
//                    System.out.print("|");   // print vertical partition
//                }
//            }
//            System.out.println();
//            if (row != ROWS - 1) {
//                System.out.println("-----------"); // print horizontal partition
//            }
//        }
//        System.out.println();
//    }
//
//    /**
//     * Print a cell with the specified "content"
//     * @param content either CROSS, NOUGHT or EMPTY
//     */
//    public  void printCell(int content) {
//        switch (content) {
//            case EMPTY:  System.out.print("   "); break;
//            case NOUGHT: System.out.print(" O "); break;
//            case CROSS:  System.out.print(" X "); break;
//        }
//    }

}
