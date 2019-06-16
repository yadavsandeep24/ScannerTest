package com.sandeep.scannertest.database.valueobjects

import com.google.gson.annotations.SerializedName

class ScannerCriteriaVo {

    @SerializedName("type")
    var type: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("variable")
    var variable: Variable? = null

}
