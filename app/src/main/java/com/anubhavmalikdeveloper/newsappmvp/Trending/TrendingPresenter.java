package com.anubhavmalikdeveloper.newsappmvp.Trending;

import com.anubhavmalikdeveloper.newsappmvp.Data.Dao.NewsDao;
import com.anubhavmalikdeveloper.newsappmvp.Network.ApiInterface;

public class TrendingPresenter implements TrendingContract.Presenter {
    private TrendingContract.View view;
    private ApiInterface apiInterface;

    public TrendingPresenter(TrendingContract.View view, ApiInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
    }

    @Override
    public void loadData(boolean isAccordingToPreferences) {

    }

    @Override
    public void getPreferences() {

    }
}
