package me.sugarkawhi.youqu.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import me.sugarkawhi.youqu.R;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Fragment基类
 * Created by sugarkawhi on 2017/2/6.
 */

public abstract class BaseFragment extends Fragment {
    public CompositeSubscription mCompositeSubscription;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisiable();
        } else {
            onInVisiable();
        }
    }

    /**
     * 可见
     */
    protected void onVisiable() {
        loadData();
    }

    /**
     * 不可见
     */
    protected void onInVisiable() {

    }

    /**
     * 加载数据
     */
    protected void loadData() {

    }

    /**
     * 加载失败
     * 重新加载
     */
    protected void onRefresh(){

    }


    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null)
            mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


}
