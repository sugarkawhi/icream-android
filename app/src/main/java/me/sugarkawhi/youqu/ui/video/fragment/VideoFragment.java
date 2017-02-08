package me.sugarkawhi.youqu.ui.video.fragment;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.base.BaseFragment;

/**
 * 视频Fragment
 * Created by sugarkawhi on 2017/2/7.
 */

public class VideoFragment extends BaseFragment {

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_gank;
    }
}
