package com.anubhavmalikdeveloper.newsappmvp.AllNews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anubhavmalikdeveloper.newsappmvp.Callbacks.GeneralNewsInterface;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Article;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.NewsModel;
import com.anubhavmalikdeveloper.newsappmvp.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AllNewsAdapter extends RecyclerView.Adapter<AllNewsAdapter.TrendingViewHolder> {

    private Context mContext;
    private NewsModel newsModel;
    private List<Article> articleArrayList;
    private GeneralNewsInterface generalNewsInterface;

    public AllNewsAdapter(Context mContext, NewsModel newsModel, GeneralNewsInterface generalNewsInterface) {
        this.mContext = mContext;
        this.newsModel = newsModel;
        this.generalNewsInterface = generalNewsInterface;
        this.articleArrayList = newsModel.getArticles();
    }

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TrendingViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder trendingViewHolder, int i) {
        final Article article = articleArrayList.get(i);

        Log.d("TAGGGG", "adapter tak aya");
        if (i == articleArrayList.size() - 1) {
            if (newsModel.getTotalResults() > articleArrayList.size()) {
                generalNewsInterface.loadMore();
            } else {
                //do nothing
            }
        }
        if (article != null) {
            if (article.getTitle() != null) {
                trendingViewHolder.tvTitle.setText(article.getTitle());
                if (article.getAuthor() != null) {
                    trendingViewHolder.tvAuthor.setText(article.getAuthor());
                } else {
                    trendingViewHolder.tvAuthor.setText("");
                }
                if (article.getUrlToImage() != null) {
                    Glide.with(mContext)
                            .load(article.getUrlToImage())
                            .into(trendingViewHolder.ivMain);
                } else {
                    trendingViewHolder.ivMain.getLayoutParams().width = 1;
                    trendingViewHolder.ivMain.requestLayout();
                }
                if (article.getContent() != null) {
                    trendingViewHolder.tvDescription.setText(article.getContent());
                } else {
                    trendingViewHolder.tvDescription.setVisibility(View.GONE);
                    trendingViewHolder.ivMain.setVisibility(View.GONE);
                }
                if (article.getPublishedAt() != null) {
                    trendingViewHolder.tvDate.setText(article.getPublishedAt());
                } else {
                    trendingViewHolder.tvDate.setText("");
                }
                if (article.getSource() != null) {
                    if (article.getSource().getName() != null) {
                        trendingViewHolder.tvSource.setText(article.getSource().getName());
                    } else {
                        trendingViewHolder.tvSource.setText("");
                    }
                } else {
                    trendingViewHolder.tvSource.setText("");
                }
                if (article.getUrl() != null) {
                    trendingViewHolder.tvVisitNews.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            generalNewsInterface.redirectToNewsSource(article.getUrl());
                        }
                    });
                } else {
                    trendingViewHolder.tvVisitNews.setVisibility(View.INVISIBLE);
                }
            } else {
                trendingViewHolder.itemView.setVisibility(View.GONE);
                trendingViewHolder.itemView.getLayoutParams().height = 0;
                trendingViewHolder.itemView.requestLayout();
            }

        } else {
            trendingViewHolder.itemView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        Log.d("TAGGG", "size aya : " + articleArrayList.size());
        return articleArrayList.size();
    }


    static class TrendingViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvDescription, tvDate, tvAuthor, tvSource, tvVisitNews;
        private ImageView ivMain;
        private LinearLayout lrContainer;

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvSource = itemView.findViewById(R.id.tv_source);
            tvVisitNews = itemView.findViewById(R.id.tv_visit);
            ivMain = itemView.findViewById(R.id.iv_main);
            lrContainer = itemView.findViewById(R.id.lrContainer);

        }
    }
}
