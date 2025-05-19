package com.lishan.walmartindoor.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.lishan.walmartindoor.model.entity.Section
import com.lishan.walmartindoor.model.entity.SectionWithShelves
import com.lishan.walmartindoor.model.entity.Shelf

@Dao
interface StoreDao {

    @Insert
    suspend fun insertSection(section: Section): Long

    @Insert
    suspend fun insertShelf(shelf: Shelf): Long

    @Transaction
    @Query("SELECT * FROM sections")
    suspend fun getAllSectionsWithShelves(): List<SectionWithShelves>

    @Delete
    suspend fun deleteShelf(shelf: Shelf)

    @Delete
    suspend fun deleteSection(section: Section)
}