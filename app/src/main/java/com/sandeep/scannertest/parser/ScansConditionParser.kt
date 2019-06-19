package com.sandeep.scannertest.parser

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.AbsoluteSizeSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import com.sandeep.scannertest.R
import com.sandeep.scannertest.database.valueobjects.ScannerCriteriaVo
import com.sandeep.scannertest.database.valueobjects.VariableVo


class ScansConditionParser(private val context: Context, listner:SpannableStringClickListener) {
    private val clickListener: SpannableStringClickListener? = listner

    interface SpannableStringClickListener {
        fun onClick(variableVo:VariableVo)
    }


    fun getVariableSpannableString(predefinedScanCondition: ScannerCriteriaVo): SpannableStringBuilder {
        predefinedScanCondition.displayText = predefinedScanCondition.text
        var spannableStringBuilder = SpannableStringBuilder(predefinedScanCondition.displayText)
        for (str in predefinedScanCondition.getVariableKeys()) {
            val variableFor = predefinedScanCondition.getVariableFor(str)
            val indexOf = spannableStringBuilder.toString().indexOf(str)
            spannableStringBuilder = spannableStringBuilder.replace(indexOf, str.length + indexOf, variableFor.getValueToDisplay())
            addSpans(spannableStringBuilder, predefinedScanCondition,str, indexOf, indexOf + variableFor.getValueToDisplay().length)
        }
        return spannableStringBuilder
    }


    private fun addSpans(spannableStringBuilder: SpannableStringBuilder, predefinedScanCondition: ScannerCriteriaVo, str: String, i2: Int, i3: Int) {
        val span = object : ClickableSpan() {
            override fun onClick(view: View) {
                val variableFor = predefinedScanCondition.getVariableFor(str)
                clickListener!!.onClick(variableFor)
            }
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }
        }
        if (i2 > -1) {
            spannableStringBuilder.setSpan(span, i2, i3, 33)
            spannableStringBuilder.setSpan(AbsoluteSizeSpan(16, true), i2, i3, 0)
            spannableStringBuilder.setSpan(StyleSpan(1), i2, i3, 0)
            spannableStringBuilder.setSpan(ForegroundColorSpan(context.resources.getColor(R.color.blue)), i2, i3, 0)
        }
    }
}
