package com.sandeep.scannertest.services

import android.content.Context


class ServiceAdapter : IServiceInterFaceAdapter,
    IServiceResponseListener {

    private var mIsLogEnabled = true
    private var mResponseListener: IServiceResponseListener? = null

    private var headers = HashMap<String, String>()

    private fun getDefaultHeader(): HashMap<String, String> {
        headers["Content-Type"] = "application/json"
        return headers
    }


    override fun getScannerInfo(url: String, body: String, sType: Constants.SERVICETYPES,
                                listner: IServiceResponseListener, context: Context) {
        val req = ReqGetServiceData(url, getDefaultHeader(), context)
        setListner(listner, sType, req)
    }


    override fun requestCompleted(response: IServiceResponse) {
        mResponseListener!!.requestCompleted(response)
    }

    override fun requestErrorOccured(error: IServiceResponse) {
        mResponseListener!!.requestErrorOccured(error)
    }

    private fun setListner(listner: IServiceResponseListener, stypes: Constants.SERVICETYPES, req: IServiceRequest) {
        this.mResponseListener = listner
        callMainService(stypes, req)

    }

    private fun callMainService(serviceType: Constants.SERVICETYPES, req: IServiceRequest) {
        req.setRequestTagName(serviceType)
        req.makeRequest(mIsLogEnabled, this)
    }
}