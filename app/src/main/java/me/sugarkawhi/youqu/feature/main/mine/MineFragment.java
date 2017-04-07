package me.sugarkawhi.youqu.feature.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.base.BaseFragment;

/**
 * Created by sugarkawhi on 2017/4/7.
 */

public class MineFragment extends BaseFragment {


    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        Button btn = (Button) view.findViewById(R.id.btn_share_element);

        return view;
    }
}
