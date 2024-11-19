package com.example.examen1;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity1 extends AppCompatActivity {

    private FrameLayout sky;
    private View sun;
    private boolean isAnimated = false;
    private ImageView emoji2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        sky = findViewById(R.id.sky);
        sun = findViewById(R.id.sun);
        emoji2 = findViewById(R.id.emoji2);

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnimated) {
                    resetAnimation();
                } else {
                    startAnimation();
                }
                isAnimated = !isAnimated;
            }
        });

        emoji2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity1.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void startAnimation() {
        ObjectAnimator moveCircle = ObjectAnimator.ofFloat(sun, "translationY", 0f, 600f);
        moveCircle.setDuration(3000);

        ValueAnimator colorChange = ValueAnimator.ofArgb(
                getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorSecondary),
                getResources().getColor(R.color.colorTertiary),
                getResources().getColor(R.color.colorBackground)
        );
        colorChange.setDuration(4500);
        colorChange.setInterpolator(new android.view.animation.LinearInterpolator());
        colorChange.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                sky.setBackgroundColor((int) animator.getAnimatedValue());
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(moveCircle, colorChange);
        animatorSet.start();
    }

    private void resetAnimation() {
        ObjectAnimator moveCircleBack = ObjectAnimator.ofFloat(sun, "translationY", 600f, 0f);
        moveCircleBack.setDuration(3000);

        ValueAnimator colorReset = ValueAnimator.ofArgb(
                getResources().getColor(R.color.colorBackground),
                getResources().getColor(R.color.colorTertiary),
                getResources().getColor(R.color.colorSecondary),
                getResources().getColor(R.color.colorPrimaryDark)
        );
        colorReset.setDuration(4500);
        colorReset.setInterpolator(new android.view.animation.LinearInterpolator());
        colorReset.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                sky.setBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(moveCircleBack, colorReset);
        animatorSet.start();
    }
}
