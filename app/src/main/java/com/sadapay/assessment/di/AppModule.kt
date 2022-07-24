package com.sadapay.assessment.di

import android.content.Context
import android.content.SharedPreferences
import com.sadapay.assessment.network.RetrofitService
import com.sadapay.assessment.repository.GitHubRepoRepository
import com.sadapay.assessment.repository.GitHubRepoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val KEY_PREF_NAME = "com.sadapay.assessment.di"
    const val KEY_PREF_THEME = "key_pref_theme"

    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitService =
        Retrofit.Builder()
            .baseUrl(RetrofitService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)

    @Singleton
    @Provides
    fun provideGitHubRepository(
        api: RetrofitService
    ) = GitHubRepoRepositoryImpl(api) as GitHubRepoRepository

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(KEY_PREF_NAME, Context.MODE_PRIVATE)
    }

}