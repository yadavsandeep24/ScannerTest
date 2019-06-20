package com.sandeep.scannertest

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.sandeep.scannertest.activities.BaseActivity
import com.sandeep.scannertest.activities.ScannerDetailActivity
import com.sandeep.scannertest.adapter.ScannnerMainListAdapter
import com.sandeep.scannertest.constants.Constants
import com.sandeep.scannertest.database.valueobjects.ScannerVo
import com.sandeep.scannertest.databinding.ActivityMainBinding
import com.sandeep.scannertest.listners.ScannerMainListItemClickListner
import com.sandeep.scannertest.services.IServiceResponse
import com.sandeep.scannertest.services.IServiceResponseListener
import com.sandeep.scannertest.services.ScannerHelper
import com.sandeep.scannertest.services.ServiceContoller
import com.sandeep.scannertest.utility.Utility

class MainActivity : BaseActivity(), IServiceResponseListener, ScannerMainListItemClickListner {
    private lateinit var mDataBinding: ActivityMainBinding
    private lateinit var mScannerAdapter: ScannnerMainListAdapter

    override fun onListItemClick(id: Int) {
        val i = Intent(this@MainActivity, ScannerDetailActivity::class.java)
        i.putExtra(Constants.KEY_SCANNERID, id)
        startActivity(i)
    }

    override fun requestErrorOccured(error: IServiceResponse) {
        Utility.dismissDialog()
    }

    override fun requestCompleted(response: IServiceResponse) {
        Utility.dismissDialog()
        val resJsonObj = Gson().toJson(response.getServiceResponse()!!.body())
        DoDataSavingTask(Constants.SERVICETYPES.SCANNER_INFO).execute(resJsonObj)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialisation()
        setToolbar(getString(R.string.title_scanner))
        getServiceData()
    }

    private fun initialisation() {
        mDataBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        mScannerAdapter = ScannnerMainListAdapter(this, this@MainActivity);
        mDataBinding.layoutManager =  LinearLayoutManager(this@MainActivity)
        mDataBinding.adapter = mScannerAdapter
    }

    private fun getServiceData() {
        if (Utility.isNetworkConnectionAvailable(this)) {
            Utility.showProgressDialog(resources.getString(R.string.al_please_wait), this)
            val url = resources.getString(R.string.service_url_root)
            ServiceContoller.getInstance().getServiceAdapter().getScannerInfo(
                url, "",
                Constants.SERVICETYPES.SCANNER_INFO,
                this@MainActivity, this@MainActivity
            )
        } else {
            Utility.showToast(this, resources.getString(R.string.al_no_network), Toast.LENGTH_LONG)
        }
    }

    inner class DoDataSavingTask(internal var mServiecType: Constants.SERVICETYPES) : AsyncTask<String, Void,  ArrayList<ScannerVo>>() {

        override fun doInBackground(vararg params: String): ArrayList<ScannerVo>? {
            when (mServiecType) {
                Constants.SERVICETYPES.SCANNER_INFO -> {
                   return  ScannerHelper().setScannerData(this@MainActivity,params[0])
                }
            }
        }

        override fun onPostExecute(result:  ArrayList<ScannerVo>) {
            when (mServiecType) {
                Constants.SERVICETYPES.SCANNER_INFO -> {
                    Utility.dismissDialog()
                    val handler = Handler()
                    handler.postDelayed(Runnable {
                        mDataBinding.adapter!!.setData(result)
                    }, 100)

                }
            }
        }
    }

}
