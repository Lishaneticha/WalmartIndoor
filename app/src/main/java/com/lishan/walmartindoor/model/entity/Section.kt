package com.lishan.walmartindoor.model.entity

import androidx.compose.ui.geometry.Offset
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sections",
    indices = [Index(value = ["name"], unique = true)]
)
data class Section(
    @PrimaryKey(autoGenerate = true) val sectionId: Long = 0,
    val name: String
)