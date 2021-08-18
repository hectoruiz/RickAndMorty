package hector.ruiz.rickandmorty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hector.ruiz.datasource.sharedpreferences.ApiPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MemoryModule {

    @Provides
    @Singleton
    fun providerSharedPreferences(apiPreferences: ApiPreferences) = apiPreferences.sharedPreferences
}
