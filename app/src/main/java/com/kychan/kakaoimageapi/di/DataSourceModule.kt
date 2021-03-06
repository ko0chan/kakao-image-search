package com.kychan.kakaoimageapi.di

import com.kychan.kakaoimageapi.data.network.SearchRemoteDataSource
import com.kychan.kakaoimageapi.data.network.SearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindSearchRemoteDataSource(searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl): SearchRemoteDataSource
}