package com.example.moviesseal.commons.di

import android.content.Context
import androidx.room.Room
import c.Constants
import com.example.moviesseal.commons.interceptor.InterceptorMoviesDB
import com.example.moviesseal.genres.repository.DataBase
import com.example.moviesseal.genres.repository.GenreRepository
import com.example.moviesseal.genres.repository.GenresDao
import com.example.moviesseal.login.data.AuthRepository
import com.example.moviesseal.login.data.AuthRepositoryImpl
import com.example.moviesseal.movies.api.MoviesDBApi
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideGenreRepository(dao: GenresDao): GenreRepository =
        GenreRepository(dao)

    @Provides
    @Singleton
    fun provideMoviesDBApi(builder: Retrofit.Builder): MoviesDBApi {
        return builder
            .build()
            .create(MoviesDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGenresDao(db: DataBase): GenresDao {
        return db.genresDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): DataBase {
        return Room.databaseBuilder(
            appContext,
            DataBase::class.java,
            "DataBase"
        ).build()
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