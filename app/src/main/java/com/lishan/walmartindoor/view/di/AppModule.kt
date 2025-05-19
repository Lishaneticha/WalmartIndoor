package com.lishan.walmartindoor.view.di

import android.content.Context
import androidx.room.Room
import com.lishan.walmartindoor.model.StoreDatabase
import com.lishan.walmartindoor.model.dao.StoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStoreDatabase(
        appContext: Context
    ): StoreDatabase {
        return Room.databaseBuilder(
            appContext,
            StoreDatabase::class.java,
            "store_database"
        ).build()
    }

    @Provides
    fun provideStoreDao(
        database: StoreDatabase
    ): StoreDao {
        return database.storeDao()
    }
}