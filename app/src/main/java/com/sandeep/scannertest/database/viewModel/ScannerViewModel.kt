package com.sandeep.scannertest.database.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sandeep.scannertest.database.AppDatabase
import com.sandeep.scannertest.database.valueobjects.ScannerVo

class ScannerViewModel(application: Application) : AndroidViewModel(application) {

    private val scannerDao = AppDatabase.getInstance(application).scannerDao()

    fun getScannerList(): LiveData<List<ScannerVo>> {
        return scannerDao.scannerInfo
    }

    fun getSelectedScannerData(scannerID : Int) :ScannerVo{
        return scannerDao.getSelectedScanner(scannerID)
    }
}
