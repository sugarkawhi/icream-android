package me.sugarkawhi.youqu.feature.splash;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.base.BaseActivity;
import me.sugarkawhi.youqu.feature.main.MainActivity;
import me.sugarkawhi.youqu.listener.OnAnimatorListener;
import me.sugarkawhi.youqu.widget.WaveView;
import rx.Observable;
import rx.Subscription;

/**
 * create by sk on 17/4/1
 * ObjectAnimator and Animator practice
 */
public class SplashActivity extends BaseActivity {

    private ImageView mWhale;
    private WaveView mWave;

    private ImageView mCloud;
    private ImageView mSmallCloud;

    private ImageView mMoon;

    //Whale Animator
    private ObjectAnimator mWhaleTranslationX;
    private ObjectAnimator mWhaleRotationY;
    private ObjectAnimator mWhaleTranslationX1;
    private ObjectAnimator mWhaleRotationY1;
    private ObjectAnimator mWhaleTranslationX2;

    //Cloud Animator
    private ObjectAnimator mCloudAnimator;
    private ObjectAnimator mSmallCloudAnimator;

    //Moon

    private AnimatorSet mAnimatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mWhale = (ImageView) findViewById(R.id.iv_whale);
        mWave = (WaveView) findViewById(R.id.waveView);
        mCloud = (ImageView) findViewById(R.id.iv_cloud);
        mSmallCloud = (ImageView) findViewById(R.id.iv_small_cloud);
        mMoon = (ImageView) findViewById(R.id.iv_moon);
        doWhaleSwim();
        doCloudMove();
        doMoonAnim();
    }


    /**
     * whale swim animation
     */
    private void doWhaleSwim() {

        mWave.start();
        //
        mWhaleTranslationX = ObjectAnimator.ofFloat(mWhale, "translationX", -200);
        mWhaleTranslationX.setInterpolator(new LinearInterpolator());
        mWhaleTranslationX.setDuration(1000);
        //
        mWhaleRotationY = ObjectAnimator.ofFloat(mWhale, "rotationY", 0, 180);
        mWhaleRotationY.setInterpolator(new LinearInterpolator());
        mWhaleRotationY.setDuration(100);
        //
        mWhaleTranslationX1 = ObjectAnimator.ofFloat(mWhale, "translationX", -200, 200);
        mWhaleTranslationX1.setInterpolator(new LinearInterpolator());
        mWhaleTranslationX1.setDuration(2000);

        mWhaleRotationY1 = ObjectAnimator.ofFloat(mWhale, "rotationY", 180, 0);
        mWhaleRotationY1.setInterpolator(new LinearInterpolator());
        mWhaleRotationY1.setDuration(100);

        mWhaleTranslationX2 = ObjectAnimator.ofFloat(mWhale, "translationX", 200, 0);
        mWhaleTranslationX2.setInterpolator(new LinearInterpolator());
        mWhaleTranslationX2.setDuration(1000);

        mWhaleTranslationX.start();

        mWhaleTranslationX.addListener(new OnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mWhaleRotationY.start();
            }
        });


        mWhaleRotationY.addListener(new OnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mWhaleTranslationX1.start();
            }
        });

        mWhaleTranslationX1.addListener(new OnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mWhaleRotationY1.start();
            }
        });

        mWhaleRotationY1.addListener(new OnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mWhaleTranslationX2.start();
            }
        });
        mWhaleTranslationX2.addListener(new OnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mWhaleTranslationX.start();
            }
        });
    }


    /**
     * cloud move left/right
     */
    private void doCloudMove() {
        mCloudAnimator = ObjectAnimator.ofFloat(mCloud, "translationX", 0, 20, -20, 0);
        mCloudAnimator.setRepeatMode(ObjectAnimator.INFINITE);
        mCloudAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        mCloudAnimator.setInterpolator(new LinearInterpolator());
        mCloudAnimator.setDuration(5000);
        mCloudAnimator.start();

        mSmallCloudAnimator = ObjectAnimator.ofFloat(mSmallCloud, "translationX", 0, 5, -5, 0);
        mSmallCloudAnimator.setRepeatMode(ObjectAnimator.INFINITE);
        mSmallCloudAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        mSmallCloudAnimator.setInterpolator(new LinearInterpolator());
        mSmallCloudAnimator.setDuration(3000);
        mSmallCloudAnimator.start();
    }

    /**
     * moon anim
     */
    private void doMoonAnim() {
        PropertyValuesHolder values1 = PropertyValuesHolder.ofFloat("alpha", 0.7f, 1f);
        PropertyValuesHolder values2 = PropertyValuesHolder.ofFloat("translationY", 0, -60);
        ObjectAnimator proAnim = ObjectAnimator.ofPropertyValuesHolder(mMoon, values1, values2);
        proAnim.setInterpolator(new LinearInterpolator());
        proAnim.setDuration(3000);
        proAnim.start();

        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mMoon, "alpha", 1.0f, 0.5f, 1.0f);
        alphaAnim.setDuration(3000);
        alphaAnim.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnim.setRepeatMode(ValueAnimator.INFINITE);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.play(alphaAnim)
                .after(proAnim);

        mAnimatorSet.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWave.stop();
        mWhaleTranslationX.cancel();
        mWhaleRotationY.cancel();
        mWhaleTranslationX1.cancel();
        mWhaleRotationY1.cancel();
        mWhaleTranslationX2.cancel();

        mCloudAnimator.cancel();
        mSmallCloudAnimator.cancel();

        mAnimatorSet.cancel();
    }

    public void onWhaleClick(View view) {
        MainActivity.start(this);
        finish();
    }

}
