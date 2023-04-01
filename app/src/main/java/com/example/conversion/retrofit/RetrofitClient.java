package com.example.conversion.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private final ConversionAPI fetchHexApi;
    private final ConversionAPI uploadAPI;


    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConversionAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        fetchHexApi = retrofit.create(ConversionAPI.class);
        uploadAPI = retrofit.create(ConversionAPI.class);
}

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ConversionAPI getFetchHexAPI() {
        return fetchHexApi;
    }
    public ConversionAPI getUploadAPI() { return uploadAPI; }

}


