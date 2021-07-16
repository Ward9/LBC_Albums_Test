package com.example.lbc_albums.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lbc_albums.data.model.AlbumModel
import com.example.lbc_albums.data.repository.AlbumRepository
import com.example.lbc_albums.utils.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(repository: AlbumRepository) : ViewModel(){
    val albums : LiveData<ResultData<List<AlbumModel>>> = repository.getAlbumLiveData()
}