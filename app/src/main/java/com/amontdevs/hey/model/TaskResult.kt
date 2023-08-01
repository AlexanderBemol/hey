package com.amontdevs.hey.model

sealed class TaskResult<out T> {
    data class Success<out T>(val data: T) : TaskResult<T>()
    data class Error(val e: Exception) : TaskResult<Nothing>()
}
