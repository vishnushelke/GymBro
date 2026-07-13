package com.pamu.gymbro.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pamu.gymbro.data.local.entity.WorkoutDayEntity;
import com.pamu.gymbro.data.local.entity.WorkoutExerciseEntity;
import com.pamu.gymbro.data.local.entity.WorkoutPlanEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
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
  public Flow<List<WorkoutDayEntity>> getWorkoutDaysForPlan(final long planId) {
    final String _sql = "SELECT * FROM workout_days WHERE workoutPlanId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, planId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workout_days"}, new Callable<List<WorkoutDayEntity>>() {
      @Override
      @NonNull
      public List<WorkoutDayEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWorkoutPlanId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutPlanId");
          final int _cursorIndexOfDayNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "dayNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final List<WorkoutDayEntity> _result = new ArrayList<WorkoutDayEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkoutDayEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWorkoutPlanId;
            _tmpWorkoutPlanId = _cursor.getLong(_cursorIndexOfWorkoutPlanId);
            final int _tmpDayNumber;
            _tmpDayNumber = _cursor.getInt(_cursorIndexOfDayNumber);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item = new WorkoutDayEntity(_tmpId,_tmpWorkoutPlanId,_tmpDayNumber,_tmpTitle);
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
  public Flow<List<WorkoutExerciseEntity>> getExercisesForDay(final long dayId) {
    final String _sql = "SELECT * FROM workout_exercises WHERE workoutDayId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dayId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workout_exercises"}, new Callable<List<WorkoutExerciseEntity>>() {
      @Override
      @NonNull
      public List<WorkoutExerciseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWorkoutDayId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutDayId");
          final int _cursorIndexOfExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
          final int _cursorIndexOfSets = CursorUtil.getColumnIndexOrThrow(_cursor, "sets");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final int _cursorIndexOfRestSeconds = CursorUtil.getColumnIndexOrThrow(_cursor, "restSeconds");
          final List<WorkoutExerciseEntity> _result = new ArrayList<WorkoutExerciseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkoutExerciseEntity _item;
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
            _item = new WorkoutExerciseEntity(_tmpId,_tmpWorkoutDayId,_tmpExerciseId,_tmpSets,_tmpReps,_tmpRestSeconds);
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
}
