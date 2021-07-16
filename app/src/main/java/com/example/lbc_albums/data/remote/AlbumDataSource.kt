package com.example.lbc_albums.data.remote

import android.util.Log
import com.example.lbc_albums.data.model.ListAlbumModel
import com.example.lbc_albums.utils.ResultData
import javax.inject.Inject

/**
 * Encapsulate the Retrofit response from IArtistAPI using getResult suspend fun
 */
class AlbumDataSource @Inject constructor(private val albumApi: IAlbumAPI) {
    suspend fun getAlbumFromNetwork(): ResultData<ListAlbumModel> {
        try {
            val response = albumApi.getAlbums()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return ResultData.success(ListAlbumModel(body))
                }
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ResultData<T> {
        Log.e("remoteDataSource", message)
        return ResultData.error("Network call has failed for a following reason: $message")
    }
}