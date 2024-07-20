package com.thomas.topheadlines.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.presentation.paging.IPagingFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class HeadlineArticlesViewModel(private val pagingFactory: IPagingFactory) :
    ViewModel() {

    private val _pagingArticle =
        MutableStateFlow<PagingData<SealedArticleResult>>(PagingData.empty())
    val pagingArticle: StateFlow<PagingData<SealedArticleResult>>
        get() = _pagingArticle.asStateFlow()

    fun getTopHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            pagingFactory.paginateData()
                .cachedIn(viewModelScope)
                .collectLatest {
                    _pagingArticle.value = it
                }
        }
    }
}
