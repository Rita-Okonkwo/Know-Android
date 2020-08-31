package com.project.know.ui.videoui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.Player
import com.project.know.R
import com.project.know.databinding.FragmentVideoListBinding


/**
 * A simple [Fragment] subclass.
 */
class VideoListFragment : Fragment() {
    //initialize the view model
    val videoViewModel : VideoViewModel by viewModels()

    private lateinit var videoBinding : FragmentVideoListBinding
    private var player: Player? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videoBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false)
        videoBinding.lifecycleOwner = this
        videoBinding.viewModel = videoViewModel
        val recyclerview: RecyclerView = videoBinding.videoRecyclerView
        videoViewModel.videos?.observe(viewLifecycleOwner, Observer { videos ->
            if (videos != null) {
                recyclerview.adapter = VideoRecyclerViewAdapter(videos)
            }
        })
        videoViewModel.player.observe(viewLifecycleOwner, Observer {
            play ->
            if (play != null) {
                player = play
            }
        })
        recyclerview.layoutManager = LinearLayoutManager(context)
        return videoBinding.root
    }

    override fun onStop() {
        super.onStop()
        Log.d("tag", "onstop")
        player?.stop()
        player?.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("tag", "ondestroy")
        player?.stop()
        player?.release()
    }
}