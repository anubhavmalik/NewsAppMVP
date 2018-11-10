package com.anubhavmalikdeveloper.newsappmvp.Network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient mInstance;
    private static final String BASE_URL = "https://newsapi.org/v2/";

    private ApiClient() {

    }

    public static synchronized ApiClient getInstance() {
        if (mInstance == null) mInstance = new ApiClient();
        return mInstance;
    }

    public <S> S createService(Class<S> serviceClass) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }
}
