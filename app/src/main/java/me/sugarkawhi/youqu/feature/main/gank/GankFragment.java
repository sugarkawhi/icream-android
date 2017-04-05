package me.sugarkawhi.youqu.feature.main.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sugarkawhi.youqu.R;
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


    //正在加载
    private boolean isloading;
    //
    private int curPage = 1;

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
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();


                if (lastVisibleItemPosition > mAdapter.getItemCount() / 2 - 3 && !isloading) {
                    loadData();
                }

            }
        });
    }

    protected void loadData() {
        isloading = true;
        String url = "http://gank.io/api/data/福利/10/" + curPage;
        Log.e("GankFragment", "url > " + url);
        Subscription subscription = HttpMethods.create(HttpRetrofitClient.class)
                .getGankIoData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankIoDataBean>() {
                    @Override
                    public void onCompleted() {
                        isloading = false;
                        curPage++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        isloading = false;
                    }

                    @Override
                    public void onNext(GankIoDataBean gankIoDataBean) {
                        mAdapter.addAll(gankIoDataBean.getResults());
                    }
                });
        addSubscription(subscription);
    }


}
