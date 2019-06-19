package com.sandeep.scannertest.services

import android.content.Context
import com.sandeep.scannertest.constants.Constants

/**
 * Created by Sandeep Y.
 */
interface IServiceInterFaceAdapter {
    fun getScannerInfo(
        url: String,
        body: String,
        sType: Constants.SERVICETYPES,
        listner: IServiceResponseListener,
        context: Context
    )

}