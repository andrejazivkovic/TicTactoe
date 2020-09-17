package com.example.igra_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int activePlayer=1;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int [][]winningCombinations={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean status=true;


    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int counterTaped = Integer.parseInt(counter.getTag().toString());
        System.out.println(counterTaped);
        if (gameState[counterTaped] == 2 && status) {
            gameState[counterTaped]=activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int[] winningPos : winningCombinations){
                if(gameState[winningPos[0]]==gameState[winningPos[1]] && gameState[winningPos[1]]==gameState[winningPos[2]] && gameState[winningPos[0]]!=2) {
                    status=false;
                    if (gameState[winningPos[0]]== 1) {
                        Toast.makeText(MainActivity.this, "THE WINNER IS RED!", Toast.LENGTH_SHORT).show();
                        TextView pocniOdN= (TextView) findViewById(R.id.textView2);
                        ImageView slikaReF=(ImageView) findViewById(R.id.imageView10);
                        pocniOdN.setAlpha(1f);
                        slikaReF.setAlpha(1f);

                    }
                    else if (gameState[winningPos[0]] == 0){
                        Toast.makeText(MainActivity.this, "THE WINNER IS YELLOW!", Toast.LENGTH_SHORT).show();
                        TextView pocniOdN= (TextView) findViewById(R.id.textView2);
                        ImageView slikaReF=(ImageView) findViewById(R.id.imageView10);
                        pocniOdN.setAlpha(1f);
                        slikaReF.setAlpha(1f);

                    }
                }
                else {
                    boolean iSGmaeOver=true;
                    for ( int checker : gameState){
                        if(checker == 2){
                            iSGmaeOver=false;
                        }
                    }
                    if(iSGmaeOver){
                        TextView pocniOdN= (TextView) findViewById(R.id.textView2);
                        ImageView slikaReF=(ImageView) findViewById(R.id.imageView10);
                        pocniOdN.setAlpha(1f);
                        slikaReF.setAlpha(1f);
                        Toast.makeText(MainActivity.this, "DRAWW!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    public void playAgain(View view){
        status=true;
        TextView pocniOdN = (TextView) findViewById(R.id.textView2);
        ImageView slikaReF = (ImageView) findViewById(R.id.imageView10);
        pocniOdN.setAlpha(0f);
        slikaReF.setAlpha(0f);
        activePlayer = 1;
        Arrays.fill(gameState, 2);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
