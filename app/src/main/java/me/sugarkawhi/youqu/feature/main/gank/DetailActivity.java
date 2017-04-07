package me.sugarkawhi.youqu.feature.main.gank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import me.sugarkawhi.youqu.R;

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
        loadFullSizeImage();
    }

    private void loadFullSizeImage() {
        Glide.with(this)
                .load(mImgUrl)
                .centerCrop()
                .into(mIv);
    }

    /**
     * @param activity  activity
     * @param url       url
     */
    public static void start(Activity activity, String url) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(IMAGE_URL, url);
        activity.startActivity(intent);
    }

}
