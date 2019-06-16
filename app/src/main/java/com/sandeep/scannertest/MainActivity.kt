package com.sandeep.scannertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.google.gson.Gson
import com.sandeep.scannertest.services.*

class MainActivity : AppCompatActivity(), IServiceResponseListener {
    override fun requestErrorOccured(error: IServiceResponse) {

    }

    override fun requestCompleted(response: IServiceResponse) {
        Utility.dismissDialog()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getServiceData()
    }

    private fun getServiceData() {
        if (Utility.isNetworkConnectionAvailable(this)) {
            Utility.showProgressDialog(resources.getString(R.string.al_please_wait), this)
            val url = resources.getString(R.string.service_url_root)
            ServiceContoller.getInstance().getServiceAdapter().getScannerInfo(
                url,"",
                Constants.SERVICETYPES.SCANNER_INFO,
                this@MainActivity,this@MainActivity)
        }else{
            Utility.showToast(this,resources.getString(R.string.al_no_network),Toast.LENGTH_LONG,Gravity.CENTER)
        }
    }
}
