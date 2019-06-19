package com.sandeep.scannertest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sandeep.scannertest.database.valueobjects.ScannerVo


@Dao
interface ScannerDao {
    @get:Query("SELECT * FROM ScannerCondition")
    val scannerInfo: LiveData<List<ScannerVo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScanners(vararg scannerVo: ScannerVo)

    @Delete
    fun delete(scannerVos: ScannerVo)

    @Query("SELECT * FROM ScannerCondition where Id = :scannerID")
    fun getSelectedScanner(scannerID: Int): ScannerVo
}