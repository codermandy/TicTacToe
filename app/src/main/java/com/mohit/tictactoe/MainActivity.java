package com.mohit.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //player representation
    //0 - x
    //1 - o
    int activePlayer = 0;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //State meaning
    //0 - x
    //1 - o
    //2 - null
    int winPos[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 4, 8}, {2, 4, 6}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8}};
    boolean gameActive = true;
    boolean emptySquare = false;
    String winnerPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playerTap(View view) {
        ImageView imageView = (ImageView) view;
        int tappedImage = Integer.parseInt(imageView.getTag().toString());
        if (!gameActive) {
            gameReset(view);
        }
        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            imageView.setTranslationY(-1000f);
            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.status);
                status.setText("O's turn - tap to play");
                activePlayer = 1;
            } else {
                imageView.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's turn - tap to play");
            }
            imageView.animate().translationYBy(1000f).setDuration(300);
        }
        //to check if anybody has won
        for (int[] winPosition : winPos) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                //player has won
                //which player?
                if (gameState[winPosition[0]] == 0) {
                    winnerPlayer = "X has won the game";
                    gameActive = false;
                } else {
                    winnerPlayer = "O has won the game";
                    gameActive = false;
                }
                //update the status bar for winner
                TextView status = findViewById(R.id.status);
                status.setText(winnerPlayer);
            }
            for (int squareState : gameState) {
                if (squareState == 2) {
                    emptySquare = true;
                    break;
                }
                if (!emptySquare && gameActive) {
                    gameActive = false;
                    winnerPlayer = "It's a draw";


                }

            }
        }


    }
    private void gameReset (View view){
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);

        }
    }
    }