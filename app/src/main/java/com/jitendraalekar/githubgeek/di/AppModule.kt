package com.jitendraalekar.githubgeek.di

import com.jitendraalekar.githubgeek.data.IRepository
import com.jitendraalekar.githubgeek.data.RepositoryImpl
import com.jitendraalekar.githubgeek.data.model.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object  AppModule {

    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}
