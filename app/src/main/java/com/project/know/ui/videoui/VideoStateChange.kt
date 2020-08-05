package com.project.know.ui.videoui

import com.google.android.exoplayer2.Player

interface VideoStateChange {
    /**
     * Callback to when the [PlayerView] has fetched the duration of video
     **/
    fun onVideoDurationRetrieved(duration: Long, player: Player)

    fun onVideoBuffering(player: Player)

    fun onStartedPlaying(player: Player)

    fun onFinishedPlaying(player: Player)
}