package com.pamu.gymbro.data.di

import com.pamu.gymbro.data.repository.DailyStatsRepositoryImpl
import com.pamu.gymbro.data.repository.DietRepositoryImpl
import com.pamu.gymbro.data.repository.ExerciseRepositoryImpl
import com.pamu.gymbro.data.repository.ProgressRepositoryImpl
import com.pamu.gymbro.data.repository.UserRepositoryImpl
import com.pamu.gymbro.data.repository.WorkoutRepositoryImpl
import com.pamu.gymbro.data.repository.WorkoutSessionRepositoryImpl
import com.pamu.gymbro.domain.repository.DailyStatsRepository
import com.pamu.gymbro.domain.repository.DietRepository
import com.pamu.gymbro.domain.repository.ExerciseRepository
import com.pamu.gymbro.domain.repository.ProgressRepository
import com.pamu.gymbro.domain.repository.UserRepository
import com.pamu.gymbro.domain.repository.WorkoutRepository
import com.pamu.gymbro.domain.repository.WorkoutSessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExerciseRepository(
        exerciseRepositoryImpl: ExerciseRepositoryImpl
    ): ExerciseRepository

    @Binds
    @Singleton
    abstract fun bindWorkoutRepository(
        workoutRepositoryImpl: WorkoutRepositoryImpl
    ): WorkoutRepository

    @Binds
    @Singleton
    abstract fun bindDietRepository(
        dietRepositoryImpl: DietRepositoryImpl
    ): DietRepository

    @Binds
    @Singleton
    abstract fun bindProgressRepository(
        progressRepositoryImpl: ProgressRepositoryImpl
    ): ProgressRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindWorkoutSessionRepository(
        workoutSessionRepositoryImpl: WorkoutSessionRepositoryImpl
    ): WorkoutSessionRepository

    @Binds
    @Singleton
    abstract fun bindDailyStatsRepository(
        dailyStatsRepositoryImpl: DailyStatsRepositoryImpl
    ): DailyStatsRepository
}
