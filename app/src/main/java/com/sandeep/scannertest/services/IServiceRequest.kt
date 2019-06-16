package com.sandeep.scannertest.services

/**
 * Created by Sandeep Y.
 */

interface IServiceRequest {
    fun makeRequest(isLogEnabled: Boolean, responseListener: IServiceResponseListener)
    fun setRequestTagName(tag: Constants.SERVICETYPES)
}
