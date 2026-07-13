package com.pamu.gymbro.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.pamu.gymbro.data.local.dao.DietDao;
import com.pamu.gymbro.data.local.dao.DietDao_Impl;
import com.pamu.gymbro.data.local.dao.ExerciseDao;
import com.pamu.gymbro.data.local.dao.ExerciseDao_Impl;
import com.pamu.gymbro.data.local.dao.ProgressDao;
import com.pamu.gymbro.data.local.dao.ProgressDao_Impl;
import com.pamu.gymbro.data.local.dao.UserDao;
import com.pamu.gymbro.data.local.dao.UserDao_Impl;
import com.pamu.gymbro.data.local.dao.WorkoutDao;
import com.pamu.gymbro.data.local.dao.WorkoutDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class GymBroDatabase_Impl extends GymBroDatabase {
  private volatile UserDao _userDao;

  private volatile ExerciseDao _exerciseDao;

  private volatile WorkoutDao _workoutDao;

  private volatile DietDao _dietDao;

  private volatile ProgressDao _progressDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(19) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_profile` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `age` INTEGER NOT NULL, `gender` TEXT NOT NULL, `heightCm` REAL NOT NULL, `weightKg` REAL NOT NULL, `fitnessGoal` TEXT NOT NULL, `experienceLevel` TEXT NOT NULL, `workoutDurationMinutes` INTEGER NOT NULL, `workoutDaysPerWeek` INTEGER NOT NULL, `injuries` TEXT NOT NULL, `availableEquipment` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `exercise_categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `exercises` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `categoryId` INTEGER NOT NULL, `difficulty` TEXT NOT NULL, `equipment` TEXT NOT NULL, `primaryMuscle` TEXT NOT NULL, `secondaryMuscles` TEXT NOT NULL, `exerciseType` TEXT NOT NULL, `movementPattern` TEXT NOT NULL, `caloriesBurnedEstimate` INTEGER NOT NULL, `description` TEXT NOT NULL, `benefits` TEXT NOT NULL, `commonMistakes` TEXT NOT NULL, `safetyWarnings` TEXT NOT NULL, `beginnerVariation` TEXT NOT NULL, `intermediateVariation` TEXT NOT NULL, `advancedVariation` TEXT NOT NULL, `instructions` TEXT NOT NULL, `thumbnailUrl` TEXT NOT NULL, `videoFrontUrl` TEXT NOT NULL, `videoSideUrl` TEXT NOT NULL, `videoDuration` INTEGER NOT NULL, `videoFps` INTEGER NOT NULL, `videoResolution` TEXT NOT NULL, `isFavorite` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`categoryId`) REFERENCES `exercise_categories`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_exercises_categoryId` ON `exercises` (`categoryId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `workout_plans` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `level` TEXT NOT NULL, `goal` TEXT NOT NULL, `durationWeeks` INTEGER NOT NULL, `description` TEXT NOT NULL, `isFavorite` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `workout_days` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `workoutPlanId` INTEGER NOT NULL, `dayNumber` INTEGER NOT NULL, `title` TEXT NOT NULL, FOREIGN KEY(`workoutPlanId`) REFERENCES `workout_plans`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_days_workoutPlanId` ON `workout_days` (`workoutPlanId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `workout_exercises` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `workoutDayId` INTEGER NOT NULL, `exerciseId` INTEGER NOT NULL, `sets` INTEGER NOT NULL, `reps` TEXT NOT NULL, `restSeconds` INTEGER NOT NULL, FOREIGN KEY(`workoutDayId`) REFERENCES `workout_days`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`exerciseId`) REFERENCES `exercises`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_exercises_workoutDayId` ON `workout_exercises` (`workoutDayId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_exercises_exerciseId` ON `workout_exercises` (`exerciseId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `diet_plans` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `goal` TEXT NOT NULL, `calories` INTEGER NOT NULL, `protein` INTEGER NOT NULL, `carbs` INTEGER NOT NULL, `fats` INTEGER NOT NULL, `isFavorite` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `meals` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dietPlanId` INTEGER NOT NULL, `mealType` TEXT NOT NULL, `name` TEXT NOT NULL, `calories` INTEGER NOT NULL, `protein` INTEGER NOT NULL, `carbs` INTEGER NOT NULL, `fats` INTEGER NOT NULL, FOREIGN KEY(`dietPlanId`) REFERENCES `diet_plans`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_meals_dietPlanId` ON `meals` (`dietPlanId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `progress_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` INTEGER NOT NULL, `weight` REAL NOT NULL, `chest` REAL NOT NULL, `waist` REAL NOT NULL, `hips` REAL NOT NULL, `arms` REAL NOT NULL, `thighs` REAL NOT NULL, `bodyFat` REAL NOT NULL, `notes` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'aa0bb0660030506e516c544497091384')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `user_profile`");
        db.execSQL("DROP TABLE IF EXISTS `exercise_categories`");
        db.execSQL("DROP TABLE IF EXISTS `exercises`");
        db.execSQL("DROP TABLE IF EXISTS `workout_plans`");
        db.execSQL("DROP TABLE IF EXISTS `workout_days`");
        db.execSQL("DROP TABLE IF EXISTS `workout_exercises`");
        db.execSQL("DROP TABLE IF EXISTS `diet_plans`");
        db.execSQL("DROP TABLE IF EXISTS `meals`");
        db.execSQL("DROP TABLE IF EXISTS `progress_entries`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUserProfile = new HashMap<String, TableInfo.Column>(12);
        _columnsUserProfile.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("age", new TableInfo.Column("age", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("gender", new TableInfo.Column("gender", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("heightCm", new TableInfo.Column("heightCm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("weightKg", new TableInfo.Column("weightKg", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("fitnessGoal", new TableInfo.Column("fitnessGoal", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("experienceLevel", new TableInfo.Column("experienceLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("workoutDurationMinutes", new TableInfo.Column("workoutDurationMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("workoutDaysPerWeek", new TableInfo.Column("workoutDaysPerWeek", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("injuries", new TableInfo.Column("injuries", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("availableEquipment", new TableInfo.Column("availableEquipment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserProfile = new TableInfo("user_profile", _columnsUserProfile, _foreignKeysUserProfile, _indicesUserProfile);
        final TableInfo _existingUserProfile = TableInfo.read(db, "user_profile");
        if (!_infoUserProfile.equals(_existingUserProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "user_profile(com.pamu.gymbro.data.local.entity.UserProfileEntity).\n"
                  + " Expected:\n" + _infoUserProfile + "\n"
                  + " Found:\n" + _existingUserProfile);
        }
        final HashMap<String, TableInfo.Column> _columnsExerciseCategories = new HashMap<String, TableInfo.Column>(2);
        _columnsExerciseCategories.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseCategories.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExerciseCategories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExerciseCategories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExerciseCategories = new TableInfo("exercise_categories", _columnsExerciseCategories, _foreignKeysExerciseCategories, _indicesExerciseCategories);
        final TableInfo _existingExerciseCategories = TableInfo.read(db, "exercise_categories");
        if (!_infoExerciseCategories.equals(_existingExerciseCategories)) {
          return new RoomOpenHelper.ValidationResult(false, "exercise_categories(com.pamu.gymbro.data.local.entity.ExerciseCategoryEntity).\n"
                  + " Expected:\n" + _infoExerciseCategories + "\n"
                  + " Found:\n" + _existingExerciseCategories);
        }
        final HashMap<String, TableInfo.Column> _columnsExercises = new HashMap<String, TableInfo.Column>(25);
        _columnsExercises.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("difficulty", new TableInfo.Column("difficulty", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("equipment", new TableInfo.Column("equipment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("primaryMuscle", new TableInfo.Column("primaryMuscle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("secondaryMuscles", new TableInfo.Column("secondaryMuscles", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("exerciseType", new TableInfo.Column("exerciseType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("movementPattern", new TableInfo.Column("movementPattern", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("caloriesBurnedEstimate", new TableInfo.Column("caloriesBurnedEstimate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("benefits", new TableInfo.Column("benefits", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("commonMistakes", new TableInfo.Column("commonMistakes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("safetyWarnings", new TableInfo.Column("safetyWarnings", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("beginnerVariation", new TableInfo.Column("beginnerVariation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("intermediateVariation", new TableInfo.Column("intermediateVariation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("advancedVariation", new TableInfo.Column("advancedVariation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("instructions", new TableInfo.Column("instructions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("thumbnailUrl", new TableInfo.Column("thumbnailUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("videoFrontUrl", new TableInfo.Column("videoFrontUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("videoSideUrl", new TableInfo.Column("videoSideUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("videoDuration", new TableInfo.Column("videoDuration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("videoFps", new TableInfo.Column("videoFps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("videoResolution", new TableInfo.Column("videoResolution", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExercises = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysExercises.add(new TableInfo.ForeignKey("exercise_categories", "CASCADE", "NO ACTION", Arrays.asList("categoryId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesExercises = new HashSet<TableInfo.Index>(1);
        _indicesExercises.add(new TableInfo.Index("index_exercises_categoryId", false, Arrays.asList("categoryId"), Arrays.asList("ASC")));
        final TableInfo _infoExercises = new TableInfo("exercises", _columnsExercises, _foreignKeysExercises, _indicesExercises);
        final TableInfo _existingExercises = TableInfo.read(db, "exercises");
        if (!_infoExercises.equals(_existingExercises)) {
          return new RoomOpenHelper.ValidationResult(false, "exercises(com.pamu.gymbro.data.local.entity.ExerciseEntity).\n"
                  + " Expected:\n" + _infoExercises + "\n"
                  + " Found:\n" + _existingExercises);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkoutPlans = new HashMap<String, TableInfo.Column>(7);
        _columnsWorkoutPlans.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutPlans.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutPlans.put("level", new TableInfo.Column("level", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutPlans.put("goal", new TableInfo.Column("goal", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutPlans.put("durationWeeks", new TableInfo.Column("durationWeeks", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutPlans.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutPlans.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkoutPlans = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorkoutPlans = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorkoutPlans = new TableInfo("workout_plans", _columnsWorkoutPlans, _foreignKeysWorkoutPlans, _indicesWorkoutPlans);
        final TableInfo _existingWorkoutPlans = TableInfo.read(db, "workout_plans");
        if (!_infoWorkoutPlans.equals(_existingWorkoutPlans)) {
          return new RoomOpenHelper.ValidationResult(false, "workout_plans(com.pamu.gymbro.data.local.entity.WorkoutPlanEntity).\n"
                  + " Expected:\n" + _infoWorkoutPlans + "\n"
                  + " Found:\n" + _existingWorkoutPlans);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkoutDays = new HashMap<String, TableInfo.Column>(4);
        _columnsWorkoutDays.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutDays.put("workoutPlanId", new TableInfo.Column("workoutPlanId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutDays.put("dayNumber", new TableInfo.Column("dayNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutDays.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkoutDays = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysWorkoutDays.add(new TableInfo.ForeignKey("workout_plans", "CASCADE", "NO ACTION", Arrays.asList("workoutPlanId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesWorkoutDays = new HashSet<TableInfo.Index>(1);
        _indicesWorkoutDays.add(new TableInfo.Index("index_workout_days_workoutPlanId", false, Arrays.asList("workoutPlanId"), Arrays.asList("ASC")));
        final TableInfo _infoWorkoutDays = new TableInfo("workout_days", _columnsWorkoutDays, _foreignKeysWorkoutDays, _indicesWorkoutDays);
        final TableInfo _existingWorkoutDays = TableInfo.read(db, "workout_days");
        if (!_infoWorkoutDays.equals(_existingWorkoutDays)) {
          return new RoomOpenHelper.ValidationResult(false, "workout_days(com.pamu.gymbro.data.local.entity.WorkoutDayEntity).\n"
                  + " Expected:\n" + _infoWorkoutDays + "\n"
                  + " Found:\n" + _existingWorkoutDays);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkoutExercises = new HashMap<String, TableInfo.Column>(6);
        _columnsWorkoutExercises.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutExercises.put("workoutDayId", new TableInfo.Column("workoutDayId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutExercises.put("exerciseId", new TableInfo.Column("exerciseId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutExercises.put("sets", new TableInfo.Column("sets", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutExercises.put("reps", new TableInfo.Column("reps", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutExercises.put("restSeconds", new TableInfo.Column("restSeconds", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkoutExercises = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysWorkoutExercises.add(new TableInfo.ForeignKey("workout_days", "CASCADE", "NO ACTION", Arrays.asList("workoutDayId"), Arrays.asList("id")));
        _foreignKeysWorkoutExercises.add(new TableInfo.ForeignKey("exercises", "CASCADE", "NO ACTION", Arrays.asList("exerciseId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesWorkoutExercises = new HashSet<TableInfo.Index>(2);
        _indicesWorkoutExercises.add(new TableInfo.Index("index_workout_exercises_workoutDayId", false, Arrays.asList("workoutDayId"), Arrays.asList("ASC")));
        _indicesWorkoutExercises.add(new TableInfo.Index("index_workout_exercises_exerciseId", false, Arrays.asList("exerciseId"), Arrays.asList("ASC")));
        final TableInfo _infoWorkoutExercises = new TableInfo("workout_exercises", _columnsWorkoutExercises, _foreignKeysWorkoutExercises, _indicesWorkoutExercises);
        final TableInfo _existingWorkoutExercises = TableInfo.read(db, "workout_exercises");
        if (!_infoWorkoutExercises.equals(_existingWorkoutExercises)) {
          return new RoomOpenHelper.ValidationResult(false, "workout_exercises(com.pamu.gymbro.data.local.entity.WorkoutExerciseEntity).\n"
                  + " Expected:\n" + _infoWorkoutExercises + "\n"
                  + " Found:\n" + _existingWorkoutExercises);
        }
        final HashMap<String, TableInfo.Column> _columnsDietPlans = new HashMap<String, TableInfo.Column>(8);
        _columnsDietPlans.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietPlans.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietPlans.put("goal", new TableInfo.Column("goal", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietPlans.put("calories", new TableInfo.Column("calories", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietPlans.put("protein", new TableInfo.Column("protein", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietPlans.put("carbs", new TableInfo.Column("carbs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietPlans.put("fats", new TableInfo.Column("fats", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietPlans.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDietPlans = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDietPlans = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDietPlans = new TableInfo("diet_plans", _columnsDietPlans, _foreignKeysDietPlans, _indicesDietPlans);
        final TableInfo _existingDietPlans = TableInfo.read(db, "diet_plans");
        if (!_infoDietPlans.equals(_existingDietPlans)) {
          return new RoomOpenHelper.ValidationResult(false, "diet_plans(com.pamu.gymbro.data.local.entity.DietPlanEntity).\n"
                  + " Expected:\n" + _infoDietPlans + "\n"
                  + " Found:\n" + _existingDietPlans);
        }
        final HashMap<String, TableInfo.Column> _columnsMeals = new HashMap<String, TableInfo.Column>(8);
        _columnsMeals.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("dietPlanId", new TableInfo.Column("dietPlanId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("mealType", new TableInfo.Column("mealType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("calories", new TableInfo.Column("calories", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("protein", new TableInfo.Column("protein", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("carbs", new TableInfo.Column("carbs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("fats", new TableInfo.Column("fats", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMeals = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysMeals.add(new TableInfo.ForeignKey("diet_plans", "CASCADE", "NO ACTION", Arrays.asList("dietPlanId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesMeals = new HashSet<TableInfo.Index>(1);
        _indicesMeals.add(new TableInfo.Index("index_meals_dietPlanId", false, Arrays.asList("dietPlanId"), Arrays.asList("ASC")));
        final TableInfo _infoMeals = new TableInfo("meals", _columnsMeals, _foreignKeysMeals, _indicesMeals);
        final TableInfo _existingMeals = TableInfo.read(db, "meals");
        if (!_infoMeals.equals(_existingMeals)) {
          return new RoomOpenHelper.ValidationResult(false, "meals(com.pamu.gymbro.data.local.entity.MealEntity).\n"
                  + " Expected:\n" + _infoMeals + "\n"
                  + " Found:\n" + _existingMeals);
        }
        final HashMap<String, TableInfo.Column> _columnsProgressEntries = new HashMap<String, TableInfo.Column>(10);
        _columnsProgressEntries.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressEntries.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressEntries.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressEntries.put("chest", new TableInfo.Column("chest", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressEntries.put("waist", new TableInfo.Column("waist", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressEntries.put("hips", new TableInfo.Column("hips", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressEntries.put("arms", new TableInfo.Column("arms", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressEntries.put("thighs", new TableInfo.Column("thighs", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressEntries.put("bodyFat", new TableInfo.Column("bodyFat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressEntries.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProgressEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProgressEntries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProgressEntries = new TableInfo("progress_entries", _columnsProgressEntries, _foreignKeysProgressEntries, _indicesProgressEntries);
        final TableInfo _existingProgressEntries = TableInfo.read(db, "progress_entries");
        if (!_infoProgressEntries.equals(_existingProgressEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "progress_entries(com.pamu.gymbro.data.local.entity.ProgressEntryEntity).\n"
                  + " Expected:\n" + _infoProgressEntries + "\n"
                  + " Found:\n" + _existingProgressEntries);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "aa0bb0660030506e516c544497091384", "80285255cb63c636c6c44f46f00c34ac");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user_profile","exercise_categories","exercises","workout_plans","workout_days","workout_exercises","diet_plans","meals","progress_entries");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `user_profile`");
      _db.execSQL("DELETE FROM `exercise_categories`");
      _db.execSQL("DELETE FROM `exercises`");
      _db.execSQL("DELETE FROM `workout_plans`");
      _db.execSQL("DELETE FROM `workout_days`");
      _db.execSQL("DELETE FROM `workout_exercises`");
      _db.execSQL("DELETE FROM `diet_plans`");
      _db.execSQL("DELETE FROM `meals`");
      _db.execSQL("DELETE FROM `progress_entries`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExerciseDao.class, ExerciseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WorkoutDao.class, WorkoutDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DietDao.class, DietDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProgressDao.class, ProgressDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public ExerciseDao exerciseDao() {
    if (_exerciseDao != null) {
      return _exerciseDao;
    } else {
      synchronized(this) {
        if(_exerciseDao == null) {
          _exerciseDao = new ExerciseDao_Impl(this);
        }
        return _exerciseDao;
      }
    }
  }

  @Override
  public WorkoutDao workoutDao() {
    if (_workoutDao != null) {
      return _workoutDao;
    } else {
      synchronized(this) {
        if(_workoutDao == null) {
          _workoutDao = new WorkoutDao_Impl(this);
        }
        return _workoutDao;
      }
    }
  }

  @Override
  public DietDao dietDao() {
    if (_dietDao != null) {
      return _dietDao;
    } else {
      synchronized(this) {
        if(_dietDao == null) {
          _dietDao = new DietDao_Impl(this);
        }
        return _dietDao;
      }
    }
  }

  @Override
  public ProgressDao progressDao() {
    if (_progressDao != null) {
      return _progressDao;
    } else {
      synchronized(this) {
        if(_progressDao == null) {
          _progressDao = new ProgressDao_Impl(this);
        }
        return _progressDao;
      }
    }
  }
}
