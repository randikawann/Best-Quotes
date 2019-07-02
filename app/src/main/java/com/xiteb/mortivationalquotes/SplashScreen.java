package com.xiteb.mortivationalquotes;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiteb.mortivationalquotes.activity.MenuActivity;

public class SplashScreen extends AppCompatActivity {

    ImageView imgsplashanim;
    LinearLayout linear3, linear4;
    Button btgetstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imgsplashanim = findViewById(R.id.imgsplashanim);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        btgetstart = findViewById(R.id.btgetstart);

        changebackimage();
//        changegetstrat();

        btgetstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splashtomenu = new Intent(SplashScreen.this, MenuActivity.class);
                startActivity(splashtomenu);
            }
        });
        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splashtomenu = new Intent(SplashScreen.this, MenuActivity.class);
                startActivity(splashtomenu);
            }
        });

    }

    private void changegetstrat() {
//        Animation moveupgetstrat = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.move_upgetstart);
//        linear3.startAnimation(moveupgetstrat);
    }

    private void changebackimage() {
        final Animation scaleDown = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.scaledown);
        final Animation moveupgetstrat = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.move_upgetstart);
        final Animation movetext = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.move_text);

//        imgsplashanim.startAnimation(scaleDown);

        int delayBetweenAnim = 900;
        scaleDown.setStartOffset(delayBetweenAnim);
        movetext.setStartOffset(delayBetweenAnim);
        imgsplashanim.startAnimation(scaleDown);
        linear3.startAnimation(moveupgetstrat);
        linear4.startAnimation(movetext);


//        animationSet.addAnimation(scaleDown);
//        animationSet.addAnimation(moveupgetstrat);




//        scaleDown.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                linear3.startAnimation(moveupgetstrat);
//                imgsplashanim.startAnimation(scaleDown);
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

    }
}
