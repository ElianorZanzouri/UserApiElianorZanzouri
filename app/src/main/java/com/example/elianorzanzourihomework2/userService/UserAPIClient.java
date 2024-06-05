package com.example.elianorzanzourihomework2.userService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPIClient {

    private final static String BASE_URL = "https://randomuser.me";
    public static Retrofit getClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
