package com.sandeep.scannertest.database.valueobjects

import com.google.gson.annotations.SerializedName

class `$1` {

    @SerializedName("type")
    var type: String? = null

    @SerializedName("values")
    var values: List<Int>? = null

    @SerializedName("study_type")
    var studyType: String? = null

    @SerializedName("parameter_name")
    var parameterName: String? = null

    @SerializedName("min_value")
    var minValue: Int? = null

    @SerializedName("max_value")
    var maxValue: Int? = null

    @SerializedName("default_value")
    var defaultValue: Int? = null

}