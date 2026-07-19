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
import com.pamu.gymbro.data.local.converter.DateConverter;
import com.pamu.gymbro.data.local.entity.WorkoutSessionEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WorkoutSessionDao_Impl implements WorkoutSessionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkoutSessionEntity> __insertionAdapterOfWorkoutSessionEntity;

  private final DateConverter __dateConverter = new DateConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteActiveSession;

  public WorkoutSessionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkoutSessionEntity = new EntityInsertionAdapter<WorkoutSessionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `active_workout_sessions` (`id`,`planId`,`dayId`,`startTime`,`currentExerciseIndex`,`currentSetNumber`,`totalRepsCompleted`,`totalWeightLifted`,`isPaused`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkoutSessionEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPlanId());
        statement.bindLong(3, entity.getDayId());
        final Long _tmp = __dateConverter.dateToTimestamp(entity.getStartTime());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        statement.bindLong(5, entity.getCurrentExerciseIndex());
        statement.bindLong(6, entity.getCurrentSetNumber());
        statement.bindLong(7, entity.getTotalRepsCompleted());
        statement.bindDouble(8, entity.getTotalWeightLifted());
        final int _tmp_1 = entity.isPaused() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
      }
    };
    this.__preparedStmtOfDeleteActiveSession = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM active_workout_sessions WHERE id = 1";
        return _query;
      }
    };
  }

  @Override
  public void insertSession(final WorkoutSessionEntity session) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWorkoutSessionEntity.insert(session);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteActiveSession() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteActiveSession.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteActiveSession.release(_stmt);
    }
  }

  @Override
  public Flow<WorkoutSessionEntity> getActiveSession() {
    final String _sql = "SELECT * FROM active_workout_sessions WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"active_workout_sessions"}, new Callable<WorkoutSessionEntity>() {
      @Override
      @Nullable
      public WorkoutSessionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlanId = CursorUtil.getColumnIndexOrThrow(_cursor, "planId");
          final int _cursorIndexOfDayId = CursorUtil.getColumnIndexOrThrow(_cursor, "dayId");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfCurrentExerciseIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "currentExerciseIndex");
          final int _cursorIndexOfCurrentSetNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "currentSetNumber");
          final int _cursorIndexOfTotalRepsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "totalRepsCompleted");
          final int _cursorIndexOfTotalWeightLifted = CursorUtil.getColumnIndexOrThrow(_cursor, "totalWeightLifted");
          final int _cursorIndexOfIsPaused = CursorUtil.getColumnIndexOrThrow(_cursor, "isPaused");
          final WorkoutSessionEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPlanId;
            _tmpPlanId = _cursor.getLong(_cursorIndexOfPlanId);
            final long _tmpDayId;
            _tmpDayId = _cursor.getLong(_cursorIndexOfDayId);
            final Date _tmpStartTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfStartTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfStartTime);
            }
            final Date _tmp_1 = __dateConverter.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpStartTime = _tmp_1;
            }
            final int _tmpCurrentExerciseIndex;
            _tmpCurrentExerciseIndex = _cursor.getInt(_cursorIndexOfCurrentExerciseIndex);
            final int _tmpCurrentSetNumber;
            _tmpCurrentSetNumber = _cursor.getInt(_cursorIndexOfCurrentSetNumber);
            final int _tmpTotalRepsCompleted;
            _tmpTotalRepsCompleted = _cursor.getInt(_cursorIndexOfTotalRepsCompleted);
            final double _tmpTotalWeightLifted;
            _tmpTotalWeightLifted = _cursor.getDouble(_cursorIndexOfTotalWeightLifted);
            final boolean _tmpIsPaused;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPaused);
            _tmpIsPaused = _tmp_2 != 0;
            _result = new WorkoutSessionEntity(_tmpId,_tmpPlanId,_tmpDayId,_tmpStartTime,_tmpCurrentExerciseIndex,_tmpCurrentSetNumber,_tmpTotalRepsCompleted,_tmpTotalWeightLifted,_tmpIsPaused);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
