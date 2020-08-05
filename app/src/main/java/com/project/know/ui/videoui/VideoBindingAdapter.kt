package com.project.know.ui.videoui

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

@BindingAdapter("video_url", "on_state_change")
fun PlayerView.loadVideo(url: String?, callback: VideoStateChange) {
    if (url == null) return
    val player = ExoPlayerFactory.newSimpleInstance(
        DefaultRenderersFactory(context), DefaultTrackSelector(),
        DefaultLoadControl()
    )

    player.playWhenReady = true
    player.repeatMode = Player.REPEAT_MODE_ALL
    // When changing track, retain the latest frame instead of showing a black screen
    setKeepContentOnPlayerReset(true)
    // We'll show the controller
    this.useController = true
    // Provide url to load the video from here
    val mediaSource = ExtractorMediaSource.Factory(
        DefaultHttpDataSourceFactory("Demo")
    ).createMediaSource(Uri.parse(url))

    player.prepare(mediaSource)

    this.player = player

}