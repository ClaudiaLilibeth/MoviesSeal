package com.example.remote.movies.repository

class NetworkState(val status: Status, val msg: String) {
    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Loading")
            ERROR = NetworkState(Status.FAILED, "Error")
        }
    }
}

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}