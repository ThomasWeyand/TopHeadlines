package com.thomas.topheadlines.presentation.adapter

import com.thomas.topheadlines.R
import com.thomas.topheadlines.databinding.DateItemCellLayoutBinding
import com.thomas.topheadlines.domain.model.SealedArticleResult

internal class DateViewHolder(private val binding: DateItemCellLayoutBinding) :
    BaseViewHolder<SealedArticleResult.Published>(binding) {

    override fun bind(data: SealedArticleResult.Published) {
        binding.title.text = binding.root.context.getString(R.string.news_from_date, data.publishedAt)
    }
}
