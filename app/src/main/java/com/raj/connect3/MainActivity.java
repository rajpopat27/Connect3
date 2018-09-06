package com.raj.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int gameState[]={2,2,2,2,2,2,2,2,2};
    boolean gameActive=true;
    //1=yellow,0=red
    int activePlayer=1;
    int winingPostitions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedcounter] == 2 && gameActive==true) {
            counter.setTranslationY(-1000f);
            gameState[tappedcounter]=activePlayer;
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer=0;
            } else if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer=1;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            System.out.println(gameState[tappedcounter]);
        }
        for(int winningCheck[]:winingPostitions){
            if (gameState[winningCheck[0]]==gameState[winningCheck[1]]&&gameState[winningCheck[0]]==gameState[winningCheck[2]]&&gameState[winningCheck[0]]!=2){
                gameActive=false;
                LinearLayout playAgain = (LinearLayout)findViewById(R.id.playAgainLayout);
                playAgain.setVisibility(View.VISIBLE);
                String message="Yellow";
                if(gameState[winningCheck[0]]==0){
                    message="Red";
                }
                TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                winnerMessage.setText(message+" Has Won"+"\n Congratulations!");
            }
            else{
                boolean drawState=true;
                for(int counterState:gameState){
                    if(counterState==2){
                        drawState=false;
                    }
                }
                if(drawState==true){
                    LinearLayout playAgain = (LinearLayout)findViewById(R.id.playAgainLayout);
                    playAgain.setVisibility(View.VISIBLE);
                    String message="It is a DRAW";
                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(message);
                }
            }
        }

    }
        public void playAgain(View view){
            activePlayer=1;
            gameActive=true;
            LinearLayout playAgainLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
            playAgainLayout.setVisibility(View.INVISIBLE);
            for(int i=0;i<gameState.length;i++){
                gameState[i]=2;
            }
            GridLayout counterLayout = (GridLayout)findViewById(R.id.counterLayout);
            for(int i=0;i<counterLayout.getChildCount();i++){
                ((ImageView)counterLayout.getChildAt(i)).setImageResource(0);
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
