package com.thomas.topheadlines.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.usecase.ITopHeadlinesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class HeadlineArticlesViewModel(private val topHeadlinesUseCase: ITopHeadlinesUseCase) :
    ViewModel() {
    init {
        getTopHeadlines()
    }

    private val _pagingArticle =
        MutableStateFlow<PagingData<SealedArticleResult>>(PagingData.empty())
    val pagingArticle: StateFlow<PagingData<SealedArticleResult>>
        get() = _pagingArticle.asStateFlow()

    private fun getTopHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            topHeadlinesUseCase.paginateData()
                .cachedIn(viewModelScope)
                .collectLatest {
                    _pagingArticle.value = it
                }
        }
    }
}
