package me.sugarkawhi.youqu.feature.splash;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.concurrent.TimeUnit;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.base.BaseActivity;
import me.sugarkawhi.youqu.feature.main.MainActivity;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class SplashActivity extends BaseActivity {

    private int[] resIds = new int[]{
            R.drawable.splash_img_1,
            R.drawable.splash_img_2};

    private ImageView mIvPic;
    private TextView mTvSkip;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        rendingUi();
    }

    private void rendingUi() {
        mIvPic = (ImageView) findViewById(R.id.iv_pic);
        mTvSkip = (TextView) findViewById(R.id.tv_skip);
        int random = (int) (Math.random() * (resIds.length));
        mIvPic.setImageResource(resIds[random]);
        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscription.unsubscribe();
                MainActivity.start(SplashActivity.this);
                finish();
            }
        });
        start();
    }

    private void start() {
        subscription = Observable.interval(1, TimeUnit.SECONDS)
                .take(1)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        MainActivity.start(SplashActivity.this);
                        finish();
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
