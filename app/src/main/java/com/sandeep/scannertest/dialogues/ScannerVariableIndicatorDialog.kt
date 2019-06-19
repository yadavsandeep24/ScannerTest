package com.sandeep.scannertest.dialogues

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.sandeep.scannertest.R
import com.sandeep.scannertest.database.valueobjects.VariableVo
import android.text.InputFilter



class ScannerVariableIndicatorDialog(mContext: Context, theme: Int) : Dialog(mContext, theme) {


    private lateinit var edtStudyTypeValue: EditText
    private lateinit var tvHeader: TextView

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_indicator_detail)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setCancelable(true)
        initView()
    }


    private fun initView() {
        tvHeader = findViewById(R.id.header)
        edtStudyTypeValue = findViewById(R.id.edt_value)

    }

    fun setData(variableVO: VariableVo) {
        tvHeader.text = variableVO.studyType!!.toUpperCase()
        edtStudyTypeValue.setText(variableVO.defaultValue.toString())
        edtStudyTypeValue.setFilters(arrayOf<InputFilter>(MinMaxFilter(variableVO.minValue!!, variableVO.maxValue!!)))


    }
}



