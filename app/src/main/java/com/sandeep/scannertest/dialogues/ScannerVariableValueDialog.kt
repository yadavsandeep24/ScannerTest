package com.sandeep.scannertest.dialogues

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.scannertest.R
import com.sandeep.scannertest.adapter.ValueAdapter
import com.sandeep.scannertest.database.valueobjects.VariableVo


class ScannerVariableValueDialog(private val mContext: Context, theme: Int) : Dialog(mContext, theme) {


    private lateinit var recyclerViewValues: RecyclerView
    private lateinit var valueAdapter: ValueAdapter

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_value_detail)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setCancelable(true)
        initView()
    }


    private fun initView() {
        recyclerViewValues = findViewById(R.id.value_recyclerview)
        recyclerViewValues.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        valueAdapter = ValueAdapter()
        recyclerViewValues.layoutManager = layoutManager
        recyclerViewValues.adapter = valueAdapter
    }

    fun setData(variableVO: VariableVo) {
        valueAdapter.setData(variableVO.values!!)


    }
}



