package me.sugarkawhi.youqu.http;

import me.sugarkawhi.youqu.bean.GankIoDataBean;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 网络请求类
 * Created by sugarkawhi on 2017/2/8.
 */

public interface HttpRetrofitClient {


    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET
    Observable<GankIoDataBean> getGankIoData(@Url String url);

}
