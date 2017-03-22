package me.sugarkawhi.youqu.feature.main.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.adapter.GankAdapter;
import me.sugarkawhi.youqu.base.BaseFragment;
import me.sugarkawhi.youqu.bean.GankIoDataBean;
import me.sugarkawhi.youqu.http.HttpMethods;
import me.sugarkawhi.youqu.http.HttpRetrofitClient;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 图片Fragment
 * Created by sugarkawhi on 2017/2/7.
 */

public class GankFragment extends BaseFragment {

    public static GankFragment newInstance() {
        return new GankFragment();
    }

    private RecyclerView recyclerView;
    private GankAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gank, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        mAdapter = new GankAdapter(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        loadData();
    }

    protected void loadData() {
        Subscription subscription = HttpMethods.create(HttpRetrofitClient.class)
                .getGankIoData("http://gank.io/api/data/福利/10/1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankIoDataBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GankIoDataBean gankIoDataBean) {
                        mAdapter.addAll(gankIoDataBean.getResults());
                    }
                });
        addSubscription(subscription);
    }

}
