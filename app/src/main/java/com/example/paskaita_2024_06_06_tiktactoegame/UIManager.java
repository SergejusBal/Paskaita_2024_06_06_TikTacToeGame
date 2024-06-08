package com.example.paskaita_2024_06_06_tiktactoegame;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class UIManager {

    private Context context;
    private ViewGroup rootView;
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
        activePlayer = ActivePlayer.Red;
        informationText.setTextColor(Color.rgb(255,0,0));
        informationText.setText("Red moves");
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
    }

    private void clickGameButton(View view){
        int intID = view.getId();
        Button button = rootView.findViewById(intID);

        String stringID = context.getResources().getResourceName(intID);
        int buttonID = Integer.parseInt(stringID.substring(stringID.length()-1));
        tikTacToeRuling.makeMove(buttonID,activePlayer);

        if(activePlayer == ActivePlayer.Red){
            button.setBackgroundResource(R.drawable.x_button);
            button.setEnabled(false);
            activePlayer = ActivePlayer.Blue;
            informationText.setTextColor(Color.rgb(0,0,255));
            informationText.setText("Blue moves");
            button.setAlpha(1);
            resetButton.setBackgroundResource(R.drawable.reset_2);
        } else {
            button.setBackgroundResource(R.drawable.o_button);
            button.setEnabled(false);
            activePlayer = ActivePlayer.Red;
            informationText.setTextColor(Color.rgb(255,0,0));
            informationText.setText("Red moves");
            button.setAlpha(1);
            resetButton.setBackgroundResource(R.drawable.reset_1);
        }

        if(tikTacToeRuling.checkWinCondition() != -1) {
            informationText.setText("Game over");
            disableGameButtons();
        }



    }

    private void disableGameButtons(){
        for(Button b : gameButtons){
            b.setEnabled(false);
        }
    }







}
