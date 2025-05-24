package com.lishan.walmartindoor.model.repository

import com.lishan.walmartindoor.model.dao.StoreDao
import com.lishan.walmartindoor.model.dao.ShelfUpdateLogDao
import com.lishan.walmartindoor.model.entity.Section
import com.lishan.walmartindoor.model.relation.SectionWithShelves
import com.lishan.walmartindoor.model.entity.Shelf
import com.lishan.walmartindoor.model.entity.ShelfUpdateLog
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDao: StoreDao,
    private val shelfUpdateLogDao: ShelfUpdateLogDao
) : StoreRepository {

    // Section
    override suspend fun insertSection(section: Section): Long {
        return storeDao.insertSection(section)
    }

    override suspend fun getSectionsWithShelves(): List<SectionWithShelves> {
        return storeDao.getAllSectionsWithShelves()
    }

    // Shelf
    override suspend fun insertShelf(shelf: Shelf): Long {
        return storeDao.insertShelf(shelf)
    }

    override suspend fun updateShelf(shelf: Shelf) {
        storeDao.updateShelf(shelf)
    }

    override suspend fun getShelfById(id: Long): Shelf {
        return storeDao.getShelfById(id)
    }

    // Shelf Update Log
    override suspend fun insertShelfLog(log: ShelfUpdateLog) {
        shelfUpdateLogDao.insertLog(log)
    }

    override suspend fun getShelfLogs(shelfId: Long): List<ShelfUpdateLog> {
        return shelfUpdateLogDao.getLogsForShelf(shelfId)
    }

    override suspend fun insertSectionWithShelves(section: Section, shelves: List<Shelf>) {
        storeDao.insertSectionWithShelves(section, shelves)
    }
}