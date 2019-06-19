package com.sandeep.scannertest.services

import retrofit2.Response

/**
 * Created by Sandeep Y.
 */

public class GenericServiceResponse : IServiceResponse {

    internal lateinit var mServiceType: Constants.SERVICETYPES
    private var mRequest: IServiceRequest? = null
    private var mResponse: Response<Any>? = null
    private var err: Throwable? = null

    override fun getRequestTagName(): Constants.SERVICETYPES {
        return mServiceType
    }

    fun setRequestTagName(serviceType: Constants.SERVICETYPES) {
        this.mServiceType = serviceType
    }

    override fun getServiceResponse(): Response<Any>? {
        return mResponse
    }

    override fun getIServiceRequest(): IServiceRequest? {
        return mRequest
    }

    fun setServiceResponse(res: Response<Any>?) {
        this.mResponse = res
    }

    fun setServiceRequest(req: IServiceRequest) {
        this.mRequest = req
    }

    override fun getServiceError(): Throwable? {
        return err
    }

    fun setError(t: Throwable) {
        this.err = t
    }
}
