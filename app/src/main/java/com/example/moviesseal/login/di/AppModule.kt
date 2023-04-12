package com.example.moviesseal.login.di

import com.example.moviesseal.login.data.AuthRepository
import com.example.moviesseal.login.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


//provee dependencias con instrucciones especìficas con las dos anotaciones

@Module
@InstallIn(value = [SingletonComponent::class])
class AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth) : AuthRepository = AuthRepositoryImpl(firebaseAuth)

}