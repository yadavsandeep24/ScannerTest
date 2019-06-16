package com.sandeep.scannertest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sandeep.scannertest.R
import com.sandeep.scannertest.database.dao.ScannerDao
import com.sandeep.scannertest.database.valueobjects.Converters
import com.sandeep.scannertest.database.valueobjects.ScannerVo

@Database(entities = [ScannerVo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun scannerDao(): ScannerDao

    companion object {
        private var appDatabase: AppDatabase? = null

        /**
         * from developers android, made my own singleton
         *
         * @param context
         * @return
         */
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, context.getString(R.string.db_name)
                ).build()
            }
            return appDatabase!!
        }
    }
}