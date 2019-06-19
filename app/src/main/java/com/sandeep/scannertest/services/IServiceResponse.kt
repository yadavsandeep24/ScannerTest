package com.sandeep.scannertest.services

import retrofit2.Response

/**
 * Created by Sandeep Y.
 */

interface IServiceResponse {
    fun getRequestTagName(): Constants.SERVICETYPES?
    fun getServiceResponse(): Response<Any>?
    fun getIServiceRequest(): IServiceRequest?
    fun getServiceError(): Throwable?
}

