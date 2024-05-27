package com.thomas.topheadlines.domain.model

sealed class SealedResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : SealedResult<T>()
    data class Error(val error: ErrorResult) : SealedResult<Nothing>()
}
