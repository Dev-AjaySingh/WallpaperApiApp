package com.example.wallpaperapiapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static Retrofit retrofit=null;
    public static final String API="GE7AUjXMOmaSg3ECAlazuBuKflor1hkeaqFI9w1gdA3ZrKZVFdMMZ91I";

    public static ApiInterface getApiInterface()
    {
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
