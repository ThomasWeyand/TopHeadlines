package com.thomas.topheadlines.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.thomas.topheadlines.domain.model.SealedArticleResult

abstract class BaseViewHolder<T : SealedArticleResult>(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(data: T)
}