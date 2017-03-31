package me.sugarkawhi.youqu.feature.splash;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.concurrent.TimeUnit;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.base.BaseActivity;
import me.sugarkawhi.youqu.feature.main.MainActivity;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class SplashActivity extends BaseActivity {


    ImageView mWhale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        doWhaleSwim();
    }

    /**
     * whale swim animation
     */
    private void doWhaleSwim() {
        mWhale = (ImageView) findViewById(R.id.iv);

        //
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mWhale, "translationX", -200);
        translationX.setDuration(1000);
        //
        final ObjectAnimator rotationY = ObjectAnimator.ofFloat(mWhale, "rotationY", 0, 180);
        rotationY.setDuration(100);
        //
        final ObjectAnimator translationX1 = ObjectAnimator.ofFloat(mWhale, "translationX", -200, 0);
        translationX1.setDuration(1000);

        translationX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rotationY.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        rotationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                translationX1.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        translationX1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                MainActivity.start(SplashActivity.this);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    @Override
    public void onBackPressed() {
    }

    /**
     * 点击whale
     */
    public void onClickWhale(View view) {
        Toast.makeText(this, "CATCH A WHALE,WOW", Toast.LENGTH_SHORT).show();
    }
}
