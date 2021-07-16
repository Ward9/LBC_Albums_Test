package com.example.lbc_albums.data.repository

import com.example.lbc_albums.data.local.IAlbumDAO
import com.example.lbc_albums.data.remote.AlbumDataSource
import com.example.lbc_albums.utils.resultLiveData
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val localDataSource: IAlbumDAO,
    private val remoteDataSource: AlbumDataSource
) {
    fun getAlbumLiveData() = resultLiveData(
        /** Showing old data from DB while fetching fresh one from remote */
        databaseQuery = { localDataSource.getAlbums() },
        /** Fetching fresh data from remote
         * In no internet scenario this will fail,
         * and data from local DB will be continued to show to user */
        networkCall = { remoteDataSource.getAlbumFromNetwork() },
        /** When API calls succeed this fresh data is saved to DB
         * Upon data update in DB, its listeners are notified automatically as it wrapped in live data
         * See return type of insertAll() in NewsDAO */
        saveCallResult = { localDataSource.insertAll(it.allAlbum) })
}