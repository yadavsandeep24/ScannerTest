package com.sandeep.scannertest.database.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sandeep.scannertest.database.AppDatabase
import com.sandeep.scannertest.database.valueobjects.ScannerVo

class ScannerViewModel(application: Application) : AndroidViewModel(application) {

    private val scannerDao = AppDatabase.getInstance(application).scannerDao()
    internal var allScanners : LiveData<List<ScannerVo>> = scannerDao.scannerInfo

    fun getSelectedScannerData(scannerID : Int) :ScannerVo{
        return scannerDao.getSelectedScanner(scannerID)
    }

    fun insertScanner(scannerVo: ScannerVo) {
        scannerDao.insertScanners(scannerVo)
    }
}
