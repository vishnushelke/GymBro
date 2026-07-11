package com.pamu.gymbro.data.di

import android.content.Context
import androidx.room.Room
import com.pamu.gymbro.data.local.GymBroDatabase
import com.pamu.gymbro.data.local.dao.DietDao
import com.pamu.gymbro.data.local.dao.ExerciseDao
import com.pamu.gymbro.data.local.dao.ProgressDao
import com.pamu.gymbro.data.local.dao.UserDao
import com.pamu.gymbro.data.local.dao.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GymBroDatabase {
        return Room.databaseBuilder(
            context,
            GymBroDatabase::class.java,
            "gymbro_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: GymBroDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideExerciseDao(db: GymBroDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    @Singleton
    fun provideWorkoutDao(db: GymBroDatabase): WorkoutDao = db.workoutDao()

    @Provides
    @Singleton
    fun provideDietDao(db: GymBroDatabase): DietDao = db.dietDao()

    @Provides
    @Singleton
    fun provideProgressDao(db: GymBroDatabase): ProgressDao = db.progressDao()
}
