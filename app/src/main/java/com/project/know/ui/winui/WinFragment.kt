package com.project.know.ui.winui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.project.know.R
import com.project.know.databinding.FragmentWinBinding

/**
 * @author: Rita Okonkwo.
 * A simple [Fragment] subclass.
 * The WinFragment for Know app.
 */
class WinFragment : Fragment() {

    private lateinit var winBinding : FragmentWinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        winBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_win, container, false)
        return winBinding.root
    }
}