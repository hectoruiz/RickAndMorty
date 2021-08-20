package hector.ruiz.rickandmorty.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hector.ruiz.data.datasources.MemoryDataSource
import hector.ruiz.data.repositories.FavoriteRepositoryImpl
import hector.ruiz.datasource.datasources.MemoryDataSourceImpl
import hector.ruiz.datasource.sharedpreferences.ApiPreferences
import hector.ruiz.usecase.repositories.FavoriteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MemoryModule {

    @Provides
    @Singleton
    fun providerSharedPreferences(@ApplicationContext context: Context): ApiPreferences {
        return ApiPreferences(context)
    }

    @Provides
    fun providerMemoryDataSource(apiPreferences: ApiPreferences): MemoryDataSource {
        return MemoryDataSourceImpl(apiPreferences)
    }

    @Provides
    fun providerFavoriteRepository(memoryDataSource: MemoryDataSource): FavoriteRepository {
        return FavoriteRepositoryImpl(memoryDataSource)
    }
}
