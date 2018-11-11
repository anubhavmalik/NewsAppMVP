package com.anubhavmalikdeveloper.newsappmvp.Trending;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anubhavmalikdeveloper.newsappmvp.AppUtils.Helper;
import com.anubhavmalikdeveloper.newsappmvp.Callbacks.VisitNewsInterface;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Article;
import com.anubhavmalikdeveloper.newsappmvp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder> {

    private Context mContext;
    private ArrayList<Article> articleArrayList;
    private VisitNewsInterface visitNewsInterface;

    public TrendingAdapter(Context mContext, ArrayList<Article> articleArrayList, VisitNewsInterface visitNewsInterface) {
        this.mContext = mContext;
        this.articleArrayList = articleArrayList;
        this.visitNewsInterface = visitNewsInterface;
    }

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TrendingViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder trendingViewHolder, int i) {
        final Article article = articleArrayList.get(i);
        if(article!=null){
            if(article.getTitle()!=null){
                trendingViewHolder.tvTitle.setText(article.getTitle());
            }
            else {
                trendingViewHolder.tvTitle.setText("");
            }
            if(article.getAuthor()!=null){
                trendingViewHolder.tvAuthor.setText(article.getAuthor());
            }
            else {
                trendingViewHolder.tvAuthor.setText("");
            }
            if(article.getContent()!=null){
                trendingViewHolder.tvDescription.setText(article.getContent());
            }
            else {
                trendingViewHolder.tvDescription.setText("");
            }
            if(article.getPublishedAt()!=null){
                trendingViewHolder.tvDate.setText(Helper.getFormattedDate(article.getPublishedAt()));
            }
            else {
                trendingViewHolder.tvDate.setText("");
            }
            if(article.getSource()!=null) {
                if (article.getSource().getName() != null) {
                    trendingViewHolder.tvSource.setText(article.getSource().getName());
                }
                else {
                    trendingViewHolder.tvSource.setText("");
                }
            }
            else {
                trendingViewHolder.tvSource.setText("");
            }
            if(article.getUrlToImage()!=null){
                Glide.with(mContext)
                        .load(article.getUrlToImage())
                        .into(trendingViewHolder.ivMain);
            }
            else {
                trendingViewHolder.ivMain.getLayoutParams().width = 1;
                trendingViewHolder.ivMain.requestLayout();
            }
            if(article.getUrl()!=null){
               trendingViewHolder.tvVisitNews.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       visitNewsInterface.redirectToNewsSource(article.getUrl());
                   }
               });
            }
            else {
                trendingViewHolder.tvVisitNews.setVisibility(View.INVISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    static class TrendingViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvDescription, tvDate, tvAuthor, tvSource, tvVisitNews;
        private ImageView ivMain;

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvSource = itemView.findViewById(R.id.tv_source);
            tvVisitNews = itemView.findViewById(R.id.tv_visit);
            ivMain = itemView.findViewById(R.id.iv_main);

        }
    }
}
