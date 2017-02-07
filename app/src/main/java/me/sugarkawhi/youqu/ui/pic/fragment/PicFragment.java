package me.sugarkawhi.youqu.ui.pic.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.ui.base.BaseFragment;

/**
 * 图片Fragment
 * Created by sugarkawhi on 2017/2/7.
 */

public class PicFragment extends BaseFragment {

    public static PicFragment newInstance() {
        return new PicFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pic, container, false);
        DataBindingUtil.bind(view);
        return view;
    }
}
