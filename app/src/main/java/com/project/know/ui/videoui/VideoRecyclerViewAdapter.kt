package com.project.know.ui.videoui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.Player
import com.project.know.databinding.VideoItemBinding

class VideoRecyclerViewAdapter(val videoList:List<VideosItem>): RecyclerView.Adapter<VideoRecyclerViewAdapter.ViewHolder>(), VideoStateChange {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoRecyclerViewAdapter.ViewHolder {
      return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoRecyclerViewAdapter.ViewHolder, position: Int) {
        with(holder.binding){
            videoDescription.text = videoList[position].description
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"
            callback = this@VideoRecyclerViewAdapter
            executePendingBindings()
        }
    }

    class ViewHolder(val binding: VideoItemBinding): RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent:ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VideoItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onVideoDurationRetrieved(duration: Long, player: Player) {
        TODO("Not yet implemented")
    }

    override fun onVideoBuffering(player: Player) {
        TODO("Not yet implemented")
    }

    override fun onStartedPlaying(player: Player) {
        TODO("Not yet implemented")
    }

    override fun onFinishedPlaying(player: Player) {
        TODO("Not yet implemented")
    }
}