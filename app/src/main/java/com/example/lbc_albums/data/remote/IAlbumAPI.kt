package com.example.lbc_albums.data.remote

import com.example.lbc_albums.data.model.AlbumModel
import retrofit2.Response
import retrofit2.http.GET

interface IAlbumAPI {
    @GET("img/shared/technical-test.json")
    suspend fun getAlbums() : Response<List<AlbumModel>>
}