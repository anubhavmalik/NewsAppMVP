package com.anubhavmalikdeveloper.newsappmvp.Network;

import com.anubhavmalikdeveloper.newsappmvp.Data.Models.NewsModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("{type}")
    Call<NewsModel> getGeneralNewsByType(@Header("Authorization") String token
            , @Path("type") String type
            , @QueryMap Map<String, String> options);
}
