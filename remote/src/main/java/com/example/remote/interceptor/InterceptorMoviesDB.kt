package com.example.remote.interceptor

import c.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class InterceptorMoviesDB : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url().newBuilder()
            .addQueryParameter("api_key", Constants.API_KEY)
            .addQueryParameter("language", "es-ES")
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
