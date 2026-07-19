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
import com.pamu.gymbro.data.local.entity.DailyStatsEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DailyStatsDao_Impl implements DailyStatsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DailyStatsEntity> __insertionAdapterOfDailyStatsEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateWaterIntake;

  public DailyStatsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDailyStatsEntity = new EntityInsertionAdapter<DailyStatsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `daily_stats` (`date`,`caloriesBurned`,`caloriesConsumed`,`waterIntakeMl`,`workoutCompleted`,`workoutProgressPercentage`,`totalReps`,`totalSets`,`totalDurationMinutes`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyStatsEntity entity) {
        statement.bindString(1, entity.getDate());
        statement.bindLong(2, entity.getCaloriesBurned());
        statement.bindLong(3, entity.getCaloriesConsumed());
        statement.bindLong(4, entity.getWaterIntakeMl());
        final int _tmp = entity.getWorkoutCompleted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getWorkoutProgressPercentage());
        statement.bindLong(7, entity.getTotalReps());
        statement.bindLong(8, entity.getTotalSets());
        statement.bindLong(9, entity.getTotalDurationMinutes());
      }
    };
    this.__preparedStmtOfUpdateWaterIntake = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE daily_stats SET waterIntakeMl = waterIntakeMl + ? WHERE date = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertStats(final DailyStatsEntity stats) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDailyStatsEntity.insert(stats);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateWaterIntake(final String date, final int amount) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateWaterIntake.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, amount);
    _argIndex = 2;
    _stmt.bindString(_argIndex, date);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateWaterIntake.release(_stmt);
    }
  }

  @Override
  public Flow<DailyStatsEntity> getStatsForDate(final String date) {
    final String _sql = "SELECT * FROM daily_stats WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_stats"}, new Callable<DailyStatsEntity>() {
      @Override
      @Nullable
      public DailyStatsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfCaloriesBurned = CursorUtil.getColumnIndexOrThrow(_cursor, "caloriesBurned");
          final int _cursorIndexOfCaloriesConsumed = CursorUtil.getColumnIndexOrThrow(_cursor, "caloriesConsumed");
          final int _cursorIndexOfWaterIntakeMl = CursorUtil.getColumnIndexOrThrow(_cursor, "waterIntakeMl");
          final int _cursorIndexOfWorkoutCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutCompleted");
          final int _cursorIndexOfWorkoutProgressPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutProgressPercentage");
          final int _cursorIndexOfTotalReps = CursorUtil.getColumnIndexOrThrow(_cursor, "totalReps");
          final int _cursorIndexOfTotalSets = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSets");
          final int _cursorIndexOfTotalDurationMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDurationMinutes");
          final DailyStatsEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final int _tmpCaloriesBurned;
            _tmpCaloriesBurned = _cursor.getInt(_cursorIndexOfCaloriesBurned);
            final int _tmpCaloriesConsumed;
            _tmpCaloriesConsumed = _cursor.getInt(_cursorIndexOfCaloriesConsumed);
            final int _tmpWaterIntakeMl;
            _tmpWaterIntakeMl = _cursor.getInt(_cursorIndexOfWaterIntakeMl);
            final boolean _tmpWorkoutCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfWorkoutCompleted);
            _tmpWorkoutCompleted = _tmp != 0;
            final int _tmpWorkoutProgressPercentage;
            _tmpWorkoutProgressPercentage = _cursor.getInt(_cursorIndexOfWorkoutProgressPercentage);
            final int _tmpTotalReps;
            _tmpTotalReps = _cursor.getInt(_cursorIndexOfTotalReps);
            final int _tmpTotalSets;
            _tmpTotalSets = _cursor.getInt(_cursorIndexOfTotalSets);
            final int _tmpTotalDurationMinutes;
            _tmpTotalDurationMinutes = _cursor.getInt(_cursorIndexOfTotalDurationMinutes);
            _result = new DailyStatsEntity(_tmpDate,_tmpCaloriesBurned,_tmpCaloriesConsumed,_tmpWaterIntakeMl,_tmpWorkoutCompleted,_tmpWorkoutProgressPercentage,_tmpTotalReps,_tmpTotalSets,_tmpTotalDurationMinutes);
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
