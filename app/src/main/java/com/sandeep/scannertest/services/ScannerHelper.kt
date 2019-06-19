package com.sandeep.scannertest.services

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sandeep.scannertest.database.valueobjects.ScannerVo
import java.util.*

class ScannerHelper {
    fun setScannerData(data: String): ArrayList<ScannerVo> {
        var list = ArrayList<ScannerVo>()
        try {
            if (!TextUtils.isEmpty(data)) {
                val gson = Gson()
                val listType = object : TypeToken<ArrayList<ScannerVo>>() {}.type
                list = gson.fromJson<ArrayList<ScannerVo>>(data, listType);

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}