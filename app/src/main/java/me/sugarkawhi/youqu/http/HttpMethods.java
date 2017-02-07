package me.sugarkawhi.youqu.http;

import java.util.concurrent.TimeUnit;

import me.sugarkawhi.youqu.utils.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * HTTP 进行网络请求
 * Created by sugarkawhi on 2017/2/6.
 */

public class HttpMethods {

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    //单例 构造方法私有化
    private HttpMethods() {
        //手动创建一个OkHttpClient
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    private static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static <S> S create(Class<S> classService) {
        return getInstance().retrofit.create(classService);
    }
}
