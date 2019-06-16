package com.sandeep.scannertest.services;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;


public interface APIInterface {

    @Headers({"Content-Type:application/json"})
    @GET
    Call<Object> callGetService(@Url String url, @HeaderMap HashMap<String, String> headers);

    @POST
    Call<Object> callPostService(@Url String url, @HeaderMap HashMap<String, String> headers, @Body JsonObject json);
}

