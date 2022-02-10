package com.snilloc.bonialinterview.util

/**
 * This class helps to handle the different states in the application.
 * Success means the brochures were successfully fetched.
 * In Loading state, the progress bar is visible to the user when fetching the brochures.
 * Error means there was a problem in fetching the brochures and the error is displayed to the user as feedback
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}