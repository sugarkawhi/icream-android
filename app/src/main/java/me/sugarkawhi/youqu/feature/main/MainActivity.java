package me.sugarkawhi.youqu.feature.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.databinding.ActivityMainBinding;
import me.sugarkawhi.youqu.feature.main.gank.GankFragment;
import me.sugarkawhi.youqu.feature.main.joke.JokerFragment;
import me.sugarkawhi.youqu.feature.main.video.VideoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMainBinding mBinding;

    private ImageView mIvPic;
    private ImageView mIvVideo;
    private ImageView mIvOctopus;
    private ImageView mIvSecurity;

    private static final String TAG_PIC = "TAG_PIC";
    private static final String TAG_VIDEO = "TAG_VIDEO";
    private static final String TAG_JOKER = "TAG_JOKER";
    private static final String TAG_SECURITY = "TAG_SECURITY";

    private GankFragment gankFragment;
    private VideoFragment videoFragment;
    private JokerFragment jokerFragment;

    private FragmentManager fm;
    private int currentPosition = 0;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initId();
        initFragment(savedInstanceState);
    }

    private void initId() {
        mIvPic = mBinding.ivPic;
        mIvVideo = mBinding.ivVideo;
        mIvOctopus = mBinding.ivOctopus;
        mIvSecurity = mBinding.ivSecurity;
        mIvPic.setOnClickListener(this);
        mIvVideo.setOnClickListener(this);
        mIvOctopus.setOnClickListener(this);
        mIvSecurity.setOnClickListener(this);
    }

    private void initFragment(Bundle savedInstanceState) {
        fm = getSupportFragmentManager();

        if (savedInstanceState == null) {
            gankFragment = GankFragment.newInstance();
            videoFragment = VideoFragment.newInstance();
            jokerFragment = JokerFragment.newInstance();
        } else {
            gankFragment = (GankFragment) fm.findFragmentByTag(TAG_PIC);
            if (gankFragment == null) {
                gankFragment = GankFragment.newInstance();
            }
            videoFragment = (VideoFragment) fm.findFragmentByTag(TAG_VIDEO);
            if (videoFragment == null) {
                videoFragment = VideoFragment.newInstance();
            }
            jokerFragment = (JokerFragment) fm.findFragmentByTag(TAG_JOKER);
            if (jokerFragment == null) {
                jokerFragment = JokerFragment.newInstance();
            }
        }

        fm.beginTransaction()
                .add(R.id.container, gankFragment)
                .add(R.id.container, videoFragment)
                .add(R.id.container, jokerFragment)
                .commit();
        switchFragment(0);
        mIvPic.setEnabled(false);
        mIvVideo.setEnabled(true);
        mIvOctopus.setEnabled(true);
        currentPosition = 0;
    }


    @Override
    public void onClick(View v) {
//        Log.e("MainActivity", "curr position > " + currentPosition);
        switch (v.getId()) {
            case R.id.iv_pic:
                if (currentPosition != 0) {
                    mIvPic.setEnabled(false);
                    mIvVideo.setEnabled(true);
                    mIvOctopus.setEnabled(true);
                    switchFragment(0);
                    currentPosition = 0;
                }
                break;
            case R.id.iv_video:
                if (currentPosition != 1) {
                    mIvVideo.setEnabled(false);
                    mIvPic.setEnabled(true);
                    mIvOctopus.setEnabled(true);
                    switchFragment(1);
                    currentPosition = 1;
                }
                break;
            case R.id.iv_octopus:
                if (currentPosition != 2) {
                    mIvOctopus.setEnabled(false);
                    mIvPic.setEnabled(true);
                    mIvVideo.setEnabled(true);
                    switchFragment(2);
                    currentPosition = 2;
                }
                break;
            case R.id.iv_security:

                break;
        }
    }


    /**
     * 切换当前Fragment
     *
     * @param position 当前索引位置
     */
    private void switchFragment(int position) {
        switch (position) {
            case 0:
                fm.beginTransaction()
                        .show(gankFragment)
                        .hide(videoFragment)
                        .hide(jokerFragment)
                        .commit();
                break;
            case 1:
                fm.beginTransaction()
                        .hide(gankFragment)
                        .show(videoFragment)
                        .hide(jokerFragment)
                        .commit();
                break;
            case 2:
                fm.beginTransaction()
                        .hide(gankFragment)
                        .hide(videoFragment)
                        .show(jokerFragment)
                        .commit();
                break;
        }
    }
}
