package com.thomas.topheadlines.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thomas.topheadlines.R
import com.thomas.topheadlines.databinding.HeadlineArticlesFragmentLayoutBinding
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.presentation.adapter.HeadlineAdapter
import com.thomas.topheadlines.presentation.base.BaseFragment
import com.thomas.topheadlines.presentation.viewmodel.HeadlineArticlesViewModel
import com.thomas.topheadlines.utils.switchVisibility
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

internal class HeadlineArticlesFragment : BaseFragment<HeadlineArticlesFragmentLayoutBinding>() {

    private val viewModel: HeadlineArticlesViewModel by activityViewModel()
    private val adapter by lazy { HeadlineAdapter(::actionClick) }

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HeadlineArticlesFragmentLayoutBinding {
        return HeadlineArticlesFragmentLayoutBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        (activity as? AppCompatActivity)?.let {
            it.setSupportActionBar(binding.toolbar)
            it.supportActionBar?.title = getString(R.string.bbc_news_provider)
        }
        setupRecycler()
        loadList()
        setupAdapterLoadingListeners()
    }

    private fun setupRecycler() {
        context?.let { safeContext ->
            binding.rvHeadlines.apply {
                layoutManager = LinearLayoutManager(safeContext, RecyclerView.VERTICAL, false)
                adapter = this@HeadlineArticlesFragment.adapter
            }
        }
    }

    private fun loadList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pagingArticle.collectLatest {
                adapter.submitData(it)
            }
        }
        if(adapter.itemCount == 0)
            viewModel.getTopHeadlines()
    }

    private fun actionClick(article: SealedArticleResult.Article) {
        val bundle = Bundle().apply {
            putParcelable(HeadlineArticlesDetailFragment.PARCELABLE_KEY, article)
        }
        findNavController().navigate(R.id.article_detail_action, bundle)
    }

    private fun setupAdapterLoadingListeners() {
        adapter.addLoadStateListener { loadState ->
            binding.run {
                val isListEmpty =
                    (loadState.refresh is LoadState.NotLoading || loadState.refresh is LoadState.Error) && adapter.itemCount == 0
                rvHeadlines.switchVisibility(!isListEmpty)
                loadingSpinner.switchVisibility(
                    loadState.source.refresh is LoadState.Loading
                            && adapter.itemCount == 0
                )
                if (isListEmpty) findNavController().navigate(R.id.article_error_action)
            }
        }
    }
}
