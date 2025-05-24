package com.lishan.walmartindoor.model.repository

import com.lishan.walmartindoor.model.entity.Section
import com.lishan.walmartindoor.model.relation.SectionWithShelves
import com.lishan.walmartindoor.model.entity.Shelf
import com.lishan.walmartindoor.model.entity.ShelfUpdateLog

interface StoreRepository {
    // Section
    suspend fun insertSection(section: Section): Long
    suspend fun getSectionsWithShelves(): List<SectionWithShelves>

    // Shelf
    suspend fun insertShelf(shelf: Shelf): Long
    suspend fun updateShelf(shelf: Shelf)
    suspend fun getShelfById(id: Long): Shelf

    // Shelf Update Log
    suspend fun insertShelfLog(log: ShelfUpdateLog)
    suspend fun getShelfLogs(shelfId: Long): List<ShelfUpdateLog>

    suspend fun insertSectionWithShelves(section: Section, shelves: List<Shelf>)
}