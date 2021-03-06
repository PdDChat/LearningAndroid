package com.learningandroid.ui.roulette

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.learningandroid.databinding.FragmentRouletteBinding
import com.learningandroid.common.ResponseStatus
import com.learningandroid.ui.roulette.dialog.RouletteInfoSettingDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RouletteFragment : Fragment(), RouletteInfoSettingDialogFragment.OnRouletteInfoSettingClickListener {

    private var _binding: FragmentRouletteBinding? = null
    private val binding get() = _binding

    private val viewModel: RouletteViewModel by viewModels()

    private lateinit var adapter: RouletteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRouletteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()

        setUpObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpView() {
        adapter = RouletteAdapter()
        binding?.rouletteRecyclerview?.adapter = adapter

        binding?.addButton?.setOnClickListener {
            findNavController().navigate(RouletteFragmentDirections.actionToRegisterDialogFragment(true))
        }

        binding?.deleteButton?.setOnClickListener {
            findNavController().navigate(RouletteFragmentDirections.actionToRegisterDialogFragment(false))
        }

        binding?.zeroMatchButton?.setOnClickListener {
            findNavController().navigate(RouletteFragmentDirections.actionToRegisterDialogFragment(true))
        }

        binding?.startRouletteButton?.setOnClickListener {
            val result = viewModel.startRoulette()
            findNavController().navigate(RouletteFragmentDirections.actionToRouletteSelectedDialogFragment(result))
        }
    }

    private fun setUpObserver() {
        viewModel.getRouletteInfo()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.rouletteInfoStatus.collect {
                    when (it) {
                        is ResponseStatus.Success -> {
                            binding?.apply {
                                successLayout.visibility = View.VISIBLE
                                zeroMatchLayout.visibility = View.GONE
                            }
                            adapter.submitList(it.value)
                        }
                        is ResponseStatus.ZeroMatch -> {
                            binding?.apply {
                                successLayout.visibility = View.GONE
                                zeroMatchLayout.visibility = View.VISIBLE
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun onPositiveClick() {
        // FIXME DB??????????????????????????????????????????????????????????????????????????????
        //  DB???????????????????????????????????????
        Handler(Looper.getMainLooper()).postDelayed( {
            viewModel.getRouletteInfo()
        }, 1000)
    }
}