package com.sandeep.scannertest.services

import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sandeep.scannertest.database.AppDatabase
import com.sandeep.scannertest.database.valueobjects.ScannerVo
import java.util.*

class ScannerHelper {
    fun setScannerData(context: Context,data: String): ArrayList<ScannerVo> {
        var list = ArrayList<ScannerVo>()
        try {
            if (!TextUtils.isEmpty(data)) {
                val gson = Gson()
                val listType = object : TypeToken<ArrayList<ScannerVo>>() {}.type
                list = gson.fromJson<ArrayList<ScannerVo>>(data, listType);
                if (list.size > 0) {
                    for (i in 0..list.size - 1) {
                        AppDatabase.getInstance(context).scannerDao().insertScanners(list.get(i))
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}