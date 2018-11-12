package com.anubhavmalikdeveloper.newsappmvp.Sources;

import android.support.annotation.NonNull;

import com.anubhavmalikdeveloper.newsappmvp.Base.BasePresenter;
import com.anubhavmalikdeveloper.newsappmvp.Base.BaseView;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Article;

import java.util.List;

public interface SourcesContract {
    interface View extends BaseView {

        void setRefreshing(boolean refreshing);

        boolean isNetworkAvailable();

        void showData(@NonNull List<Article> articleList);
    }

    interface Presenter extends BasePresenter {
        void loadData(boolean isAccordingToPreferences, boolean isNewPageNeeded);

        void getPreferences();
    }
}
