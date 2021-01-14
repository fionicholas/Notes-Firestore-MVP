package com.fionicholas.samplefirestoremvp.utils

sealed class BaseResult<T> {
    class Loading<T> : BaseResult<T>()
    data class Success<T>(val data: T) : BaseResult<T>()
    data class Failed<T>(val message: String) : BaseResult<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(message: String) = Failed<T>(message)
    }
}