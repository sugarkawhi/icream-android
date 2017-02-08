package me.sugarkawhi.youqu.base;

import android.support.v7.app.AppCompatActivity;

import rx.subscriptions.CompositeSubscription;

/**
 * Activity基类
 * Created by sugarkawhi on 2017/2/6.
 */

public class BaseActivity extends AppCompatActivity {

    public CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
