package com.example.lbc_albums.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lbc_albums.data.model.AlbumModel

@Dao
interface IAlbumDAO {
    @Query("SELECT * FROM albums")
    fun getAlbums(): LiveData<List<AlbumModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<AlbumModel>)

    @Query("DELETE FROM albums")
    suspend fun clearAlbums()
}