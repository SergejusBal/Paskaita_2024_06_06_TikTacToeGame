package com.example.paskaita_2024_06_06_tiktactoegame;


public class TikTacToeRuling {


    ActivePlayer[] boardValues;

    public TikTacToeRuling() {
        boardValues = new ActivePlayer[9];
    }

    public void reset(){
        boardValues = new ActivePlayer[9];
    }

    public void makeMove(int tileNumber, ActivePlayer activePlayer){
        if(tileNumber < 9 && tileNumber >= 0)
            boardValues[tileNumber] = activePlayer;
    }

    /**
     *  -1      no winning conditions
     *  1 - 3   rows has winning conditions
     *  4 - 6   columns winning conditions
     *  7 - 8   diagonals winning conditions
     */
    public int checkWinCondition(){
        if(checkWinConditionRows() != -1) return checkWinConditionRows();
        if(checkWinConditionColumns() != -1) return checkWinConditionColumns();
        if(checkWinConditionDiagonals() != -1) return checkWinConditionDiagonals();
        return -1;
    }

    private int checkWinConditionRows(){

        for(int i = 0; i < 3; i++){
            if(
                    boardValues[i * 3] == boardValues[1 + i * 3] &&
                    boardValues[1 + i * 3] == boardValues[2 + i * 3] &&
                    boardValues[i * 3] != null
            ){
                return i + 1;
            }
        }
        return -1;
    }
    private int checkWinConditionColumns(){

        for(int i = 0; i < 3; i++){
            if(
                    boardValues[i] == boardValues[3 + i] &&
                    boardValues[3 + i] == boardValues[6 + i] &&
                    boardValues[i] != null
            ){
                return 3 + i;
            }
        }
        return -1;
    }

    private int checkWinConditionDiagonals(){

        if(
                    boardValues[0] == boardValues[4] &&
                    boardValues[4] == boardValues[8] &&
                    boardValues[0] != null
        ){
            return 7;
        }
        else if(
                    boardValues[2] == boardValues[4] &&
                    boardValues[4] == boardValues[6] &&
                    boardValues[2] != null
        ){
            return 8;
        }

        return -1;
    }



}
