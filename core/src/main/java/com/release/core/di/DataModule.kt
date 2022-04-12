package com.release.core.di

import com.release.data.model.StartResponse
import com.release.data.repositories.AuthRepositoryImpl
import com.release.data.repositories.InspectionsRepositoryImpl
import com.release.data.utils.mapper.InspectionMapper
import com.release.data.utils.mapper.NetworkUiMapper
import com.release.domain.model.InspectionItem
import com.release.domain.repositories.AuthRepository
import com.release.domain.repositories.InspectionsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindInspectionsRepository(inspectionsRepositoryImpl: InspectionsRepositoryImpl): InspectionsRepository

    @Binds
    abstract fun provideInspectionMapper(inspectionMapper: InspectionMapper): NetworkUiMapper<StartResponse, InspectionItem>
}
