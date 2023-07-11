package com.tambi.core.storage.di

import com.tambi.core.storage.preference.DataStoreStorage
import com.tambi.core.storage.preference.Storage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun provideStorage(storage: DataStoreStorage): Storage
}
