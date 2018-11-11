package com.anubhavmalikdeveloper.newsappmvp.Trending;

import android.support.annotation.NonNull;

import com.anubhavmalikdeveloper.newsappmvp.Base.BasePresenter;
import com.anubhavmalikdeveloper.newsappmvp.Base.BaseView;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Article;

import java.util.List;

public interface TrendingContract {
    interface View extends BaseView {

        void setRefreshing(boolean refreshing);

        boolean isNetworkAvailable();

        boolean isActive();

        void showData(@NonNull List<Article> articleList);
    }

    interface Presenter extends BasePresenter {
        void loadData(boolean isAccordingToPreferences);

        void getPreferences();
    }
}
