package com.sandeep.scannertest.services

import retrofit2.Call
import retrofit2.Response

/**
 * Created by Sandeep Y.
 */

interface RetrofitResponseListener {
    fun onError(call: Call<Any>?, t: Throwable?)
    fun onResponse(call: Call<Any>?, response: Response<Any>?)

}
