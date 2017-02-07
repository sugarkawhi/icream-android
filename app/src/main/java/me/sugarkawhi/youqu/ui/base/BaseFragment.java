package me.sugarkawhi.youqu.ui.base;

import android.support.v4.app.Fragment;

import rx.subscriptions.CompositeSubscription;

/**
 * Fragment基类
 * Created by sugarkawhi on 2017/2/6.
 */

public class BaseFragment extends Fragment {
    public CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
