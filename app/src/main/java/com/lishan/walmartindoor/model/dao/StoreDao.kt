package com.lishan.walmartindoor.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.lishan.walmartindoor.model.entity.Section
import com.lishan.walmartindoor.model.relation.SectionWithShelves
import com.lishan.walmartindoor.model.entity.Shelf

@Dao
interface StoreDao {

    @Query("SELECT * FROM sections WHERE name = :name LIMIT 1")
    suspend fun getSectionByName(name: String): Section?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSection(section: Section): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShelf(shelf: Shelf): Long

    @Transaction
    @Query("SELECT * FROM sections")
    suspend fun getAllSectionsWithShelves(): List<SectionWithShelves>

    @Update
    suspend fun updateShelf(shelf: Shelf)

    @Query("SELECT * FROM shelves WHERE shelfId = :id")
    suspend fun getShelfById(id: Long): Shelf

    @Delete
    suspend fun deleteShelf(shelf: Shelf)

    @Delete
    suspend fun deleteSection(section: Section)

    @Insert
    suspend fun insertShelves(shelves: List<Shelf>)

    @Transaction
    suspend fun insertSectionWithShelves(section: Section, shelves: List<Shelf>) {
        val existing = getSectionByName(section.name)
        val sectionId = existing?.sectionId ?: insertSection(section)

        val shelvesWithSectionId = shelves.map {
            it.copy(sectionOwnerId = sectionId)
        }

        insertShelves(shelvesWithSectionId)
    }
}