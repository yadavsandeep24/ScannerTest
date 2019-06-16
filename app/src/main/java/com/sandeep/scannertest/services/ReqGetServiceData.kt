package com.sandeep.scannertest.services

import android.content.Context
import retrofit2.Call
import retrofit2.Response
import java.util.*

class ReqGetServiceData(
    private val url: String,
    private var headers: HashMap<String, String>,
    private val mContext: Context) : IServiceRequest,
    RetrofitResponseListener {

    internal lateinit var mResponse: GenericServiceResponse
    internal lateinit var mResponseListner: IServiceResponseListener

    override fun makeRequest(isLogEnabled: Boolean, responseListener: IServiceResponseListener) {
        this.mResponseListner = responseListener
        mResponse.setServiceRequest(this)
        ServiceUtility.execute(isLogEnabled, url, headers, "", mContext, Constants.RequestType.GET, this)
    }

    override fun setRequestTagName(tag: Constants.SERVICETYPES) {
        mResponse = GenericServiceResponse()
        mResponse.setRequestTagName(tag)
    }

    override fun onError(call: Call<Any>?, t: Throwable?) {
        mResponse.setError(t!!)
        mResponseListner.requestErrorOccured(mResponse)
    }

    override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
        mResponse.setServiceResponse(response)
        mResponseListner.requestCompleted(mResponse)
    }
}
