package com.project.know.ui.videoui

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.project.know.loadVideo
import com.project.know.repository.KnowRepository
import com.project.know.ui.questionui.QuestionApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

enum class VideoApiStatus { LOADING, ERROR, DONE }
class VideoViewModel : ViewModel() {
    val repository: KnowRepository = KnowRepository()
    var player : SimpleExoPlayer? = null

    private val _apiStatus = MutableLiveData<VideoApiStatus>()
    val apiStatus: LiveData<VideoApiStatus>
        get() = _apiStatus

    init {
        _apiStatus.value = VideoApiStatus.LOADING
    }

    var videos: LiveData<List<VideosItem>>? = liveData(Dispatchers.IO) {
        loading()
        try {
            val retrivedVideos = repository.getVideos()
            emit(retrivedVideos)
            done()
        } catch (e : Exception) {
            networkError()
        }
    }

    private suspend fun loading() {
        withContext(Dispatchers.Main) {
            _apiStatus.value = VideoApiStatus.LOADING
        }
    }

    private suspend fun networkError() {
        withContext(Dispatchers.Main) {
            _apiStatus.value = VideoApiStatus.ERROR
        }
    }

    private suspend fun done() {
        withContext(Dispatchers.Main) {
            _apiStatus.value = VideoApiStatus.DONE
        }
    }

    fun preparePlayer(play: Player)   {
        player = play as SimpleExoPlayer
    }

    override fun onCleared() {
        Log.d("tag", "on clear")
        super.onCleared()
        player?.stop()
        player?.release()
    }
}