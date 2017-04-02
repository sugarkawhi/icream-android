package me.sugarkawhi.youqu.feature.main.joke;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.sugarkawhi.youqu.R;
import me.sugarkawhi.youqu.bean.JokeBean;

/**
 * Created by admin on 2017/4/2.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeHoder> {

    private List<JokeBean.ResultBean.DataBean> mDatas = new ArrayList<>();

    private Context mContext;

    public JokeAdapter(Context context) {
        this.mContext = context;
    }

    public void addAll(JokeBean joke) {
        if (joke == null
                || joke.getResult() == null
                || joke.getResult().getData() == null
                || joke.getResult().getData().isEmpty())
            return;
        mDatas.addAll(joke.getResult().getData());
        notifyDataSetChanged();
    }

    @Override
    public JokeHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_joke, parent, false);
        return new JokeHoder(view);
    }

    @Override
    public void onBindViewHolder(JokeHoder holder, int position) {
        JokeBean.ResultBean.DataBean data = mDatas.get(position);
        holder.mContent.setText(data.getContent());
        holder.mTime.setText(data.getUpdatetime());
        if (position == 0) {
            holder.itemView.setBackgroundResource(R.drawable.item_background_joke_first);
        } else if (position == mDatas.size() - 1) {
            holder.itemView.setBackgroundResource(R.drawable.item_background_joke_last);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.item_background_joke_last);
        }

        if (position == mDatas.size() - 1) {
            holder.mDivider.setVisibility(View.INVISIBLE);
        } else {
            holder.mDivider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class JokeHoder extends RecyclerView.ViewHolder {
        TextView mContent, mTime;
        View mDivider;

        public JokeHoder(View itemView) {
            super(itemView);
            mContent = (TextView) itemView.findViewById(R.id.tv_content);
            mTime = (TextView) itemView.findViewById(R.id.tv_time);
            mDivider = itemView.findViewById(R.id.divider);
        }
    }
}
