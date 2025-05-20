package com.lishan.walmartindoor.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lishan.walmartindoor.model.dao.ShelfUpdateLogDao
import com.lishan.walmartindoor.model.dao.StoreDao
import com.lishan.walmartindoor.model.entity.Section
import com.lishan.walmartindoor.model.entity.Shelf
import com.lishan.walmartindoor.model.entity.ShelfUpdateLog

@Database(
    entities = [Section::class, Shelf::class, ShelfUpdateLog::class],
    version = 2,
    exportSchema = false
)

//@TypeConverters(OffsetTypeConverter::class)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun storeDao(): StoreDao
    abstract fun shelfUpdateLogDao(): ShelfUpdateLogDao

    companion object {
        @Volatile
        private var INSTANCE: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreDatabase::class.java,
                    "store_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}