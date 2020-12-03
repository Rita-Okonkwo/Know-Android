package com.project.know.ui.homeui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.project.know.R
import com.project.know.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var homeBinding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        navigateToQuiz()
        navigateToVideos()
        return homeBinding.root
    }

    fun navigateToQuiz(){
        homeBinding.playquiz.setOnClickListener {
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToWelcomeFragment())
        }
    }

    fun navigateToVideos(){
        homeBinding.video.setOnClickListener {
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToVideoListFragment())
        }
    }

}