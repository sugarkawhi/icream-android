package me.sugarkawhi.youqu.feature.main.gank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.bean.GankIoDataBean;

/**
 * 图片
 * Created by sugarkawhi on 2017/2/8.
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankHolder> {

    private Context context;
    private List<GankIoDataBean.ResultBean> mlist = new ArrayList<>();

    public GankAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<GankIoDataBean.ResultBean> list) {
        mlist.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GankAdapter.GankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_gank, parent, false);
        return new GankHolder(view);
    }

    @Override
    public void onBindViewHolder(final GankAdapter.GankHolder holder, int position) {

        final String url = mlist.get(position).getUrl();

        Glide.with(context)
                .load(url)
                .crossFade(200)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.IMAGE_URL, url);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context,
                        new Pair<View, String>(v, DetailActivity.VIEW_IMAGE));
                ActivityCompat.startActivity(context, intent, activityOptionsCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class GankHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public GankHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_pic);
        }
    }
}
