package com.sandeep.scannertest.database.valueobjects

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.sandeep.scannertest.services.Constants


class ScannerCriteriaVo {

    @SerializedName("type")
    var type: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("variable")
    var variableVo:JsonObject= JsonObject()

    fun isPlainText(): Boolean {
        return this.type.equals(Constants.PLAIN_TEXT_TYPE,ignoreCase = true)
    }

    fun isVariableText(): Boolean {
        return this.type.equals(Constants.VARIABLE_TYPE,ignoreCase = true)
    }

    fun getVariableKeys(): List<String> {

        val keys = variableVo.keySet()

        val arrayList = ArrayList<String>()
        for (key in keys) {
            arrayList.add(key)
        }
        return arrayList
    }


}
