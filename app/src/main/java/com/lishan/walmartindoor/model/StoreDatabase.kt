package com.lishan.walmartindoor.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lishan.walmartindoor.model.dao.StoreDao
import com.lishan.walmartindoor.model.entity.Section
import com.lishan.walmartindoor.model.entity.Shelf

@Database(
    entities = [Section::class, Shelf::class],
    version = 1,
    exportSchema = false
)

//@TypeConverters(OffsetTypeConverter::class)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun storeDao(): StoreDao

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