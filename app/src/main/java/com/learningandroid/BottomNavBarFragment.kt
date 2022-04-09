package com.learningandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.learningandroid.databinding.FragmentBottomNavBarBinding

class BottomNavBarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentBottomNavBarBinding.inflate(inflater, container, false)
        val fragment = binding.bottomNavBar
        fragment.setOnItemSelectedListener { item ->
            findNavController().navigate(item.itemId)
            true
        }

        return binding.root
    }

}