package com.example.moviesseal.login.data

//#firebase 1 object events
sealed class Resource<T>(
    val currentUser: T? = null,
    val message: String? = null,
    val loading: Boolean? = null
) {
    class Success<T>(data: T? = null) : Resource<T>(currentUser = data)
    class Error<T>(data: T? = null, message: String? = null) : Resource<T>(data, message = message)
    class Loading<T>(loading: Boolean? = null) : Resource<T>(loading = loading)
}