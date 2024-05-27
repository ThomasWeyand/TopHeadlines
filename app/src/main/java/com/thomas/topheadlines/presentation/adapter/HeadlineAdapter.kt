package com.thomas.topheadlines.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.thomas.topheadlines.databinding.ArticleItemCellLayoutBinding
import com.thomas.topheadlines.databinding.DateItemCellLayoutBinding
import com.thomas.topheadlines.domain.model.SealedArticleResult

internal class HeadlineAdapter(private val actionClick: (SealedArticleResult.Article) -> Unit) :
    PagingDataAdapter<SealedArticleResult, BaseViewHolder<SealedArticleResult>>(headlineComparator) {

    override fun onBindViewHolder(holder: BaseViewHolder<SealedArticleResult>, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SealedArticleResult.Article -> ARTICLE_TYPE
            else -> DATE_TYPE
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SealedArticleResult> {
        return when (viewType) {
            ARTICLE_TYPE -> {
                ArticleViewHolder(
                    binding = ArticleItemCellLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    actionClick
                )
            }

            else -> {
                DateViewHolder(
                    binding = DateItemCellLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        } as BaseViewHolder<SealedArticleResult>
    }

    companion object {
        private const val ARTICLE_TYPE = 0
        private const val DATE_TYPE = 1
        private val headlineComparator = object : DiffUtil.ItemCallback<SealedArticleResult>() {
            override fun areItemsTheSame(
                oldItem: SealedArticleResult,
                newItem: SealedArticleResult
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SealedArticleResult,
                newItem: SealedArticleResult
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
