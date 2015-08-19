package com.example.randomguy.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

/**
 * Created by randomguy on 31.07.2015.
 */
public class GameActivity extends ActionBarActivity{
    TextView gameText;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameText = (TextView)findViewById(R.id.gameView);
        startAnimation();
    }
    private void startAnimation(){
        Animation fallingAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fallinganim);
        Animation topToBottomAnimation = new TranslateAnimation(-200,-200,0,1600);
        topToBottomAnimation.setDuration(10000);
        gameText.startAnimation(topToBottomAnimation);
        //gameText.startAnimation(fallingAnimation);



    }
}
