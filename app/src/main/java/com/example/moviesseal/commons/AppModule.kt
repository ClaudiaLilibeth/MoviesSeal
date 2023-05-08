package com.example.moviesseal.commons

import c.Constants
import c.Constants.CONNECTION_TIMEOUT
import com.example.remote.interceptor.InterceptorMoviesDB
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    fun provideClient(
        interceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideClient(interceptor, InterceptorMoviesDB()))
    }

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}