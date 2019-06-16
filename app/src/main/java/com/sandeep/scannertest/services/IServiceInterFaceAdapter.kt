package com.sandeep.scannertest.services

import android.content.Context

/**
 * Created by Sandeep Y.
 */
interface IServiceInterFaceAdapter {
   fun getScannerInfo(url: String, body: String, sType: Constants.SERVICETYPES, listner: IServiceResponseListener, context: Context)

}