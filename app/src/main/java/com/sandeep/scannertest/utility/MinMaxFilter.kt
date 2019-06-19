package com.sandeep.scannertest.utility

import android.text.InputFilter
import android.text.Spanned


class MinMaxFilter(minValue: Int, maxValue: Int) : InputFilter {

    private var mIntMin: Int = minValue
    private var mIntMax: Int = maxValue


    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = Integer.parseInt(dest.toString() + source.toString())
            if (isInRange(mIntMin, mIntMax, input))
                return null
        } catch (nfe: NumberFormatException) {
        }

        return ""
    }

    private fun isInRange(a: Int, b: Int, c: Int): Boolean {
        return if (b > a) c >= a && c <= b else c >= b && c <= a
    }
}