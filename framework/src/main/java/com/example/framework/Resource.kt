package com.example.framework

data class Resource<out T>(
    val networkState: NetworkState,
    val code: Int,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(NetworkState.SUCCESS, -1, data, null)
        }

        fun <T> error(code: Int = -1, msg: String, data: T?): Resource<T> {
            return Resource(NetworkState.ERROR, code, data, msg)
        }
    }
}
