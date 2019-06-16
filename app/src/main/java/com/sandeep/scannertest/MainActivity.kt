package com.sandeep.scannertest

import android.os.AsyncTask
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.sandeep.scannertest.services.*

class MainActivity : AppCompatActivity(), IServiceResponseListener {
    override fun requestErrorOccured(error: IServiceResponse) {


    }

    override fun requestCompleted(response: IServiceResponse) {
        Utility.dismissDialog()
        val servicetype = response.getRequestTagName()
        val resJsonObj = Gson().toJson(response.getServiceResponse()!!.body())
        DoDataSavingTask(Constants.SERVICETYPES.SCANNER_INFO).execute(resJsonObj)
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

    inner class DoDataSavingTask(internal var mServiecType: Constants.SERVICETYPES) : AsyncTask<String, Void, Any>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String): Any {
            when (mServiecType) {
                Constants.SERVICETYPES.SCANNER_INFO -> {
                    var userVo = ScannerHelper().setScannerData(this@MainActivity, params[0])
                }
            }
            return ""
        }

        override fun onPostExecute(result: Any) {
            when (mServiecType) {
                Constants.SERVICETYPES.SCANNER_INFO -> {
                    Utility.dismissDialog()
                }
            }
        }
    }

}
