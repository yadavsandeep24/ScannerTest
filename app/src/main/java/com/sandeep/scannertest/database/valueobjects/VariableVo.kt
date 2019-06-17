package com.sandeep.scannertest.database.valueobjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VariableVo {

    @SerializedName("variable")
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
}