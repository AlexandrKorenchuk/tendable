package com.release.core.di

import com.release.data.database.InspectionsRealm
import com.release.data.database.InspectionsRealmImpl
import com.release.data.database.TendableDatabase
import com.release.data.database.TendableDatabaseImpl
import com.release.data.database.entity.InspectionsEntity
import com.release.data.database.entity.QuestionEntity
import com.release.data.model.Inspection
import com.release.data.model.StartResponse
import com.release.data.repositories.AuthRepositoryImpl
import com.release.data.repositories.InspectionsRepositoryImpl
import com.release.data.utils.mapper.*
import com.release.domain.model.InspectionItem
import com.release.domain.model.QuestionItem
import com.release.domain.repositories.AuthRepository
import com.release.domain.repositories.InspectionsRepository
import dagger.Binds
import dagger.Module
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
    abstract fun provideInspectionMapper(inspectionNetworkUIMapper: InspectionNetworkUIMapper): DataUiMapper<StartResponse, InspectionItem>
}
