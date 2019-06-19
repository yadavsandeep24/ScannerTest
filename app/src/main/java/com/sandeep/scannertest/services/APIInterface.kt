package com.sandeep.scannertest.services

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

import java.util.HashMap


interface APIInterface {

    @Headers("Content-Type:application/json")
    @GET
    fun callGetService(@Url url: String, @HeaderMap headers: HashMap<String, String>): Call<Any>

    @POST
    fun callPostService(@Url url: String, @HeaderMap headers: HashMap<String, String>, @Body json: JsonObject): Call<Any>
}

