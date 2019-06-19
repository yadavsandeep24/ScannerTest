package com.sandeep.scannertest.services

import com.sandeep.scannertest.constants.Constants

/**
 * Created by Sandeep Y.
 */

interface IServiceRequest {
    fun makeRequest(isLogEnabled: Boolean, responseListener: IServiceResponseListener)
    fun setRequestTagName(tag: Constants.SERVICETYPES)
}
