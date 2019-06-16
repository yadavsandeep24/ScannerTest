package com.sandeep.scannertest.services

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sandeep.scannertest.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ServiceUtility {
    val LOG_TAG = "SAN"
    fun getClient(context: Context): Retrofit {
        var retrofit: Retrofit? = null
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.NONE
            val client = OkHttpClient.Builder().apply {
                readTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
                connectTimeout(30, TimeUnit.SECONDS)
                addInterceptor(interceptor)
                addInterceptor { chain ->
                    var request = chain.request()
                    request = request.newBuilder()
                        .build()
                    val response = chain.proceed(request)
                    response
                }
            }
            retrofit = Retrofit.Builder()
                .baseUrl(context.resources.getString(R.string.service_url_root))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }
        return retrofit!!
    }

    fun execute(
        isLogEnabled: Boolean, url: String,
        headers: HashMap<String, String>,
        jsonObject: String, context: Context,
        requestType: Constants.RequestType,
        listner: RetrofitResponseListener
    ) {
        try {
            if (isLogEnabled) {
                Log.d(LOG_TAG, "URL-->" + url)
                for (entry in headers.keys) {
                    val value = headers.get(entry)
                    Log.d(LOG_TAG, entry + "-->" + value)
                }
            }
            val api = getClient(context).create(APIInterface::class.java)
            val callObj: Call<Any>
            if (requestType == Constants.RequestType.POST) {
                var json = JsonObject()
                if (!TextUtils.isEmpty(jsonObject)) {
                    val parser = JsonParser()
                    json = parser.parse(jsonObject).getAsJsonObject()
                    if (isLogEnabled) {
                        Log.d(LOG_TAG, "Body: " + json.toString())
                    }
                }
                callObj = api.callPostService(url, headers, json)
            } else {
                callObj = api.callGetService(url, headers)
            }
            callObj.enqueue(object : Callback<Any> {
                override fun onFailure(call: Call<Any>?, t: Throwable?) {
                    if (isLogEnabled) {
                        Log.d(LOG_TAG, "Response Error-->" + t!!.message!!)
                    }
                    listner.onError(call, t)
                }

                override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                    val str = Gson().toJson(response!!.body())
                    if (isLogEnabled) {
                        Log.d(LOG_TAG, "Response-->" + str)
                    }
                    listner.onResponse(call, response)
                }

            })
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}