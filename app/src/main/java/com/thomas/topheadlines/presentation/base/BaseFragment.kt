package com.thomas.topheadlines.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

internal abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    val binding get() = _binding!!

    abstract fun initView()

    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
