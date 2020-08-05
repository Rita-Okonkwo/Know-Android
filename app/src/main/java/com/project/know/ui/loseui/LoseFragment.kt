package com.project.know.ui.loseui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.project.know.R
import com.project.know.databinding.FragmentLoseBinding

/**
 * @author: Rita Okonkwo.
 * A simple [Fragment] subclass.
 * The LoseFrgament for Know app.
 */
class LoseFragment : Fragment() {

    private lateinit var loseBinding : FragmentLoseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lose, container, false)
        return loseBinding.root
    }

}