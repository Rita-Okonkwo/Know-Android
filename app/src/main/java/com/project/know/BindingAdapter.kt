package com.project.know

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.project.know.ui.questionui.QuestionApiStatus
import com.project.know.ui.videoui.VideoApiStatus
import com.project.know.ui.videoui.VideoStateChange
import com.project.know.ui.videoui.VideoViewModel

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, apiStatus: QuestionApiStatus) {
    when (apiStatus) {
        QuestionApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        QuestionApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        QuestionApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("videoApiStatus")
fun bindStatus(statusImageView: ImageView, apiStatus: VideoApiStatus) {
    when (apiStatus) {
        VideoApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        VideoApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        VideoApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("textVisible")
fun bindStatusText(answerTextView: TextView, apiStatus: QuestionApiStatus) {
    when (apiStatus) {
        QuestionApiStatus.LOADING -> {
            answerTextView.visibility = View.GONE
        }
        QuestionApiStatus.ERROR -> {
            answerTextView.visibility = View.GONE
        }
        QuestionApiStatus.DONE -> {
            answerTextView.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("video_url", "on_state_change")
fun PlayerView.loadVideo(url: String?, callback: VideoStateChange) {
    if (url == null) {
        return
    }
    this.player = VideoViewModel().preparePlayer(url, context)
    this.useController = true
    VideoViewModel().set(this.player)

}