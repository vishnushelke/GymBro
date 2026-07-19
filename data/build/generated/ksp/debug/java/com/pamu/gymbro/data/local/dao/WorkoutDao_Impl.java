package com.pamu.gymbro.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pamu.gymbro.data.local.entity.ExerciseEntity;
import com.pamu.gymbro.data.local.entity.WorkoutDayEntity;
import com.pamu.gymbro.data.local.entity.WorkoutExerciseEntity;
import com.pamu.gymbro.data.local.entity.WorkoutPlanEntity;
import com.pamu.gymbro.data.local.model.WorkoutDayWithExercises;
import com.pamu.gymbro.data.local.model.WorkoutExerciseWithExercise;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WorkoutDao_Impl implements WorkoutDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkoutPlanEntity> __insertionAdapterOfWorkoutPlanEntity;

  private final EntityInsertionAdapter<WorkoutDayEntity> __insertionAdapterOfWorkoutDayEntity;

  private final EntityInsertionAdapter<WorkoutExerciseEntity> __insertionAdapterOfWorkoutExerciseEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteWorkoutPlan;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDaysForPlan;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFavoriteStatus;

  public WorkoutDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkoutPlanEntity = new EntityInsertionAdapter<WorkoutPlanEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `workout_plans` (`id`,`name`,`level`,`goal`,`durationWeeks`,`description`,`isFavorite`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkoutPlanEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getLevel());
        statement.bindString(4, entity.getGoal());
        statement.bindLong(5, entity.getDurationWeeks());
        statement.bindString(6, entity.getDescription());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(7, _tmp);
      }
    };
    this.__insertionAdapterOfWorkoutDayEntity = new EntityInsertionAdapter<WorkoutDayEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `workout_days` (`id`,`workoutPlanId`,`dayNumber`,`title`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkoutDayEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getWorkoutPlanId());
        statement.bindLong(3, entity.getDayNumber());
        statement.bindString(4, entity.getTitle());
      }
    };
    this.__insertionAdapterOfWorkoutExerciseEntity = new EntityInsertionAdapter<WorkoutExerciseEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `workout_exercises` (`id`,`workoutDayId`,`exerciseId`,`sets`,`reps`,`restSeconds`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkoutExerciseEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getWorkoutDayId());
        statement.bindLong(3, entity.getExerciseId());
        statement.bindLong(4, entity.getSets());
        statement.bindString(5, entity.getReps());
        statement.bindLong(6, entity.getRestSeconds());
      }
    };
    this.__preparedStmtOfDeleteWorkoutPlan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM workout_plans WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteDaysForPlan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM workout_days WHERE workoutPlanId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateFavoriteStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE workout_plans SET isFavorite = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insertWorkoutPlan(final WorkoutPlanEntity plan) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfWorkoutPlanEntity.insertAndReturnId(plan);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertWorkoutDays(final List<WorkoutDayEntity> days) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final List<Long> _result = __insertionAdapterOfWorkoutDayEntity.insertAndReturnIdsList(days);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertWorkoutExercises(final List<WorkoutExerciseEntity> exercises) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWorkoutExerciseEntity.insert(exercises);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteWorkoutPlan(final long planId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteWorkoutPlan.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, planId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteWorkoutPlan.release(_stmt);
    }
  }

  @Override
  public void deleteDaysForPlan(final long planId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDaysForPlan.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, planId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteDaysForPlan.release(_stmt);
    }
  }

  @Override
  public void updateFavoriteStatus(final long id, final boolean isFavorite) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFavoriteStatus.acquire();
    int _argIndex = 1;
    final int _tmp = isFavorite ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateFavoriteStatus.release(_stmt);
    }
  }

  @Override
  public Flow<List<WorkoutPlanEntity>> getAllWorkoutPlans() {
    final String _sql = "SELECT * FROM workout_plans";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workout_plans"}, new Callable<List<WorkoutPlanEntity>>() {
      @Override
      @NonNull
      public List<WorkoutPlanEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "goal");
          final int _cursorIndexOfDurationWeeks = CursorUtil.getColumnIndexOrThrow(_cursor, "durationWeeks");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<WorkoutPlanEntity> _result = new ArrayList<WorkoutPlanEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkoutPlanEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpLevel;
            _tmpLevel = _cursor.getString(_cursorIndexOfLevel);
            final String _tmpGoal;
            _tmpGoal = _cursor.getString(_cursorIndexOfGoal);
            final int _tmpDurationWeeks;
            _tmpDurationWeeks = _cursor.getInt(_cursorIndexOfDurationWeeks);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new WorkoutPlanEntity(_tmpId,_tmpName,_tmpLevel,_tmpGoal,_tmpDurationWeeks,_tmpDescription,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<WorkoutPlanEntity> getWorkoutPlanById(final long planId) {
    final String _sql = "SELECT * FROM workout_plans WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, planId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workout_plans"}, new Callable<WorkoutPlanEntity>() {
      @Override
      @Nullable
      public WorkoutPlanEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "goal");
          final int _cursorIndexOfDurationWeeks = CursorUtil.getColumnIndexOrThrow(_cursor, "durationWeeks");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final WorkoutPlanEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpLevel;
            _tmpLevel = _cursor.getString(_cursorIndexOfLevel);
            final String _tmpGoal;
            _tmpGoal = _cursor.getString(_cursorIndexOfGoal);
            final int _tmpDurationWeeks;
            _tmpDurationWeeks = _cursor.getInt(_cursorIndexOfDurationWeeks);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _result = new WorkoutPlanEntity(_tmpId,_tmpName,_tmpLevel,_tmpGoal,_tmpDurationWeeks,_tmpDescription,_tmpIsFavorite);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<WorkoutDayWithExercises>> getWorkoutDaysWithExercisesForPlan(final long planId) {
    final String _sql = "SELECT * FROM workout_days WHERE workoutPlanId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, planId);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"exercises", "workout_exercises",
        "workout_days"}, new Callable<List<WorkoutDayWithExercises>>() {
      @Override
      @NonNull
      public List<WorkoutDayWithExercises> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfWorkoutPlanId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutPlanId");
            final int _cursorIndexOfDayNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "dayNumber");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final LongSparseArray<ArrayList<WorkoutExerciseWithExercise>> _collectionWorkoutExercises = new LongSparseArray<ArrayList<WorkoutExerciseWithExercise>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionWorkoutExercises.containsKey(_tmpKey)) {
                _collectionWorkoutExercises.put(_tmpKey, new ArrayList<WorkoutExerciseWithExercise>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipworkoutExercisesAscomPamuGymbroDataLocalModelWorkoutExerciseWithExercise(_collectionWorkoutExercises);
            final List<WorkoutDayWithExercises> _result = new ArrayList<WorkoutDayWithExercises>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final WorkoutDayWithExercises _item;
              final WorkoutDayEntity _tmpWorkoutDay;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final long _tmpWorkoutPlanId;
              _tmpWorkoutPlanId = _cursor.getLong(_cursorIndexOfWorkoutPlanId);
              final int _tmpDayNumber;
              _tmpDayNumber = _cursor.getInt(_cursorIndexOfDayNumber);
              final String _tmpTitle;
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              _tmpWorkoutDay = new WorkoutDayEntity(_tmpId,_tmpWorkoutPlanId,_tmpDayNumber,_tmpTitle);
              final ArrayList<WorkoutExerciseWithExercise> _tmpWorkoutExercisesCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpWorkoutExercisesCollection = _collectionWorkoutExercises.get(_tmpKey_1);
              _item = new WorkoutDayWithExercises(_tmpWorkoutDay,_tmpWorkoutExercisesCollection);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<WorkoutDayWithExercises> getWorkoutDayWithExercisesById(final long dayId) {
    final String _sql = "SELECT * FROM workout_days WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dayId);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"exercises", "workout_exercises",
        "workout_days"}, new Callable<WorkoutDayWithExercises>() {
      @Override
      @Nullable
      public WorkoutDayWithExercises call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfWorkoutPlanId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutPlanId");
            final int _cursorIndexOfDayNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "dayNumber");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final LongSparseArray<ArrayList<WorkoutExerciseWithExercise>> _collectionWorkoutExercises = new LongSparseArray<ArrayList<WorkoutExerciseWithExercise>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionWorkoutExercises.containsKey(_tmpKey)) {
                _collectionWorkoutExercises.put(_tmpKey, new ArrayList<WorkoutExerciseWithExercise>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipworkoutExercisesAscomPamuGymbroDataLocalModelWorkoutExerciseWithExercise(_collectionWorkoutExercises);
            final WorkoutDayWithExercises _result;
            if (_cursor.moveToFirst()) {
              final WorkoutDayEntity _tmpWorkoutDay;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final long _tmpWorkoutPlanId;
              _tmpWorkoutPlanId = _cursor.getLong(_cursorIndexOfWorkoutPlanId);
              final int _tmpDayNumber;
              _tmpDayNumber = _cursor.getInt(_cursorIndexOfDayNumber);
              final String _tmpTitle;
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              _tmpWorkoutDay = new WorkoutDayEntity(_tmpId,_tmpWorkoutPlanId,_tmpDayNumber,_tmpTitle);
              final ArrayList<WorkoutExerciseWithExercise> _tmpWorkoutExercisesCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpWorkoutExercisesCollection = _collectionWorkoutExercises.get(_tmpKey_1);
              _result = new WorkoutDayWithExercises(_tmpWorkoutDay,_tmpWorkoutExercisesCollection);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<WorkoutPlanEntity>> getFavoriteWorkoutPlans() {
    final String _sql = "SELECT * FROM workout_plans WHERE isFavorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workout_plans"}, new Callable<List<WorkoutPlanEntity>>() {
      @Override
      @NonNull
      public List<WorkoutPlanEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "goal");
          final int _cursorIndexOfDurationWeeks = CursorUtil.getColumnIndexOrThrow(_cursor, "durationWeeks");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<WorkoutPlanEntity> _result = new ArrayList<WorkoutPlanEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkoutPlanEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpLevel;
            _tmpLevel = _cursor.getString(_cursorIndexOfLevel);
            final String _tmpGoal;
            _tmpGoal = _cursor.getString(_cursorIndexOfGoal);
            final int _tmpDurationWeeks;
            _tmpDurationWeeks = _cursor.getInt(_cursorIndexOfDurationWeeks);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new WorkoutPlanEntity(_tmpId,_tmpName,_tmpLevel,_tmpGoal,_tmpDurationWeeks,_tmpDescription,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipexercisesAscomPamuGymbroDataLocalEntityExerciseEntity(
      @NonNull final LongSparseArray<ExerciseEntity> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, false, (map) -> {
        __fetchRelationshipexercisesAscomPamuGymbroDataLocalEntityExerciseEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`name`,`categoryId`,`difficulty`,`equipment`,`primaryMuscle`,`secondaryMuscles`,`exerciseType`,`movementPattern`,`caloriesBurnedEstimate`,`description`,`benefits`,`commonMistakes`,`safetyWarnings`,`beginnerVariation`,`intermediateVariation`,`advancedVariation`,`instructions`,`thumbnailUrl`,`videoFrontUrl`,`videoSideUrl`,`videoDuration`,`videoFps`,`videoResolution`,`isFavorite` FROM `exercises` WHERE `id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfName = 1;
      final int _cursorIndexOfCategoryId = 2;
      final int _cursorIndexOfDifficulty = 3;
      final int _cursorIndexOfEquipment = 4;
      final int _cursorIndexOfPrimaryMuscle = 5;
      final int _cursorIndexOfSecondaryMuscles = 6;
      final int _cursorIndexOfExerciseType = 7;
      final int _cursorIndexOfMovementPattern = 8;
      final int _cursorIndexOfCaloriesBurnedEstimate = 9;
      final int _cursorIndexOfDescription = 10;
      final int _cursorIndexOfBenefits = 11;
      final int _cursorIndexOfCommonMistakes = 12;
      final int _cursorIndexOfSafetyWarnings = 13;
      final int _cursorIndexOfBeginnerVariation = 14;
      final int _cursorIndexOfIntermediateVariation = 15;
      final int _cursorIndexOfAdvancedVariation = 16;
      final int _cursorIndexOfInstructions = 17;
      final int _cursorIndexOfThumbnailUrl = 18;
      final int _cursorIndexOfVideoFrontUrl = 19;
      final int _cursorIndexOfVideoSideUrl = 20;
      final int _cursorIndexOfVideoDuration = 21;
      final int _cursorIndexOfVideoFps = 22;
      final int _cursorIndexOfVideoResolution = 23;
      final int _cursorIndexOfIsFavorite = 24;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final ExerciseEntity _item_1;
          final long _tmpId;
          _tmpId = _cursor.getLong(_cursorIndexOfId);
          final String _tmpName;
          _tmpName = _cursor.getString(_cursorIndexOfName);
          final long _tmpCategoryId;
          _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
          final String _tmpDifficulty;
          _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
          final String _tmpEquipment;
          _tmpEquipment = _cursor.getString(_cursorIndexOfEquipment);
          final String _tmpPrimaryMuscle;
          _tmpPrimaryMuscle = _cursor.getString(_cursorIndexOfPrimaryMuscle);
          final String _tmpSecondaryMuscles;
          _tmpSecondaryMuscles = _cursor.getString(_cursorIndexOfSecondaryMuscles);
          final String _tmpExerciseType;
          _tmpExerciseType = _cursor.getString(_cursorIndexOfExerciseType);
          final String _tmpMovementPattern;
          _tmpMovementPattern = _cursor.getString(_cursorIndexOfMovementPattern);
          final int _tmpCaloriesBurnedEstimate;
          _tmpCaloriesBurnedEstimate = _cursor.getInt(_cursorIndexOfCaloriesBurnedEstimate);
          final String _tmpDescription;
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
          final String _tmpBenefits;
          _tmpBenefits = _cursor.getString(_cursorIndexOfBenefits);
          final String _tmpCommonMistakes;
          _tmpCommonMistakes = _cursor.getString(_cursorIndexOfCommonMistakes);
          final String _tmpSafetyWarnings;
          _tmpSafetyWarnings = _cursor.getString(_cursorIndexOfSafetyWarnings);
          final String _tmpBeginnerVariation;
          _tmpBeginnerVariation = _cursor.getString(_cursorIndexOfBeginnerVariation);
          final String _tmpIntermediateVariation;
          _tmpIntermediateVariation = _cursor.getString(_cursorIndexOfIntermediateVariation);
          final String _tmpAdvancedVariation;
          _tmpAdvancedVariation = _cursor.getString(_cursorIndexOfAdvancedVariation);
          final String _tmpInstructions;
          _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
          final String _tmpThumbnailUrl;
          _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
          final String _tmpVideoFrontUrl;
          _tmpVideoFrontUrl = _cursor.getString(_cursorIndexOfVideoFrontUrl);
          final String _tmpVideoSideUrl;
          _tmpVideoSideUrl = _cursor.getString(_cursorIndexOfVideoSideUrl);
          final int _tmpVideoDuration;
          _tmpVideoDuration = _cursor.getInt(_cursorIndexOfVideoDuration);
          final int _tmpVideoFps;
          _tmpVideoFps = _cursor.getInt(_cursorIndexOfVideoFps);
          final String _tmpVideoResolution;
          _tmpVideoResolution = _cursor.getString(_cursorIndexOfVideoResolution);
          final boolean _tmpIsFavorite;
          final int _tmp;
          _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
          _tmpIsFavorite = _tmp != 0;
          _item_1 = new ExerciseEntity(_tmpId,_tmpName,_tmpCategoryId,_tmpDifficulty,_tmpEquipment,_tmpPrimaryMuscle,_tmpSecondaryMuscles,_tmpExerciseType,_tmpMovementPattern,_tmpCaloriesBurnedEstimate,_tmpDescription,_tmpBenefits,_tmpCommonMistakes,_tmpSafetyWarnings,_tmpBeginnerVariation,_tmpIntermediateVariation,_tmpAdvancedVariation,_tmpInstructions,_tmpThumbnailUrl,_tmpVideoFrontUrl,_tmpVideoSideUrl,_tmpVideoDuration,_tmpVideoFps,_tmpVideoResolution,_tmpIsFavorite);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipworkoutExercisesAscomPamuGymbroDataLocalModelWorkoutExerciseWithExercise(
      @NonNull final LongSparseArray<ArrayList<WorkoutExerciseWithExercise>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipworkoutExercisesAscomPamuGymbroDataLocalModelWorkoutExerciseWithExercise(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`workoutDayId`,`exerciseId`,`sets`,`reps`,`restSeconds` FROM `workout_exercises` WHERE `workoutDayId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, true, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "workoutDayId");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfWorkoutDayId = 1;
      final int _cursorIndexOfExerciseId = 2;
      final int _cursorIndexOfSets = 3;
      final int _cursorIndexOfReps = 4;
      final int _cursorIndexOfRestSeconds = 5;
      final LongSparseArray<ExerciseEntity> _collectionExercise = new LongSparseArray<ExerciseEntity>();
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_cursorIndexOfExerciseId);
        _collectionExercise.put(_tmpKey, null);
      }
      _cursor.moveToPosition(-1);
      __fetchRelationshipexercisesAscomPamuGymbroDataLocalEntityExerciseEntity(_collectionExercise);
      while (_cursor.moveToNext()) {
        final long _tmpKey_1;
        _tmpKey_1 = _cursor.getLong(_itemKeyIndex);
        final ArrayList<WorkoutExerciseWithExercise> _tmpRelation = _map.get(_tmpKey_1);
        if (_tmpRelation != null) {
          final WorkoutExerciseWithExercise _item_1;
          final WorkoutExerciseEntity _tmpWorkoutExercise;
          final long _tmpId;
          _tmpId = _cursor.getLong(_cursorIndexOfId);
          final long _tmpWorkoutDayId;
          _tmpWorkoutDayId = _cursor.getLong(_cursorIndexOfWorkoutDayId);
          final long _tmpExerciseId;
          _tmpExerciseId = _cursor.getLong(_cursorIndexOfExerciseId);
          final int _tmpSets;
          _tmpSets = _cursor.getInt(_cursorIndexOfSets);
          final String _tmpReps;
          _tmpReps = _cursor.getString(_cursorIndexOfReps);
          final int _tmpRestSeconds;
          _tmpRestSeconds = _cursor.getInt(_cursorIndexOfRestSeconds);
          _tmpWorkoutExercise = new WorkoutExerciseEntity(_tmpId,_tmpWorkoutDayId,_tmpExerciseId,_tmpSets,_tmpReps,_tmpRestSeconds);
          final ExerciseEntity _tmpExercise;
          final long _tmpKey_2;
          _tmpKey_2 = _cursor.getLong(_cursorIndexOfExerciseId);
          _tmpExercise = _collectionExercise.get(_tmpKey_2);
          _item_1 = new WorkoutExerciseWithExercise(_tmpWorkoutExercise,_tmpExercise);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
