package com.anubhavmalikdeveloper.newsappmvp.TopNews;

import android.support.annotation.NonNull;
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

public class TopNewsPresenter implements TopNewsContract.Presenter {

    private TopNewsContract.View view;
    private ApiInterface apiInterface;
    private Integer pageNumber = 1;
    private DatabaseHelper databaseHelper;

    public TopNewsPresenter(TopNewsContract.View view, ApiInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
        this.databaseHelper = new DatabaseHelper();
    }

    @Override
    public void loadData(boolean isAccordingToPreferences, final boolean isNewPageNeeded, boolean resetPageNumber) {
        if (view.isNetworkAvailable()) {

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

            if (isAccordingToPreferences) {
                map.put(ApiConstants.SELECTED_SOURCES, databaseHelper.getSelectedSourcesString());
            } else {
//                 since both of them can't work together in the api call (mentioned in documentation)
                map.put(ApiConstants.API_COUNTRY, databaseHelper.getSelectedCountry());
            }

            apiInterface
                    .getGeneralNewsByType(ApiConstants.API_KEY
                            , ApiConstants.API_TOP_HEADLINES
                            , map)
                    .enqueue(new Callback<NewsModel>() {
                        @Override
                        public void onResponse(@NonNull Call<NewsModel> call, @NonNull Response<NewsModel> response) {
                            if (response.isSuccessful()) {
                                view.showProgress(false);
                                if (response.body() != null) {
                                    if (response.body().getStatus().equalsIgnoreCase(ApiConstants.API_STATUS_OK)) {
//                                        Log.d("TAGG", "response mein size aya : " + response.body().getArticles().size());
                                        if (!isNewPageNeeded) {
                                            databaseHelper.addMoreTopNewsListToDb(response.body());
                                            Log.d("TAGG", "response mien size aya : " + response.body().getArticles().size());
                                            response.body().setArticles(databaseHelper.getTopNewsListFromDb());
                                            Log.d("TAGG", "response mien size aya 2 : " + response.body().getArticles().size());
                                        } else {
                                            databaseHelper.addTopNewsToDb(response.body());
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
                        public void onFailure(@NonNull Call<NewsModel> call, @NonNull Throwable t) {
                            view.showProgress(false);
                            view.showSnackBar(ApiConstants.API_FAIL_MESSAGE, Snackbar.LENGTH_SHORT);
                            t.printStackTrace();
                        }
                    });

        } else {
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
