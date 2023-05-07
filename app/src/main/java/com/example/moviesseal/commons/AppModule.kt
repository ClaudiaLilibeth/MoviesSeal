package com.example.moviesseal.commons

import c.Constants
import com.example.remote.auth.data.AuthRepository
import com.example.remote.auth.data.AuthRepositoryImpl
import com.example.remote.interceptor.InterceptorMoviesDB
import com.example.remote.movies.api.MoviesDBApi
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(value = [SingletonComponent::class])
class AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): com.example.remote.auth.data.AuthRepository =
        com.example.remote.auth.data.AuthRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideMoviesDBApi(builder: Retrofit.Builder): com.example.remote.movies.api.MoviesDBApi {
        return builder
            .build()
            .create(com.example.remote.movies.api.MoviesDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(com.example.remote.interceptor.InterceptorMoviesDB()).build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
    }
}