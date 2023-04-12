package com.example.moviesseal.login.di

import com.example.moviesseal.login.data.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn


//provee dependencias con instrucciones especìficas con las dos anotaciones
@InstallIn
@Module
class AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepository) : AuthRepository = impl

}