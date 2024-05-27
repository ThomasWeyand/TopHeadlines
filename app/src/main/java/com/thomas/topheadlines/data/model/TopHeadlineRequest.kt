package com.thomas.topheadlines.data.model

import com.thomas.topheadlines.utils.Constant

data class TopHeadlineRequest(
    val page: Int,
    val pageSize: Int = Constant.PAGE_SIZE,
)
