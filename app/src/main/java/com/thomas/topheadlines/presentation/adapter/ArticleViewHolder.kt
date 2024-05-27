package com.thomas.topheadlines.presentation.adapter

import com.thomas.topheadlines.databinding.ArticleItemCellLayoutBinding
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.utils.loadImageFromUrl

internal class ArticleViewHolder(
    private val binding: ArticleItemCellLayoutBinding,
    private val actionClick: (SealedArticleResult.Article) -> Unit
) : BaseViewHolder<SealedArticleResult.Article>(binding) {

    override fun bind(data: SealedArticleResult.Article) {
        binding.run {
            root.setOnClickListener {
                actionClick.invoke(data)
            }
            headerImage.loadImageFromUrl(data.urlToImage)
            title.text = data.title
        }
    }

}