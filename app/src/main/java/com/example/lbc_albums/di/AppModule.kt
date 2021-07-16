package com.example.lbc_albums.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.example.lbc_albums.data.local.AppDatabase
import com.example.lbc_albums.data.local.IAlbumDAO
import com.example.lbc_albums.data.model.AlbumModel
import com.example.lbc_albums.data.remote.AlbumDataSource
import com.example.lbc_albums.data.remote.IAlbumAPI
import com.example.lbc_albums.data.repository.AlbumRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
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

    private const val BASE_URL: String = "https://static.leboncoin.fr/"

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAlbumService(retrofit: Retrofit): IAlbumAPI = retrofit.create(IAlbumAPI::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideAlbumDao(db: AppDatabase) = db.albumDao()

    @Singleton
    @Provides
    fun provideDataSource(albumAPI: IAlbumAPI) = AlbumDataSource(albumAPI)

    @Singleton
    @Provides
    fun provideRepository(dao: IAlbumDAO, datasource: AlbumDataSource) = AlbumRepository(dao, datasource)
}