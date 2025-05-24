package com.lishan.walmartindoor.view.di

import android.content.Context
import androidx.room.Room
import com.lishan.walmartindoor.model.StoreDatabase
import com.lishan.walmartindoor.model.dao.ShelfUpdateLogDao
import com.lishan.walmartindoor.model.dao.StoreDao
import com.lishan.walmartindoor.model.repository.StoreRepository
import com.lishan.walmartindoor.model.repository.StoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStoreDatabase(
        @ApplicationContext context: Context
    ): StoreDatabase {
        return Room.databaseBuilder(
            context,
            StoreDatabase::class.java,
            "store_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideStoreDao(
        database: StoreDatabase
    ): StoreDao {
        return database.storeDao()
    }

    @Provides
    @Singleton
    fun provideShelfUpdateLogDao(
        database: StoreDatabase
    ): ShelfUpdateLogDao{
        return database.shelfUpdateLogDao()
    }

    @Provides
    @Singleton
    fun provideStoreRepository(
        storeDao: StoreDao,
        shelfUpdateLogDao: ShelfUpdateLogDao
    ): StoreRepository{
        return StoreRepositoryImpl(storeDao, shelfUpdateLogDao)
    }
}