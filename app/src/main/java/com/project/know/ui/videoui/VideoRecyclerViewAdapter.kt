package com.project.know.ui.videoui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.google.android.exoplayer2.Player
import com.project.know.R
import com.project.know.databinding.VideoItemBinding
import kotlinx.android.synthetic.main.video_item.view.*

class VideoRecyclerViewAdapter(val videoList:List<VideosItem>): RecyclerView.Adapter<VideoRecyclerViewAdapter.VideoViewHolder>(), VideoStateChange {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
      return VideoViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        with(holder.binding){
            videoDescription.text = videoList[position].description
            url = videoList[position].video_url
            callback = this@VideoRecyclerViewAdapter
            executePendingBindings()
        }
    }

    class VideoViewHolder(val binding: VideoItemBinding): RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent:ViewGroup): VideoViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VideoItemBinding.inflate(layoutInflater, parent, false)
                return VideoViewHolder(binding)
            }
        }
    }

    override fun onVideoDurationRetrieved(duration: Long, player: Player) {

    }

    override fun onVideoBuffering(player: Player) {

    }

    override fun onStartedPlaying(player: Player) {

    }

    override fun onFinishedPlaying(player: Player) {

    }
}