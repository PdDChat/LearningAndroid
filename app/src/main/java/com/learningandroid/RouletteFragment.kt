package com.learningandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.learningandroid.databinding.FragmentRouletteBinding
class RouletteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentRouletteBinding.inflate(inflater, container, false)
        return binding.root
    }

}