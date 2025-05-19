package com.lishan.walmartindoor.model.entity

import androidx.compose.ui.geometry.Offset
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "shelves",
    foreignKeys = [
        ForeignKey(
            entity = Section::class,
            parentColumns = ["sectionId"],
            childColumns = ["sectionOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Shelf(
    @PrimaryKey(autoGenerate = true) val shelfId: Long = 0,
    val label: String,
    val sectionOwnerId: Long, // Foreign key linking to Section
    val offsetX: Float,
    val offsetY: Float
)