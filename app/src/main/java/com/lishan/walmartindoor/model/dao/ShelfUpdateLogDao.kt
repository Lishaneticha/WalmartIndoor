package com.lishan.walmartindoor.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lishan.walmartindoor.model.entity.ShelfUpdateLog

@Dao
interface ShelfUpdateLogDao {

    @Insert
    suspend fun insertLog(log: ShelfUpdateLog)

    @Query("SELECT * FROM shelf_update_logs WHERE shelfId = :shelfId ORDER BY updatedAt DESC")
    suspend fun getLogsForShelf(shelfId: Long): List<ShelfUpdateLog>
}