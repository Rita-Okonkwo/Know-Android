package com.project.know.ui.videoui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.project.know.repository.KnowRepository
import kotlinx.coroutines.Dispatchers

class VideoViewModel : ViewModel() {
    val repository: KnowRepository = KnowRepository()

    val videos: LiveData<List<VideosItem>> = liveData(Dispatchers.IO) {
        val retrivedVideos = repository.getVideos()
        emit(retrivedVideos)
    }
}