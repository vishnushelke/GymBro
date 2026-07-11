package com.pamu.gymbro.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pamu.gymbro.data.local.entity.UserProfileEntity;
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
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserProfileEntity> __insertionAdapterOfUserProfileEntity;

  private final EntityDeletionOrUpdateAdapter<UserProfileEntity> __updateAdapterOfUserProfileEntity;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserProfileEntity = new EntityInsertionAdapter<UserProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_profile` (`id`,`name`,`age`,`gender`,`heightCm`,`weightKg`,`fitnessGoal`,`experienceLevel`,`workoutDurationMinutes`,`workoutDaysPerWeek`,`injuries`,`availableEquipment`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindLong(3, entity.getAge());
        statement.bindString(4, entity.getGender());
        statement.bindDouble(5, entity.getHeightCm());
        statement.bindDouble(6, entity.getWeightKg());
        statement.bindString(7, entity.getFitnessGoal());
        statement.bindString(8, entity.getExperienceLevel());
        statement.bindLong(9, entity.getWorkoutDurationMinutes());
        statement.bindLong(10, entity.getWorkoutDaysPerWeek());
        statement.bindString(11, entity.getInjuries());
        statement.bindString(12, entity.getAvailableEquipment());
      }
    };
    this.__updateAdapterOfUserProfileEntity = new EntityDeletionOrUpdateAdapter<UserProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_profile` SET `id` = ?,`name` = ?,`age` = ?,`gender` = ?,`heightCm` = ?,`weightKg` = ?,`fitnessGoal` = ?,`experienceLevel` = ?,`workoutDurationMinutes` = ?,`workoutDaysPerWeek` = ?,`injuries` = ?,`availableEquipment` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindLong(3, entity.getAge());
        statement.bindString(4, entity.getGender());
        statement.bindDouble(5, entity.getHeightCm());
        statement.bindDouble(6, entity.getWeightKg());
        statement.bindString(7, entity.getFitnessGoal());
        statement.bindString(8, entity.getExperienceLevel());
        statement.bindLong(9, entity.getWorkoutDurationMinutes());
        statement.bindLong(10, entity.getWorkoutDaysPerWeek());
        statement.bindString(11, entity.getInjuries());
        statement.bindString(12, entity.getAvailableEquipment());
        statement.bindLong(13, entity.getId());
      }
    };
  }

  @Override
  public void insertUserProfile(final UserProfileEntity profile) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserProfileEntity.insert(profile);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateUserProfile(final UserProfileEntity profile) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUserProfileEntity.handle(profile);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flow<UserProfileEntity> getUserProfile() {
    final String _sql = "SELECT * FROM user_profile WHERE id = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_profile"}, new Callable<UserProfileEntity>() {
      @Override
      @Nullable
      public UserProfileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfHeightCm = CursorUtil.getColumnIndexOrThrow(_cursor, "heightCm");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfFitnessGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "fitnessGoal");
          final int _cursorIndexOfExperienceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceLevel");
          final int _cursorIndexOfWorkoutDurationMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutDurationMinutes");
          final int _cursorIndexOfWorkoutDaysPerWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutDaysPerWeek");
          final int _cursorIndexOfInjuries = CursorUtil.getColumnIndexOrThrow(_cursor, "injuries");
          final int _cursorIndexOfAvailableEquipment = CursorUtil.getColumnIndexOrThrow(_cursor, "availableEquipment");
          final UserProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpGender;
            _tmpGender = _cursor.getString(_cursorIndexOfGender);
            final float _tmpHeightCm;
            _tmpHeightCm = _cursor.getFloat(_cursorIndexOfHeightCm);
            final float _tmpWeightKg;
            _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            final String _tmpFitnessGoal;
            _tmpFitnessGoal = _cursor.getString(_cursorIndexOfFitnessGoal);
            final String _tmpExperienceLevel;
            _tmpExperienceLevel = _cursor.getString(_cursorIndexOfExperienceLevel);
            final int _tmpWorkoutDurationMinutes;
            _tmpWorkoutDurationMinutes = _cursor.getInt(_cursorIndexOfWorkoutDurationMinutes);
            final int _tmpWorkoutDaysPerWeek;
            _tmpWorkoutDaysPerWeek = _cursor.getInt(_cursorIndexOfWorkoutDaysPerWeek);
            final String _tmpInjuries;
            _tmpInjuries = _cursor.getString(_cursorIndexOfInjuries);
            final String _tmpAvailableEquipment;
            _tmpAvailableEquipment = _cursor.getString(_cursorIndexOfAvailableEquipment);
            _result = new UserProfileEntity(_tmpId,_tmpName,_tmpAge,_tmpGender,_tmpHeightCm,_tmpWeightKg,_tmpFitnessGoal,_tmpExperienceLevel,_tmpWorkoutDurationMinutes,_tmpWorkoutDaysPerWeek,_tmpInjuries,_tmpAvailableEquipment);
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
