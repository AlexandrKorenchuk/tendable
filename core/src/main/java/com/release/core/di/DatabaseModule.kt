package com.release.core.di

import com.release.data.database.InspectionsRealm
import com.release.data.database.InspectionsRealmImpl
import com.release.data.database.TendableDatabase
import com.release.data.database.TendableDatabaseImpl
import com.release.data.database.entity.InspectionsEntity
import com.release.data.database.entity.QuestionEntity
import com.release.data.model.Inspection
import com.release.data.utils.mapper.DataUiMapper
import com.release.data.utils.mapper.InspectionDbUiMapper
import com.release.data.utils.mapper.InspectionNetworkDBMapper
import com.release.data.utils.mapper.QuestionDbUiMapper
import com.release.domain.model.InspectionItem
import com.release.domain.model.QuestionItem
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    abstract fun provideTendableDatabase(databaseImpl: TendableDatabaseImpl): TendableDatabase

    @Binds
    abstract fun provideInspectionsRealm(inspections: InspectionsRealmImpl): InspectionsRealm

    @Binds
    abstract fun provideInspectionMapperDB(mapperNetwork: InspectionNetworkDBMapper): DataUiMapper<Inspection, InspectionsEntity>

    @Binds
    abstract fun provideInspectionDbUiMapper(mapperNetwork: InspectionDbUiMapper): DataUiMapper<InspectionsEntity, InspectionItem>

    @Binds
    abstract fun provideQuestionDbUiMapper(questionDbUiMapper: QuestionDbUiMapper): DataUiMapper<QuestionEntity, QuestionItem>

}
