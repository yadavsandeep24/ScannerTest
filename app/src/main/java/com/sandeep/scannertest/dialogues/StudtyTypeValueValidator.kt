package com.sandeep.scannertest.dialogues

import android.text.Editable
import android.text.TextWatcher
import com.sandeep.scannertest.database.valueobjects.VariableVo

class StudtyTypeValueValidator(vo:VariableVo): TextWatcher {
    internal var isValid = false
    internal val variableVo : VariableVo = vo

    override fun afterTextChanged(editableText: Editable) {
        isValid = isValidInput(variableVo,editableText.toString())
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit

    companion object {

        fun isValidInput(vo: VariableVo, editableText: String): Boolean {
            try {
                val input =Integer.parseInt(editableText)
                if(input< vo.minValue!!) {
                    return  false
                }else if(input>vo.maxValue!!) {
                    return false
                }else {
                    return true
                }
            } catch (e: Exception) {
                return false
            }
        }
    }
}