package com.sandeep.scannertest.database.dao

import androidx.room.*
import com.sandeep.scannertest.database.valueobjects.ScannerVo


@Dao
interface ScannerDao {
    @get:Query("SELECT * FROM ScannerCondition")
    val getScannerInfo: List<ScannerVo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScanners(vararg scannerVo: ScannerVo)

    @Delete
    fun delete(scannerVos: ScannerVo)
}