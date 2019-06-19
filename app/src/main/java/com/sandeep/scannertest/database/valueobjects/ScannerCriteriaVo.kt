package com.sandeep.scannertest.database.valueobjects

import android.text.SpannableStringBuilder
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.sandeep.scannertest.constants.Constants


class ScannerCriteriaVo {

    @SerializedName("type")
    var type: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("variable")
    var variableVo: JsonObject = JsonObject()
    var spanableBuilder: SpannableStringBuilder? = null

    fun isPlainText(): Boolean {
        return this.type.equals(Constants.PLAIN_TEXT_TYPE, ignoreCase = true)
    }

    fun isVariableText(): Boolean {
        return this.type.equals(Constants.VARIABLE_TYPE, ignoreCase = true)
    }

    var displayText: String? = null

    fun getVariableKeys(): List<String> {

        val keys = variableVo.keySet()

        val arrayList = ArrayList<String>()
        for (key in keys) {
            arrayList.add(key)
        }
        return arrayList
    }

    fun getVariableFor(str: String): VariableVo {
        val keydata = variableVo.getAsJsonObject(str).toString()
        val gson = Gson()
        val listType = object : TypeToken<VariableVo>() {}.type
        return gson.fromJson<VariableVo>(keydata, listType);
    }

}
