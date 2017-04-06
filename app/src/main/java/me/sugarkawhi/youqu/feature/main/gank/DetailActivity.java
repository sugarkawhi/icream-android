package me.sugarkawhi.youqu.feature.main.gank;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.ArcMotion;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.widget.CustomChangeBounds;

/**
 * Created by sugarkawhi on 2017/4/5.
 */

public class DetailActivity extends AppCompatActivity {

    private ImageView mIv;
    private FloatingActionButton mFab;
    private String mImgUrl;

    public static final String IMAGE_URL = "IMAGE_URL";
    public static final String TRANSITION_GANK_IMAGE = "transition_gank_image";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        mIv = (ImageView) findViewById(R.id.iv);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mImgUrl = getIntent().getStringExtra(IMAGE_URL);

        setMotion(mIv, true);
    }

    private void loadFullSizeImage() {
        Glide.with(this)
                .load(mImgUrl)
                .centerCrop()
                .dontAnimate()
                .into(mIv);
    }


    /**
     * @param activity  activity
     * @param url       url
     * @param imageView imageView
     */
    public static void start(Activity activity, String url, ImageView imageView) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(IMAGE_URL, url);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        imageView,
                        TRANSITION_GANK_IMAGE);//与xml文件对应
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    protected void setMotion(ImageView imageView, boolean isShow) {
        if (!isShow) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //定义ArcMotion
            ArcMotion arcMotion = new ArcMotion();
            arcMotion.setMinimumHorizontalAngle(50f);
            arcMotion.setMinimumVerticalAngle(50f);
            //插值器，控制速度
            Interpolator interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.fast_out_slow_in);

            //实例化自定义的ChangeBounds
            CustomChangeBounds changeBounds = new CustomChangeBounds();
            changeBounds.setPathMotion(arcMotion);
            changeBounds.setInterpolator(interpolator);
            changeBounds.addTarget(imageView);
            //将切换动画应用到当前的Activity的进入和返回
            getWindow().setSharedElementEnterTransition(changeBounds);
            getWindow().setSharedElementReturnTransition(changeBounds);
        } else {
            loadFullSizeImage();
        }
    }
}
