package com.jongzazaal.game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by jongzazaal on 7/10/2559.
 */

public class playGame extends AppCompatActivity {

    int rand;
    String sRand;
    Button punch, cut, paper;
    TextView name, win, draw, lose;
    ScoreGame sc;
    ImageView picRand;

    PieGraph pg;
    PieSlice slice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);

        sc = new ScoreGame();

        name = (TextView) findViewById(R.id.name);
        Intent i = getIntent();
       // String a = i.getStringExtra("namePlayer");
        name.setText(i.getStringExtra("namePlayer"));

        punch = (Button)findViewById(R.id.punch);
        cut = (Button) findViewById(R.id.cut);
        paper = (Button) findViewById(R.id.paper);

        win = (TextView) findViewById(R.id.win);
        draw = (TextView) findViewById(R.id.draw);
        lose = (TextView) findViewById(R.id.lose);

        picRand=(ImageView) findViewById(R.id.picRand);

        final Animation animeButton = AnimationUtils.loadAnimation(this, R.anim.bounce);
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        animeButton.setInterpolator(interpolator);

        final Animation animeButton2 = AnimationUtils.loadAnimation(this, R.anim.bounce);
        //BounceInterpolator interpolator2 = new BounceInterpolator(0.2, 20);
        animeButton2.setInterpolator(interpolator);

        final Animation animeButton3 = AnimationUtils.loadAnimation(this, R.anim.bounce);
        //BounceInterpolator interpolator3 = new BounceInterpolator(0.2, 20);
        animeButton3.setInterpolator(interpolator);

        punch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check("punch");
                v.startAnimation(animeButton);


            }
        });

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check("cut");
                v.startAnimation(animeButton2);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check("paper");
                v.startAnimation(animeButton3);
            }
        });


        pg = (PieGraph)findViewById(R.id.graph);
        slice = new PieSlice();




    }

    public void grap(){
        pg.removeSlices();
        slice.setColor(Color.parseColor("#99CC00"));
        slice.setValue(sc.getWin());
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#FFBB33"));
        slice.setValue(sc.getDraw());
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#AA66CC"));
        slice.setValue(sc.getLose());
        pg.addSlice(slice);
        pg.isShown();



    }

    public void check(String c){

        String r = random_int();
        if (c.equals(r)){
            sc.setDraw(sc.getDraw()+1);
        }
        else if((c.equals("punch") && r.equals("cut")) || (c.equals("cut") && r.equals("paper")) || (c.equals("paper") && r.equals("punch")) ){
            sc.setWin(sc.getWin()+1);
        }
        else {
            sc.setLose(sc.getLose()+1);
        }
        updateui();

    }

    public void updateui(){
        //Integer iWin = Integer.valueOf(win.getText().toString().split(" ")[1]);
        //Integer iDraw = Integer.valueOf(draw.getText().toString().split(" ")[1]);
        //Integer iLose = Integer.valueOf(lose.getText().toString().split(" ")[1]);

        win.setText("Win: "+sc.getWin());
        draw.setText("Draw: "+sc.getDraw());
        lose.setText("Lose: "+sc.getLose());
        grap();
    }


    public String random_int(){

        Random random = new Random();
        rand = (random.nextInt(2 - 0 + 1) + 0);
        //randomshow.setText(   show + ""    );
        if(rand==0){
            sRand="punch";
            picRand.setImageResource(R.drawable.punch);
        }
        else if (rand==1){
            sRand="cut";
            picRand.setImageResource(R.drawable.cut);
        }
        else {sRand="paper";picRand.setImageResource(R.drawable.paper);}
        return sRand;
        //rand.nextInt((max - min) + 1) + min;
    }

    Toast m_currentToast;
    void showToast(String text)
    {
        if(m_currentToast == null)
        {
            m_currentToast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        }

        m_currentToast.setText(text);
        m_currentToast.setDuration(Toast.LENGTH_LONG);
        m_currentToast.show();
    }

}
