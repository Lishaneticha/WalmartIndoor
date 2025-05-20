package com.lishan.walmartindoor.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shelf_update_logs")
data class ShelfUpdateLog(
    @PrimaryKey(autoGenerate = true) val logId: Long = 0,
    val shelfId: Long,
    val updatedAt: Long = System.currentTimeMillis(), // Timestamp of update
    val updatedLabel: String? = null,
    val updatedBy: String? = null, // Optional: user or source
    val note: String? = null       // Optional: what changed
)