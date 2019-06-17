package com.sandeep.scannertest

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sandeep.scannertest.adapter.ScannnerMainListAdapter
import com.sandeep.scannertest.database.valueobjects.ScannerVo
import com.sandeep.scannertest.database.viewModel.ScannerViewModel
import com.sandeep.scannertest.databinding.ActivityMainBinding
import com.sandeep.scannertest.listners.ScannerMainListItemClickListner
import com.sandeep.scannertest.services.*
import java.util.*

class MainActivity : BaseActivity(), IServiceResponseListener, ScannerMainListItemClickListner {
    private lateinit var  mDataBinding: ActivityMainBinding
    private lateinit var mScannerViewModel: ScannerViewModel
    private lateinit var mScannerAdapter: ScannnerMainListAdapter

    override fun onListItemClick(id: Int) {
        val i = Intent(this@MainActivity, ScannerDetailActivity::class.java)
        i.putExtra("scannerid",id)
        startActivity(i)
    }

    override fun requestErrorOccured(error: IServiceResponse) {
        Utility.dismissDialog()
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
        initialisation()
        setToolbar("Scanner")
        getServiceData()
    }

    private fun initialisation() {
        mDataBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        mScannerAdapter = ScannnerMainListAdapter(this, this@MainActivity);
        mScannerViewModel = ViewModelProviders.of(this@MainActivity).get(ScannerViewModel::class.java)
        val layoutManager_product = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        layoutManager_product.setAutoMeasureEnabled(true)
        mDataBinding.layoutManager = layoutManager_product
        mDataBinding.adapter = mScannerAdapter

        mScannerViewModel.getScannerList().observe(this, androidx.lifecycle.Observer<List<ScannerVo>> {
            mScannerAdapter.setData(it as ArrayList<ScannerVo>)
        })
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
                    ScannerHelper().setScannerData(this@MainActivity, params[0])
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
