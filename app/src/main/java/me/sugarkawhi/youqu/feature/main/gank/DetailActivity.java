package me.sugarkawhi.youqu.feature.main.gank;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
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
    public static final String DETAIL_TRANSLATION_NAME = "detail:image";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        mIv = (ImageView) findViewById(R.id.iv);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mImgUrl = getIntent().getStringExtra(IMAGE_URL);

        ViewCompat.setTransitionName(mIv, DETAIL_TRANSLATION_NAME);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP && addTranslationListener()) {
            loadThumbnail();
        } else {
            loadFullSizeImage();
        }
    }

    private void loadThumbnail() {
        Glide.with(this)
                .load(mImgUrl)
                .dontAnimate()
                .centerCrop()
                .thumbnail(0.5f)
                .into(mIv);
    }

    private void loadFullSizeImage() {
        Glide.with(this)
                .load(mImgUrl)
                .centerCrop()
                .dontAnimate()
                .into(mIv);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean addTranslationListener() {
        Transition transition = getWindow().getSharedElementEnterTransition();
        if (transition == null)
            return false;
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                loadFullSizeImage();
                mFab.setVisibility(View.VISIBLE);
                transition.removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                transition.removeListener(this);
            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        return true;
    }
}
