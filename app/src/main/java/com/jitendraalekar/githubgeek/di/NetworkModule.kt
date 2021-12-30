package com.jitendraalekar.githubgeek.di

import com.jitendraalekar.githubgeek.data.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://api.github.com/"
@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object{
        @Singleton
        @Provides
        fun createRetrofitInstance() : Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun createGithubService(retrofit: Retrofit) : GithubService  {
            return retrofit.create(GithubService::class.java)
        }

    }

}