package com.sandeep.scannertest.database.valueobjects

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

public class Converters {
    @TypeConverter
    public fun criteriaCollectionList(value: String): ArrayList<ScannerCriteriaVo>? {
        val listType = object : TypeToken<ArrayList<ScannerCriteriaVo>>() { }.type
        return Gson().fromJson<ArrayList<ScannerCriteriaVo>>(value, listType)
    }

    @TypeConverter
    public fun criteriaArraylistToString(list: ArrayList<ScannerCriteriaVo>): String {
        val gson = Gson()
        return gson.toJson(list)
        // return date == null ? null : date.getTime();
    }
}
