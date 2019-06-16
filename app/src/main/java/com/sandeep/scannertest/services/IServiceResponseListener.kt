package com.sandeep.scannertest.services

/**
 * Created by Sandeep Y.
 */
interface IServiceResponseListener {
    fun requestCompleted(response: IServiceResponse)
    fun requestErrorOccured(error: IServiceResponse)
}
