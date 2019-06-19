package com.sandeep.scannertest.database.valueobjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VariableVo {
    val INDICATOR_TYPE = "indicator"
    val VALUE_TYPE = "value"
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("study_type")
    @Expose
    var studyType: String? = null

    @SerializedName("parameter_name")
    @Expose
    var parameterName: String? = null

    @SerializedName("min_value")
    @Expose
    var minValue: Int? = null

    @SerializedName("max_value")
    @Expose
    var maxValue: Int? = null

    @SerializedName("default_value")
    @Expose
    var defaultValue: Int? = null

    @SerializedName("values")
    @Expose
    var values: List<Double>? = null

    fun isValue(): Boolean {
        return VALUE_TYPE.equals(type)
    }

    fun isIndicator(): Boolean {
        return INDICATOR_TYPE.equals(type)
    }

    fun getValueToDisplay(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("(")
        if (isValue()) {
            stringBuilder.append(values!![0])
        } else {
            stringBuilder.append(defaultValue)
        }
        stringBuilder.append(")")
        return stringBuilder.toString()
    }
}