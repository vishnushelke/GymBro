package com.pamu.gymbro.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pamu.gymbro.data.local.converter.DateConverter
import com.pamu.gymbro.data.local.dao.*
import com.pamu.gymbro.data.local.entity.*

@Database(
    entities = [
        UserProfileEntity::class,
        ExerciseCategoryEntity::class,
        ExerciseEntity::class,
        WorkoutPlanEntity::class,
        WorkoutDayEntity::class,
        WorkoutExerciseEntity::class,
        DietPlanEntity::class,
        MealEntity::class,
        ProgressEntryEntity::class,
        WorkoutSessionEntity::class,
        DailyStatsEntity::class
    ],
    version = 24,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class GymBroDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun dietDao(): DietDao
    abstract fun progressDao(): ProgressDao
    abstract fun workoutSessionDao(): WorkoutSessionDao
    abstract fun dailyStatsDao(): DailyStatsDao
}
