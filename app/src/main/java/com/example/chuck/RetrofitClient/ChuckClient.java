package com.example.chuck.RetrofitClient;

import com.example.chuck.interfaces.ChuckApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChuckClient {

    private static Retrofit retrofit = null;

    public static ChuckApi getClient(){


        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.chucknorris.io/jokes/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
       return retrofit.create(ChuckApi.class);
    }



}
