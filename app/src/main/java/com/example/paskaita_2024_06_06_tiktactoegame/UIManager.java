package com.example.paskaita_2024_06_06_tiktactoegame;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class UIManager {

    private Context context;
    private ViewGroup rootView;
    private LinearLayout winningLine;
    private Button[] gameButtons;
    private Button resetButton;
    private TextView informationText;
    private TikTacToeRuling tikTacToeRuling;
    private ActivePlayer activePlayer;



    public UIManager(ViewGroup rootView, Context context) {
        tikTacToeRuling = new TikTacToeRuling();
        this.rootView = rootView;
        this.context = context;

        resetButton = rootView.findViewById(R.id.resetButton);
        gameButtons = new Button[9];
        for(int i = 0; i< 9; i++){
            int resID = context.getResources().getIdentifier("gameButton" + (i), "id", context.getPackageName());
            gameButtons[i] = rootView.findViewById(resID);
        }

        informationText = rootView.findViewById(R.id.informationText);
        activePlayer = ActivePlayer.RED;
        informationText.setTextColor(Color.rgb(255,0,0));
        informationText.setText("Red moves");

        winningLine = rootView.findViewById(R.id.winningLine);
        winningLine.setBackgroundColor(Color.rgb(255,0,0));

    }

    public void run(){
        resetButton.setOnClickListener(c -> resetGame());
        for(Button b : gameButtons){
            b.setOnClickListener(view -> clickGameButton(view));
        }
    }

    private void resetGame(){
        for(Button b : gameButtons){
            b.setAlpha(0);
            b.setEnabled(true);
        }
        tikTacToeRuling.reset();
        winningLine.setAlpha(0);
        setWinningLinePosition(-1);

    }

    private void clickGameButton(View view){

        int intID = view.getId();
        Button button = rootView.findViewById(intID);
        String stringID = context.getResources().getResourceName(intID);
        int buttonID = Integer.parseInt(stringID.substring(stringID.length()-1));

        tikTacToeRuling.makeAMove(buttonID, activePlayer);
        placePlayMark(button);
        checkWinCondition();

    }

    private void checkWinCondition(){
        int winCondition = tikTacToeRuling.checkWinCondition();

        if(tikTacToeRuling.checkWinCondition() != -1) {
            setWinningLinePosition(winCondition);
            if(activePlayer == ActivePlayer.RED){
                winningLine.setBackgroundColor(Color.rgb(23,126,225));
                winningLine.setAlpha(1);
                informationText.setText("Red lost");
            }else {
                winningLine.setBackgroundColor(Color.rgb(255,0,0));
                winningLine.setAlpha(1);
                informationText.setText("Blue Lost");

            }
            disableGameButtons();
        }

    }

    private void setWinningLinePosition(int winCondition){

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)winningLine.getLayoutParams();
        switch (winCondition){
            case 1:
                winningLine.setRotation(90);
                layoutParams.setMargins(0,0,0,650);
                break;
            case 2:
                winningLine.setRotation(90);
                layoutParams.setMargins(0,0,0,0);
                break;
            case 3:
                winningLine.setRotation(90);
                layoutParams.setMargins(0,650,0,0);
                break;
            case 4:
                layoutParams.setMargins(0,0,650,0);
                break;
            case 5:

                break;
            case 6:
                layoutParams.setMargins(650,0,0,0);
                break;
            case 7:
                winningLine.setRotation(135);
                break;
            case 8:
                winningLine.setRotation(45);
                break;
            default:
                layoutParams.setMargins(0,0,0,0);
                winningLine.setRotation(0);
                break;
        }
        winningLine.setLayoutParams(layoutParams);

    }

    private void placePlayMark(Button button){
        if(activePlayer == ActivePlayer.RED){
            button.setBackgroundResource(R.drawable.x_button);
            button.setEnabled(false);
            activePlayer = ActivePlayer.BLUE;
            informationText.setTextColor(Color.rgb(0,0,255));
            informationText.setText("Blue moves");
            button.setAlpha(1);
            resetButton.setBackgroundResource(R.drawable.reset_2);
        } else {
            button.setBackgroundResource(R.drawable.o_button);
            button.setEnabled(false);
            activePlayer = ActivePlayer.RED;
            informationText.setTextColor(Color.rgb(255,0,0));
            informationText.setText("Red moves");
            button.setAlpha(1);
            resetButton.setBackgroundResource(R.drawable.reset_1);
        }
    }

    private void disableGameButtons(){
        for(Button b : gameButtons){
            b.setEnabled(false);
        }
    }







}
