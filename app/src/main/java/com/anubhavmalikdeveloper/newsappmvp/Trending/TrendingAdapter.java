package com.anubhavmalikdeveloper.newsappmvp.Trending;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder> {


    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder trendingViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class TrendingViewHolder extends RecyclerView.ViewHolder{

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
