package com.thomas.topheadlines.presentation

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.thomas.topheadlines.databinding.HeadlineArticlesDetailFragmentLayoutBinding
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.presentation.base.BaseFragment
import com.thomas.topheadlines.utils.getStringUntilDotsInclusive
import com.thomas.topheadlines.utils.loadImageFromUrl
import com.thomas.topheadlines.utils.show

internal class HeadlineArticlesDetailFragment: BaseFragment<HeadlineArticlesDetailFragmentLayoutBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HeadlineArticlesDetailFragmentLayoutBinding {
        return HeadlineArticlesDetailFragmentLayoutBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        (activity as? AppCompatActivity)?.let {
            it.setSupportActionBar(binding.toolbar)
            it.supportActionBar?.title = ""
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        displayViews()
    }

    private fun displayViews() {
        getArgument()?.let { article ->
            binding.run {
                detailImage.loadImageFromUrl(article.urlToImage)
                title.text = article.title
                description.text = article.description
                content.text = article.content.getStringUntilDotsInclusive()
                if(article.url.isNotEmpty()) {
                    fullArticleBtn.apply {
                        show()
                        setOnClickListener {
                            openBrowserURL(article.url)
                        }
                    }
                }
            }
        }
    }

    private fun openBrowserURL(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.resolveActivity(requireActivity().packageManager)?.let { _ ->
                startActivity(intent)
            }
        } catch (ex: Exception) {
            //no implementation
        }
    }

    private fun getArgument() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments?.getParcelable(PARCELABLE_KEY, SealedArticleResult.Article::class.java)
    } else {
        arguments?.getParcelable(PARCELABLE_KEY)
    }

    companion object {
        const val PARCELABLE_KEY = "ArticleParcelable"
    }

}