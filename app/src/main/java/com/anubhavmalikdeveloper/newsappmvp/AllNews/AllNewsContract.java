package com.anubhavmalikdeveloper.newsappmvp.AllNews;

import android.support.annotation.NonNull;

import com.anubhavmalikdeveloper.newsappmvp.Base.BasePresenter;
import com.anubhavmalikdeveloper.newsappmvp.Base.BaseView;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.NewsModel;

public interface AllNewsContract {
    interface View extends BaseView {

        boolean isNetworkAvailable();

        void showData(@NonNull NewsModel newsModel);
    }

    interface Presenter extends BasePresenter {
        void loadData(boolean isAccordingToPreferences, boolean isNewPageNeeded, boolean isPageReset, String queryTerm);

        String getPreferences();
    }
}
