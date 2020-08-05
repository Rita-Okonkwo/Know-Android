package com.project.know.ui.videoui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.know.R
import com.project.know.databinding.FragmentVideoListBinding
import com.project.know.services.KnowApi
import com.project.know.services.KnowApiService


/**
 * A simple [Fragment] subclass.
 * Use the [VideoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideoListFragment : Fragment() {
    //initialize the view model
    val videoViewModel : VideoViewModel by viewModels()

    private lateinit var videoBinding : FragmentVideoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videoBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false)
        val recyclerview: RecyclerView = videoBinding.videoRecyclerView
        videoViewModel.videos.observe(viewLifecycleOwner, Observer { videos ->
            recyclerview.adapter = VideoRecyclerViewAdapter(videos)
        })
        recyclerview.layoutManager = LinearLayoutManager(context)
        return videoBinding.root
    }

}