package me.sugarkawhi.youqu.feature.main.joke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.base.BaseFragment;
import me.sugarkawhi.youqu.bean.JokeBean;
import me.sugarkawhi.youqu.http.HttpMethods;
import me.sugarkawhi.youqu.http.HttpRetrofitClient;
import me.sugarkawhi.youqu.utils.Constants;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 段子Fragment
 * Created by sugarkawhi on 2017/2/7.
 */

public class JokerFragment extends BaseFragment {

    private boolean isloading;

    private RecyclerView mRecyclerView;
    private JokeAdapter mJokeAdapter;

    public static JokerFragment newInstance() {
        return new JokerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mJokeAdapter = new JokeAdapter(getContext());
        mRecyclerView.setAdapter(mJokeAdapter);
        loadData();
        return view;
    }

    protected void loadData() {
        isloading = true;
        Subscription subscription = HttpMethods.create(HttpRetrofitClient.class)
                .getJokeData(Constants.JUHE_APP_KEY,
                        1,
                        20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JokeBean>() {
                    @Override
                    public void onCompleted() {
                        isloading = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        isloading = false;
                    }

                    @Override
                    public void onNext(JokeBean jokeBean) {
                        mJokeAdapter.addAll(jokeBean);
                    }
                });
        addSubscription(subscription);
    }
}
