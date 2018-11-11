package com.anubhavmalikdeveloper.newsappmvp.Network;

import com.anubhavmalikdeveloper.newsappmvp.Data.Models.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface {
    @GET("{type}")
    Call<NewsModel> getGeneralNewsByType(@Header("Authorization") String token, String type);
}
