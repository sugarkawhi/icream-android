package me.sugarkawhi.youqu.ui.joker;

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

    @Override
    public int setContent() {
        return R.layout.fragment_gank;
    }
}
