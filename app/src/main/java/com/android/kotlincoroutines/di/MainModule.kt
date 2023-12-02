package com.android.kotlincoroutines.di

import android.content.Context
import androidx.room.Room
import com.android.kotlincoroutines.data.api.ApiHelper
import com.android.kotlincoroutines.data.api.ApiHelperImpl
import com.android.kotlincoroutines.data.api.ApiService
import com.android.kotlincoroutines.data.room.UserDatabase
import com.android.kotlincoroutines.util.Constants
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
object MainModule {

    private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesApiHelper(apiService: ApiService): ApiHelper = ApiHelperImpl(apiService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        UserDatabase::class.java,
        Constants.ROOM_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesDao(database: UserDatabase) = database.userDao()
}