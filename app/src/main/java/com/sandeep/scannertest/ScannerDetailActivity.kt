package com.sandeep.scannertest

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sandeep.scannertest.database.valueobjects.ScannerVo
import com.sandeep.scannertest.database.valueobjects.VariableVo
import com.sandeep.scannertest.database.viewModel.ScannerViewModel
import com.sandeep.scannertest.services.Utility

class ScannerDetailActivity : BaseActivity() {

    private lateinit var mScannerViewModel: ScannerViewModel
    var scannerID:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_detail)
        setToolbar("Scanner Detail")
        val bundle = intent.extras
        scannerID = bundle!!.getInt("scannerid")
        mScannerViewModel = ViewModelProviders.of(this@ScannerDetailActivity).get(ScannerViewModel::class.java)
        DoDataSavingTask().execute()

    }

    inner class DoDataSavingTask() : AsyncTask<String, Void, ScannerVo>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String): ScannerVo {
            var scannerVo = mScannerViewModel.getSelectedScannerData(scannerID)
            for (i in 0 until scannerVo.criteriaVos.size) {
                val criteriaVOs = scannerVo.criteriaVos.get(i)
             if(criteriaVOs.isVariableText()) {
                 val keyList = criteriaVOs.getVariableKeys()
                 for (j in 0..keyList.size - 1) {
                     val key =keyList.get(j)
                     Log.d("PRISA",key)
                     val keydata = criteriaVOs.variableVo.getAsJsonObject(key).toString()
                     val gson = Gson()
                     val listType = object : TypeToken<VariableVo>() {}.type
                     val variableVo = gson.fromJson<VariableVo>(keydata, listType);
                 }
                }
            }

            return mScannerViewModel.getSelectedScannerData(scannerID)
        }

        override fun onPostExecute(scannerVo: ScannerVo) {
            (findViewById(R.id.tv_header) as TextView).text =  scannerVo.name
            (findViewById(R.id.tv_subheader) as TextView).text =  scannerVo.tag
            (findViewById(R.id.tv_subheader) as TextView).setTextColor(Utility.getSubHeaderColor(this@ScannerDetailActivity,scannerVo.color))
            val container = findViewById(R.id.scanner_condition_list_container) as LinearLayout
            for (i in 0 until scannerVo.criteriaVos.size) {
                val inflater_chapter = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view = inflater_chapter.inflate(R.layout.scanner_detail_dynamic_list, null)
                val tvContent: TextView
                tvContent = view.findViewById(R.id.tv_dynamic_content)
                tvContent.setText(scannerVo.criteriaVos.get(i).text +"\n\n and \n")
                if(i == scannerVo.criteriaVos.size-1) {
                    tvContent.setText(scannerVo.criteriaVos.get(i).text)
                }
                container.addView(view)

            }
        }
    }
}
