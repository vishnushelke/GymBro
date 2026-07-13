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
        return "INSERT OR REPLACE INTO `user_profile` (`id`,`name`,`isVegetarian`,`experienceLevel`,`fitnessGoal`,`email`,`phone`,`age`,`sex`,`isProfileCompleted`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final int _tmp = entity.isVegetarian() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindString(4, entity.getExperienceLevel());
        statement.bindString(5, entity.getFitnessGoal());
        statement.bindString(6, entity.getEmail());
        statement.bindString(7, entity.getPhone());
        statement.bindLong(8, entity.getAge());
        statement.bindString(9, entity.getSex());
        final int _tmp_1 = entity.isProfileCompleted() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
      }
    };
    this.__updateAdapterOfUserProfileEntity = new EntityDeletionOrUpdateAdapter<UserProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_profile` SET `id` = ?,`name` = ?,`isVegetarian` = ?,`experienceLevel` = ?,`fitnessGoal` = ?,`email` = ?,`phone` = ?,`age` = ?,`sex` = ?,`isProfileCompleted` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final int _tmp = entity.isVegetarian() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindString(4, entity.getExperienceLevel());
        statement.bindString(5, entity.getFitnessGoal());
        statement.bindString(6, entity.getEmail());
        statement.bindString(7, entity.getPhone());
        statement.bindLong(8, entity.getAge());
        statement.bindString(9, entity.getSex());
        final int _tmp_1 = entity.isProfileCompleted() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        statement.bindLong(11, entity.getId());
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
          final int _cursorIndexOfIsVegetarian = CursorUtil.getColumnIndexOrThrow(_cursor, "isVegetarian");
          final int _cursorIndexOfExperienceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceLevel");
          final int _cursorIndexOfFitnessGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "fitnessGoal");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfSex = CursorUtil.getColumnIndexOrThrow(_cursor, "sex");
          final int _cursorIndexOfIsProfileCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isProfileCompleted");
          final UserProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final boolean _tmpIsVegetarian;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsVegetarian);
            _tmpIsVegetarian = _tmp != 0;
            final String _tmpExperienceLevel;
            _tmpExperienceLevel = _cursor.getString(_cursorIndexOfExperienceLevel);
            final String _tmpFitnessGoal;
            _tmpFitnessGoal = _cursor.getString(_cursorIndexOfFitnessGoal);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpSex;
            _tmpSex = _cursor.getString(_cursorIndexOfSex);
            final boolean _tmpIsProfileCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsProfileCompleted);
            _tmpIsProfileCompleted = _tmp_1 != 0;
            _result = new UserProfileEntity(_tmpId,_tmpName,_tmpIsVegetarian,_tmpExperienceLevel,_tmpFitnessGoal,_tmpEmail,_tmpPhone,_tmpAge,_tmpSex,_tmpIsProfileCompleted);
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
