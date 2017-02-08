package me.sugarkawhi.youqu.http;

import rx.Subscription;

/**
 * 网络请求回调
 * Created by sugarkawhi on 2017/2/8.
 */

public interface RequestImpl {

    void onFail();

    void onSuccess(Object o);

    void addSubscription(Subscription s);
}
