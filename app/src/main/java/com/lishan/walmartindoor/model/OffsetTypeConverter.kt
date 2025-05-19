package com.lishan.walmartindoor.model

import androidx.compose.ui.geometry.Offset
import androidx.room.TypeConverter

class OffsetTypeConverter {

    @TypeConverter
    fun fromOffset(offset: Offset): String {
        return "${offset.x},${offset.y}"
    }

    @TypeConverter
    fun toOffset(value: String): Offset {
        val parts = value.split(",")
        return Offset(parts[0].toFloat(), parts[1].toFloat())
    }
}