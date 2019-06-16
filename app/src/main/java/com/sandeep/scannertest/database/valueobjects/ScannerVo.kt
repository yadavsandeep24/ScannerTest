package com.sandeep.scannertest.database.valueobjects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "ScannerCondition")
class ScannerVo {
    @PrimaryKey
    @ColumnInfo(name = "Id")
    @SerializedName("id")
    var scannerID: Int =0

    @ColumnInfo(name = "Name")
    @SerializedName("name")
    var name: String = ""

    @ColumnInfo(name = "Tag")
    @SerializedName("tag")
    var tag: String = ""

    @ColumnInfo(name = "Color")
    @SerializedName("color")
    var color: String = ""

    @ColumnInfo(name = "Criteria")
    @SerializedName("criteria")
    var criteriaVos = ArrayList<ScannerCriteriaVo>()

}
