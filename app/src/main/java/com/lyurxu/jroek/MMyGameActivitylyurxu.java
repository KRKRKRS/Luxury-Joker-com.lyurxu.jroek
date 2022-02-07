package com.lyurxu.jroek;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MMyGameActivitylyurxu extends AppCompatActivity {
    ImageView wheel;
    ImageView result1;
    ImageView result2;
    ImageView result3;
    ImageView result4;
    ImageView result5;
    TextView textView;
    Button btnStart;
    boolean isSpinning;
    float[] sectors = {45f, 90f, 135f, 180f, 225f, 270f, 315f, 360f};
    int[] results = {R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d1};
    String [] texts = {"Golden Slumbers", "Strawberry Fields Forever", "Come together", "Watermelon Sugar", "The Sugar Plum Fairy", " Lemon Tree", "Ah, girl", "Watching The Wheels"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmy_game);
        wheel = findViewById(R.id.wheel);
        result1 = findViewById(R.id.resultImage1);
        result2 = findViewById(R.id.resultImage2);
        result3 = findViewById(R.id.resultImage3);
        result4 = findViewById(R.id.resultImage4);
        result5 = findViewById(R.id.resultImage5);
        textView = findViewById(R.id.textView);
        btnStart = findViewById(R.id.btn_start);
    }

    public void startWheel(View view) {
        if (!isSpinning) {
            isSpinning = true;
            spin();
            btnStart.setText("Another spin!");
        }
    }

    public void spin() {
        Random random = new Random();
        int rand = random.nextInt(7) + 1;
        float sector = sectors[rand];

        float mAngleToRotate = 360f * 5; // rotate 5 rounds
        RotateAnimation wheelRotation = new RotateAnimation(0.0f, mAngleToRotate + sector, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        wheelRotation.setDuration(3000); // rotate 8 rounds in 3 seconds
        wheelRotation.setInterpolator(this, android.R.interpolator.accelerate_decelerate);
        wheelRotation.setFillAfter(true);
        wheelRotation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
                result1.setImageResource(results[rand]);
                result2.setImageResource(results[rand]);
                result3.setImageResource(results[rand]);
                result4.setImageResource(results[rand]);
                result5.setImageResource(results[rand]);
                result1.startAnimation(slideUp);
                result2.startAnimation(slideUp);
                result3.startAnimation(slideUp);
                result4.startAnimation(slideUp);
                result5.startAnimation(slideUp);
                isSpinning = false;
                textView.setText(texts[rand]);

                slideUp.setAnimationListener(new Animation.AnimationListener() {

                                                 @Override
                                                 public void onAnimationStart(Animation animation) {

                                                 }

                                                 @Override
                                                 public void onAnimationEnd(Animation animation) {
                                                     result1.setVisibility(View.INVISIBLE);
                                                     result2.setVisibility(View.INVISIBLE);
                                                     result3.setVisibility(View.INVISIBLE);
                                                     result4.setVisibility(View.INVISIBLE);
                                                     result5.setVisibility(View.INVISIBLE);
                                                 }

                                                 @Override
                                                 public void onAnimationRepeat(Animation animation) {

                                                 }
                                             }
                );


            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        wheel.startAnimation(wheelRotation);
    }

}