package com.example.connect3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int [] gameState={3,3,3,3,3,3,3,3,3,3};
    int [][] winningPositionS = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}}; // declare the winning positions
    int turns = 1;

    boolean gameACtive = true;
    public void dropIn (View view){
        ImageView counter = (ImageView) view;
        TextView winnerT = findViewById(R.id.winningmessege);
        Button playagain =findViewById(R.id.playAgain);

        int tapped = Integer.parseInt(counter.getTag().toString()); // get the tag of each ImageView
            if (gameState[tapped] == 3 && gameACtive) {
                counter.setTranslationY(-3000);
                gameState[tapped] = turns;// to update the gameState with the Turns YELLOW OR RED

                //Toast.makeText(this, counter.getTag().toString(), Toast.LENGTH_SHORT).show();
                if (turns == 1) {
                    turns = 2;
                    counter.setImageResource(R.drawable.yellow);
                } else if (turns == 2) {
                    turns = 1;
                    counter.setImageResource(R.drawable.red);
                }
                counter.animate().translationYBy(3000).rotation(3600).setDuration(600);

                // check if a winning position has the same values:  1:red or 2:yellow and not equal to 3:empty
                for (int[] winningPosition : winningPositionS) {
                    // get the position value from the gameState :-
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 3) {
                        String winner = "";
                        if (turns == 1) {
                            winner = "RED";
                        } else if (turns==2) {
                            winner = "YELLOW";
                        }

                        winnerT.setText(winner + " HAS WON");
                        winnerT.setVisibility(View.VISIBLE);
                        playagain.setVisibility(View.VISIBLE);
                        gameACtive = false;
                    }


                }
            }
    }

    public void playAgain(View view){
        TextView winnerT = findViewById(R.id.winningmessege);
        Button playagain =findViewById(R.id.playAgain);
        winnerT.setVisibility(View.INVISIBLE);
        playagain.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout=findViewById(R.id.gridLayout);

         for (int i=0;  i<gridLayout.getChildCount()  ;i++){
             ImageView counter =(ImageView)gridLayout.getChildAt(i);
             counter.setImageDrawable(null);
         }

         for (int i=0 ; i<gameState.length; i++){
             gameState[i]=3;
         }
         turns = 1;
         gameACtive = true;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}