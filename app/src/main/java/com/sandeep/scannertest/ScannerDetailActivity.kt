package com.sandeep.scannertest

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.sandeep.scannertest.database.valueobjects.ScannerVo
import com.sandeep.scannertest.database.valueobjects.VariableVo
import com.sandeep.scannertest.database.viewModel.ScannerViewModel
import com.sandeep.scannertest.dialogues.ScannerVariableIndicatorDialog
import com.sandeep.scannertest.dialogues.ScannerVariableValueDialog
import com.sandeep.scannertest.parser.ScansConditionParser
import com.sandeep.scannertest.services.Utility

class ScannerDetailActivity : BaseActivity(), ScansConditionParser.SpannableStringClickListener {
    override fun onClick(variableVO: VariableVo) {
        if (variableVO.isValue()) {
            val dialog = ScannerVariableValueDialog(this@ScannerDetailActivity, R.style.CustomDialogTheme)
            dialog.setData(variableVO)
            dialog.show()
        } else if (variableVO.isIndicator()) {
            val dialog = ScannerVariableIndicatorDialog(this@ScannerDetailActivity, R.style.CustomDialogTheme)
            dialog.setData(variableVO)
            dialog.show()
        } else {
            Utility.showToast(this, "New scanner type.", Toast.LENGTH_LONG)
        }

    }

    private lateinit var mScannerViewModel: ScannerViewModel
    var scannerID: Int = 0

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
        override fun doInBackground(vararg params: String): ScannerVo {
            var scannerVo = mScannerViewModel.getSelectedScannerData(scannerID)
            for (i in 0 until scannerVo.criteriaVos.size) {
                val criteriaVOs = scannerVo.criteriaVos.get(i)
                val builder = ScansConditionParser(
                    this@ScannerDetailActivity,
                    this@ScannerDetailActivity
                ).getVariableSpannableString(criteriaVOs)
                scannerVo.criteriaVos[i].spanableBuilder = builder
            }
            return scannerVo
        }

        override fun onPostExecute(scannerVo: ScannerVo) {
            (findViewById(R.id.tv_header) as TextView).text = scannerVo.name
            (findViewById(R.id.tv_subheader) as TextView).text = scannerVo.tag
            (findViewById(R.id.tv_subheader) as TextView).setTextColor(
                Utility.getSubHeaderColor(
                    this@ScannerDetailActivity,
                    scannerVo.color
                )
            )
            val container = findViewById(R.id.scanner_condition_list_container) as LinearLayout
            for (i in 0 until scannerVo.criteriaVos.size) {
                val inflater_chapter = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view = inflater_chapter.inflate(R.layout.scanner_detail_dynamic_list, null)
                val tvContent: TextView
                tvContent = view.findViewById(R.id.tv_dynamic_content)
                if (i == scannerVo.criteriaVos.size - 1) {
                    tvContent.setText(scannerVo.criteriaVos.get(i).spanableBuilder)
                } else {
                    tvContent.setText(scannerVo.criteriaVos.get(i).spanableBuilder!!.append("\n\n and \n"))
                }
                tvContent.setMovementMethod(LinkMovementMethod.getInstance());
                container.addView(view)

            }
        }
    }
}
