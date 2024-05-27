package com.thomas.topheadlines.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.thomas.topheadlines.R
import com.thomas.topheadlines.databinding.HeadlineErrorFragmentLayoutBinding
import com.thomas.topheadlines.presentation.base.BaseFragment
import com.thomas.topheadlines.utils.loadImageFromUrl

internal class HeadlineErrorFragment : BaseFragment<HeadlineErrorFragmentLayoutBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HeadlineErrorFragmentLayoutBinding {
        return HeadlineErrorFragmentLayoutBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        binding.run {
            errorImage.loadImageFromUrl(IMAGE_ERROR_PLACE_HOLDER)
            retryButton.setOnClickListener {
                findNavController().navigate(R.id.article_retry_action)
            }
        }
    }

    companion object {
        private const val IMAGE_ERROR_PLACE_HOLDER = "https://i.imgur.com/yW2W9SC.png"
    }
}
