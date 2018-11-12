package com.anubhavmalikdeveloper.newsappmvp.Sources;

import android.support.annotation.NonNull;

import com.anubhavmalikdeveloper.newsappmvp.Base.BasePresenter;
import com.anubhavmalikdeveloper.newsappmvp.Base.BaseView;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Article;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Source;

import java.util.List;

public interface SourcesContract {
    interface View extends BaseView {

        boolean isNetworkAvailable();

        void showData(@NonNull List<Source> sources);
    }

    interface Presenter extends BasePresenter {
        void loadData(boolean isAccordingToPreferences, boolean isNewPageNeeded);

        void getPreferences();
    }
}
