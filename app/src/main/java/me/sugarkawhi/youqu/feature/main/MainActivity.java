package me.sugarkawhi.youqu.feature.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.databinding.ActivityMainBinding;
import me.sugarkawhi.youqu.feature.main.gank.GankFragment;
import me.sugarkawhi.youqu.feature.main.joke.JokerFragment;
import me.sugarkawhi.youqu.feature.main.mine.MineFragment;
import me.sugarkawhi.youqu.feature.main.video.VideoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMainBinding mBinding;

    private ImageView mIvPic;
    private ImageView mIvVideo;
    private ImageView mIvJoke;
    private ImageView mIvMine;

    private static final String[] titles = new String[]{"Gank", "Album", "Joke", "Mine"};

    private static final String TAG_PIC = "TAG_PIC";
    private static final String TAG_VIDEO = "TAG_VIDEO";
    private static final String TAG_JOKER = "TAG_JOKER";
    private static final String TAG_MINE = "TAG_MINE";

    private GankFragment gankFragment;
    private VideoFragment videoFragment;
    private JokerFragment jokerFragment;
    private MineFragment mineFragment;

    private FragmentManager fm;
    private int currentPosition = -1;

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
        mIvJoke = mBinding.ivJoke;
        mIvMine = mBinding.ivMine;
        mIvPic.setOnClickListener(this);
        mIvVideo.setOnClickListener(this);
        mIvJoke.setOnClickListener(this);
        mIvMine.setOnClickListener(this);

        mBinding.toolbar.setTitleTextColor(Color.WHITE);
    }

    private void initFragment(Bundle savedInstanceState) {
        fm = getSupportFragmentManager();

        if (savedInstanceState == null) {
            gankFragment = GankFragment.newInstance();
            videoFragment = VideoFragment.newInstance();
            jokerFragment = JokerFragment.newInstance();
            mineFragment = MineFragment.newInstance();

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

            mineFragment = (MineFragment) fm.findFragmentByTag(TAG_MINE);
            if (mineFragment == null) {
                mineFragment = MineFragment.newInstance();
            }
        }

        fm.beginTransaction()
                .add(R.id.container, gankFragment)
                .add(R.id.container, videoFragment)
                .add(R.id.container, jokerFragment)
                .add(R.id.container, mineFragment)
                .commit();

        switchFragment(0);
    }


    @Override
    public void onClick(View v) {
//        Log.e("MainActivity", "curr position > " + currentPosition);
        switch (v.getId()) {
            case R.id.iv_pic:
                switchFragment(0);
                break;
            case R.id.iv_video:
                switchFragment(1);
                break;
            case R.id.iv_joke:
                switchFragment(2);
                break;
            case R.id.iv_mine:
                switchFragment(3);
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
                if (currentPosition == 0)
                    break;
                currentPosition = 0;
                fm.beginTransaction()
                        .show(gankFragment)
                        .hide(videoFragment)
                        .hide(jokerFragment)
                        .hide(mineFragment)
                        .commit();
                mBinding.toolbar.setTitle(titles[0]);
                mIvPic.setSelected(true);
                mIvVideo.setSelected(false);
                mIvJoke.setSelected(false);
                mIvMine.setSelected(false);
                break;
            case 1:
                if (currentPosition == 1)
                    break;
                currentPosition = 1;
                fm.beginTransaction()
                        .hide(gankFragment)
                        .show(videoFragment)
                        .hide(jokerFragment)
                        .hide(mineFragment)
                        .commit();
                mBinding.toolbar.setTitle(titles[1]);
                mIvPic.setSelected(false);
                mIvVideo.setSelected(true);
                mIvJoke.setSelected(false);
                mIvMine.setSelected(false);
                break;
            case 2:
                if (currentPosition == 2)
                    break;
                currentPosition = 2;
                fm.beginTransaction()
                        .hide(gankFragment)
                        .hide(videoFragment)
                        .hide(mineFragment)
                        .show(jokerFragment)
                        .commit();
                mBinding.toolbar.setTitle(titles[2]);
                mIvPic.setSelected(false);
                mIvVideo.setSelected(false);
                mIvJoke.setSelected(true);
                mIvMine.setSelected(false);
                break;
            case 3:
                if (currentPosition == 3)
                    break;
                currentPosition = 3;
                fm.beginTransaction()
                        .hide(gankFragment)
                        .hide(videoFragment)
                        .hide(jokerFragment)
                        .show(mineFragment)
                        .commit();
                mBinding.toolbar.setTitle(titles[3]);
                mIvPic.setSelected(false);
                mIvVideo.setSelected(false);
                mIvJoke.setSelected(false);
                mIvMine.setSelected(true);
                break;

        }
    }
}
