package com.example.conversion.retrofit;


import androidx.annotation.NonNull;

import com.example.conversion.model.Hex;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConversionAPI {
    String BASE_URL = "https://pragathi-screening.000webhostapp.com/";

    @GET("fetch_hex.php")
    Call<Hex> getHex(@Query("key") String CMD);


    @GET("upload_converted_data.php")
    Call<JsonObject> uploadStatus(@Query("key") String CMD,  @Query("value") String value);
}
