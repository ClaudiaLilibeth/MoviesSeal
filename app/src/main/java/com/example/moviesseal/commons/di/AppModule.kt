package com.example.moviesseal.commons.di

import c.Constants
import com.example.moviesseal.commons.interceptor.InterceptorMoviesDB
import com.example.moviesseal.login.data.AuthRepository
import com.example.moviesseal.login.data.AuthRepositoryImpl
import com.example.moviesseal.movies.api.MoviesDBApi
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//provee dependencias con instrucciones especìficas con las dos anotaciones
@Module
@InstallIn(value = [SingletonComponent::class])
class AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideMoviesDBApi(builder: Retrofit.Builder): MoviesDBApi {
        return builder
            .build()
            .create(MoviesDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(InterceptorMoviesDB()).build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
    }

}