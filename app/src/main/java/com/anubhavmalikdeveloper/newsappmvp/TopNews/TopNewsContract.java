package com.anubhavmalikdeveloper.newsappmvp.TopNews;

import android.support.annotation.NonNull;

import com.anubhavmalikdeveloper.newsappmvp.Base.BasePresenter;
import com.anubhavmalikdeveloper.newsappmvp.Base.BaseView;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.NewsModel;

public interface TopNewsContract {

    interface View extends BaseView {

        void setRefreshing(boolean refreshing);

        boolean isNetworkAvailable();

        void showData(@NonNull NewsModel newsModel);
    }

    interface Presenter extends BasePresenter {
        void loadData(boolean isAccordingToPreferences, boolean isNewPageNeeded, boolean isPageReset);

        String getPreferences();
    }
}
