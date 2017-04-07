package me.sugarkawhi.youqu.feature.main.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.base.BaseFragment;

/**
 * VideoFragment
 * Created by sugarkawhi on 2017/2/7.
 */

public class VideoFragment extends BaseFragment {

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }


    ImageView iv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        iv = (ImageView) view.findViewById(R.id.iv);
        return view;
    }

}
