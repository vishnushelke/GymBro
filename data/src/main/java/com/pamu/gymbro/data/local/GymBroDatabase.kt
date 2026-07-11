package com.pamu.gymbro.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pamu.gymbro.data.local.converter.DateConverter
import com.pamu.gymbro.data.local.dao.DietDao
import com.pamu.gymbro.data.local.dao.ExerciseDao
import com.pamu.gymbro.data.local.dao.ProgressDao
import com.pamu.gymbro.data.local.dao.UserDao
import com.pamu.gymbro.data.local.dao.WorkoutDao
import com.pamu.gymbro.data.local.entity.DietPlanEntity
import com.pamu.gymbro.data.local.entity.ExerciseCategoryEntity
import com.pamu.gymbro.data.local.entity.ExerciseEntity
import com.pamu.gymbro.data.local.entity.MealEntity
import com.pamu.gymbro.data.local.entity.ProgressEntryEntity
import com.pamu.gymbro.data.local.entity.UserProfileEntity
import com.pamu.gymbro.data.local.entity.WorkoutDayEntity
import com.pamu.gymbro.data.local.entity.WorkoutExerciseEntity
import com.pamu.gymbro.data.local.entity.WorkoutPlanEntity

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
        ProgressEntryEntity::class
    ],
    version = 6,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class GymBroDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun dietDao(): DietDao
    abstract fun progressDao(): ProgressDao
}
