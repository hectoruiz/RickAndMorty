package hector.ruiz.rickandmorty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hector.ruiz.datasource.api.ApiClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providerApiClient(apiClient: ApiClient) = apiClient.retrofit
}
