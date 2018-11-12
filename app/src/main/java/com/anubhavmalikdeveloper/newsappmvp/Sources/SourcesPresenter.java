package com.anubhavmalikdeveloper.newsappmvp.Sources;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.anubhavmalikdeveloper.newsappmvp.AppUtils.ApiConstants;
import com.anubhavmalikdeveloper.newsappmvp.Callbacks.SourceInterface;
import com.anubhavmalikdeveloper.newsappmvp.Data.DatabaseHelper;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.SourceWrapper;
import com.anubhavmalikdeveloper.newsappmvp.Network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourcesPresenter implements SourcesContract.Presenter, SourceInterface {

    private SourcesContract.View view;
    private ApiInterface apiInterface;
    private DatabaseHelper databaseHelper;

    public SourcesPresenter(SourcesContract.View view, ApiInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
        this.databaseHelper = new DatabaseHelper();
    }

    @Override
    public void loadData(boolean isAccordingToPreferences, boolean isNewPageNeeded) {

        if (databaseHelper.getAllSourcesFromDb() != null && !databaseHelper.getAllSourcesFromDb().isEmpty()) {
            view.showData(databaseHelper.getAllSourcesFromDb());

        } else {
            if (view.isNetworkAvailable()) {
                apiInterface.getSources(ApiConstants.API_KEY)
                        .enqueue(new Callback<SourceWrapper>() {
                            @Override
                            public void onResponse(Call<SourceWrapper> call, Response<SourceWrapper> response) {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        databaseHelper.addAllSourceListToDb(response.body());
                                    }
                                }
                                view.showData(response.body().getSourceList());
                            }

                            @Override
                            public void onFailure(Call<SourceWrapper> call, Throwable t) {
                                view.showSnackBar(t.getMessage(), Snackbar.LENGTH_SHORT);
                            }
                        });
            }
        }
    }

    @Override
    public void sourceUpdated(SourceWrapper sourceWrapper) {
        databaseHelper.addAllSourceListToDb(sourceWrapper);
    }
}
