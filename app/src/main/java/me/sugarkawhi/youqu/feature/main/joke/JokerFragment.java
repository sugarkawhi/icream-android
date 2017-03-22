package me.sugarkawhi.youqu.feature.main.joke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.base.BaseFragment;

/**
 * 段子Fragment
 * Created by sugarkawhi on 2017/2/7.
 */

public class JokerFragment extends BaseFragment {

    public static JokerFragment newInstance() {
        return new JokerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        return view;
    }
}
