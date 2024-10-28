package com.cred.sampleapp.di

import com.cred.sampleapp.data.repository.StashRepository
import com.cred.sampleapp.data.repository.api.StashRepositoryImpl
import com.cred.sampleapp.data.repository.api.APIClient
import com.cred.sampleapp.data.repository.api.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideAPIService(): APIService = APIClient.getClient()

    @Provides
    fun provideStashRepositoryImpl(
        apiService: APIService
    ): StashRepositoryImpl = StashRepositoryImpl(apiService)

    @Provides
    fun provideStashRepository(
        stashRepositoryImpl: StashRepositoryImpl
    ): StashRepository = stashRepositoryImpl

}