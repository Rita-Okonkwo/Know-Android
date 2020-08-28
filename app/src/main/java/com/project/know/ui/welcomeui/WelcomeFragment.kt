package com.project.know.ui.welcomeui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.project.know.R
import com.project.know.databinding.FragmentWelcomeBinding

/**
 * @author: Rita Okonkwo
 * A simple [Fragment] subclass.
 * This is the welcome fragment of the Know App.
 */
class WelcomeFragment : Fragment() {

    private lateinit var welcomeBinding: FragmentWelcomeBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        welcomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        welcomeBinding.startQuiz.setOnClickListener {
            it.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToQuestionFragment())
        }
        return welcomeBinding.root
    }

}