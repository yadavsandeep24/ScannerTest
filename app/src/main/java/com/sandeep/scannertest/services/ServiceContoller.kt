package com.sandeep.scannertest.services


class ServiceContoller private constructor() {


    fun getServiceAdapter(): IServiceInterFaceAdapter {
        return ServiceAdapter()
    }

    companion object {
        private var mServiceControllerInstance: ServiceContoller? = null


        fun getInstance(): ServiceContoller {
            if (mServiceControllerInstance == null) {
                mServiceControllerInstance = ServiceContoller()
            }
            return mServiceControllerInstance as ServiceContoller
        }
    }

}
