package com.jongzazaal.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button startGame;
    EditText namePlayer;
    //Animation myAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        setContentView(R.layout.activity_main);
        namePlayer = (EditText) findViewById(R.id.namePlayer);
        startGame = (Button) findViewById(R.id.startGame);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.shake);
        final Animation myAnim2 = AnimationUtils.loadAnimation(this, R.anim.bounce);
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        myAnim2.setInterpolator(interpolator);
        startGame.startAnimation(myAnim);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(myAnim2);
                Intent i = new Intent(getApplicationContext(), playGame.class);
                i.putExtra("namePlayer",namePlayer.getText().toString() );

                startActivity(i);


            }
        });

    }
}
