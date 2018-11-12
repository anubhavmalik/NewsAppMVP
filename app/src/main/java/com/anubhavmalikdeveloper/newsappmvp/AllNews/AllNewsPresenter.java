package com.anubhavmalikdeveloper.newsappmvp.AllNews;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.anubhavmalikdeveloper.newsappmvp.AppUtils.ApiConstants;
import com.anubhavmalikdeveloper.newsappmvp.Data.DatabaseHelper;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.NewsModel;
import com.anubhavmalikdeveloper.newsappmvp.Network.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllNewsPresenter implements AllNewsContract.Presenter {
    private AllNewsContract.View view;
    private ApiInterface apiInterface;
    private Integer pageNumber = 1;
    private DatabaseHelper databaseHelper;

    public AllNewsPresenter(AllNewsContract.View view, ApiInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
        databaseHelper = new DatabaseHelper();
    }

    @Override
    public void loadData(boolean isAccordingToPreferences, final boolean isNewPageNeeded, boolean resetPageNumber, String queryString) {
        if (view.isNetworkAvailable()) {

            Log.d("TAGGG", "net available");
            view.showProgress(true);
            if (resetPageNumber) {
                pageNumber = 1;
            } else {
                if (isNewPageNeeded) {
                    pageNumber++;
                }
            }

            Map<String, String> map = new HashMap();
            map.put(ApiConstants.PAGE_NUMBER, String.valueOf(pageNumber));

            if(queryString!=null && !queryString.isEmpty()){
                map.put(ApiConstants.API_QUERY, queryString);
            }

            if (isAccordingToPreferences && getPreferences()!=null && !getPreferences().isEmpty()) {
                map.put(ApiConstants.SELECTED_SOURCES, databaseHelper.getSelectedSourcesString());
            }

            apiInterface
                    .getGeneralNewsByType(ApiConstants.API_KEY
                            , ApiConstants.API_EVERYTHING
                            , map)
                    .enqueue(new Callback<NewsModel>() {
                        @Override
                        public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                            if (response.isSuccessful()) {
                                view.showProgress(false);
                                if (response.body() != null) {
                                    if (response.body().getStatus().equalsIgnoreCase(ApiConstants.API_STATUS_OK)) {
                                        if (!isNewPageNeeded) {
                                            databaseHelper.addMoreAllNewsListToDb(response.body());
                                            response.body().setArticles(databaseHelper.getAllNewsListFromDb());
                                        } else {
                                            databaseHelper.addAllNewsToDb(response.body());
                                        }
                                        view.showData(response.body());
                                    } else {
                                        view.showSnackBar(ApiConstants.API_UNEXPECTED_MESSAGE, Snackbar.LENGTH_SHORT);
                                    }
                                } else {
                                    view.showEmptyPlaceholder();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<NewsModel> call, Throwable t) {
                            view.showProgress(false);
                            view.showSnackBar(ApiConstants.API_FAIL_MESSAGE, Snackbar.LENGTH_SHORT);
                            t.printStackTrace();
                        }
                    });

        }
        else {
            NewsModel newsModel = new NewsModel();
            newsModel.setArticles(databaseHelper.getAllNewsListFromDb());
            newsModel.setTotalResults(newsModel.getArticles().size());
            view.showData(newsModel);
        }
    }

    @Override
    public String getPreferences() {
        return databaseHelper.getSelectedSourcesString();
    }
}
