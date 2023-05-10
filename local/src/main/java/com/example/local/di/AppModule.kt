package com.example.local.di

import android.content.Context
import androidx.room.Room
import com.example.local.repository.DataBase
import com.example.local.repository.GenreRepository
import com.example.local.repository.GenresDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//provee dependencias con instrucciones especìficas con las dos anotaciones
@Module
@InstallIn(value = [SingletonComponent::class])
class AppModule {

    @Provides
    @Singleton
    fun provideGenreRepository(dao: GenresDao): GenreRepository =
        GenreRepository(dao)



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
}