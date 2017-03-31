package me.sugarkawhi.youqu.feature.main.video;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        Button btn_property = (Button) view.findViewById(R.id.btn_property);
        btn_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ofPropertyValues();
            }
        });

        Button btn_set = (Button) view.findViewById(R.id.btn_set);
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ofSet();
            }
        });
        return view;
    }


    private void ofPropertyValues() {
        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", 100, -60, 20, -10, 0);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("alpha", 0.5f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(iv, translationX, translationY);
        animator.setDuration(1000);
        animator.start();
    }

    private void ofSet() {
        //
        ObjectAnimator translationX = ObjectAnimator.ofFloat(iv, "translationX", 200);
        translationX.setDuration(500);
        //
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(iv, "rotationY", 0, 180);
        rotationY.setDuration(200);
        //
        ObjectAnimator translationX1 = ObjectAnimator.ofFloat(iv, "translationX", 200, 0, -200, 0);
        translationX1.setDuration(1500);

        AnimatorSet set = new AnimatorSet();
        set.play(translationX)
                .before(rotationY)
                .before(translationX1);
        set.start();
    }
}
