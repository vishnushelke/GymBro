package com.pamu.gymbro.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pamu.gymbro.data.local.entity.ExerciseCategoryEntity;
import com.pamu.gymbro.data.local.entity.ExerciseEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class ExerciseDao_Impl implements ExerciseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExerciseCategoryEntity> __insertionAdapterOfExerciseCategoryEntity;

  private final EntityInsertionAdapter<ExerciseEntity> __insertionAdapterOfExerciseEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFavoriteStatus;

  public ExerciseDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExerciseCategoryEntity = new EntityInsertionAdapter<ExerciseCategoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `exercise_categories` (`id`,`name`) VALUES (nullif(?, 0),?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExerciseCategoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
      }
    };
    this.__insertionAdapterOfExerciseEntity = new EntityInsertionAdapter<ExerciseEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `exercises` (`id`,`name`,`categoryId`,`difficulty`,`equipment`,`primaryMuscle`,`secondaryMuscle`,`description`,`instructions`,`tips`,`commonMistakes`,`imageUrl`,`videoUrl`,`caloriesBurned`,`isFavorite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExerciseEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindLong(3, entity.getCategoryId());
        statement.bindString(4, entity.getDifficulty());
        statement.bindString(5, entity.getEquipment());
        statement.bindString(6, entity.getPrimaryMuscle());
        statement.bindString(7, entity.getSecondaryMuscle());
        statement.bindString(8, entity.getDescription());
        statement.bindString(9, entity.getInstructions());
        statement.bindString(10, entity.getTips());
        statement.bindString(11, entity.getCommonMistakes());
        statement.bindString(12, entity.getImageUrl());
        statement.bindString(13, entity.getVideoUrl());
        statement.bindLong(14, entity.getCaloriesBurned());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(15, _tmp);
      }
    };
    this.__preparedStmtOfUpdateFavoriteStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE exercises SET isFavorite = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertCategories(final List<ExerciseCategoryEntity> categories) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfExerciseCategoryEntity.insert(categories);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertExercises(final List<ExerciseEntity> exercises) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfExerciseEntity.insert(exercises);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
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
  public Flow<List<ExerciseCategoryEntity>> getAllCategories() {
    final String _sql = "SELECT * FROM exercise_categories";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercise_categories"}, new Callable<List<ExerciseCategoryEntity>>() {
      @Override
      @NonNull
      public List<ExerciseCategoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final List<ExerciseCategoryEntity> _result = new ArrayList<ExerciseCategoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExerciseCategoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item = new ExerciseCategoryEntity(_tmpId,_tmpName);
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
  public Flow<List<ExerciseEntity>> getAllExercises() {
    final String _sql = "SELECT * FROM exercises";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercises"}, new Callable<List<ExerciseEntity>>() {
      @Override
      @NonNull
      public List<ExerciseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfEquipment = CursorUtil.getColumnIndexOrThrow(_cursor, "equipment");
          final int _cursorIndexOfPrimaryMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryMuscle");
          final int _cursorIndexOfSecondaryMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "secondaryMuscle");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTips = CursorUtil.getColumnIndexOrThrow(_cursor, "tips");
          final int _cursorIndexOfCommonMistakes = CursorUtil.getColumnIndexOrThrow(_cursor, "commonMistakes");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfVideoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "videoUrl");
          final int _cursorIndexOfCaloriesBurned = CursorUtil.getColumnIndexOrThrow(_cursor, "caloriesBurned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<ExerciseEntity> _result = new ArrayList<ExerciseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExerciseEntity _item;
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
            final String _tmpSecondaryMuscle;
            _tmpSecondaryMuscle = _cursor.getString(_cursorIndexOfSecondaryMuscle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpInstructions;
            _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            final String _tmpTips;
            _tmpTips = _cursor.getString(_cursorIndexOfTips);
            final String _tmpCommonMistakes;
            _tmpCommonMistakes = _cursor.getString(_cursorIndexOfCommonMistakes);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final String _tmpVideoUrl;
            _tmpVideoUrl = _cursor.getString(_cursorIndexOfVideoUrl);
            final int _tmpCaloriesBurned;
            _tmpCaloriesBurned = _cursor.getInt(_cursorIndexOfCaloriesBurned);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new ExerciseEntity(_tmpId,_tmpName,_tmpCategoryId,_tmpDifficulty,_tmpEquipment,_tmpPrimaryMuscle,_tmpSecondaryMuscle,_tmpDescription,_tmpInstructions,_tmpTips,_tmpCommonMistakes,_tmpImageUrl,_tmpVideoUrl,_tmpCaloriesBurned,_tmpIsFavorite);
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
  public Flow<List<ExerciseEntity>> getExercisesByCategory(final long categoryId) {
    final String _sql = "SELECT * FROM exercises WHERE categoryId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, categoryId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercises"}, new Callable<List<ExerciseEntity>>() {
      @Override
      @NonNull
      public List<ExerciseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfEquipment = CursorUtil.getColumnIndexOrThrow(_cursor, "equipment");
          final int _cursorIndexOfPrimaryMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryMuscle");
          final int _cursorIndexOfSecondaryMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "secondaryMuscle");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTips = CursorUtil.getColumnIndexOrThrow(_cursor, "tips");
          final int _cursorIndexOfCommonMistakes = CursorUtil.getColumnIndexOrThrow(_cursor, "commonMistakes");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfVideoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "videoUrl");
          final int _cursorIndexOfCaloriesBurned = CursorUtil.getColumnIndexOrThrow(_cursor, "caloriesBurned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<ExerciseEntity> _result = new ArrayList<ExerciseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExerciseEntity _item;
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
            final String _tmpSecondaryMuscle;
            _tmpSecondaryMuscle = _cursor.getString(_cursorIndexOfSecondaryMuscle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpInstructions;
            _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            final String _tmpTips;
            _tmpTips = _cursor.getString(_cursorIndexOfTips);
            final String _tmpCommonMistakes;
            _tmpCommonMistakes = _cursor.getString(_cursorIndexOfCommonMistakes);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final String _tmpVideoUrl;
            _tmpVideoUrl = _cursor.getString(_cursorIndexOfVideoUrl);
            final int _tmpCaloriesBurned;
            _tmpCaloriesBurned = _cursor.getInt(_cursorIndexOfCaloriesBurned);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new ExerciseEntity(_tmpId,_tmpName,_tmpCategoryId,_tmpDifficulty,_tmpEquipment,_tmpPrimaryMuscle,_tmpSecondaryMuscle,_tmpDescription,_tmpInstructions,_tmpTips,_tmpCommonMistakes,_tmpImageUrl,_tmpVideoUrl,_tmpCaloriesBurned,_tmpIsFavorite);
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
  public ExerciseEntity getExerciseById(final long id) {
    final String _sql = "SELECT * FROM exercises WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
      final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
      final int _cursorIndexOfEquipment = CursorUtil.getColumnIndexOrThrow(_cursor, "equipment");
      final int _cursorIndexOfPrimaryMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryMuscle");
      final int _cursorIndexOfSecondaryMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "secondaryMuscle");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
      final int _cursorIndexOfTips = CursorUtil.getColumnIndexOrThrow(_cursor, "tips");
      final int _cursorIndexOfCommonMistakes = CursorUtil.getColumnIndexOrThrow(_cursor, "commonMistakes");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
      final int _cursorIndexOfVideoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "videoUrl");
      final int _cursorIndexOfCaloriesBurned = CursorUtil.getColumnIndexOrThrow(_cursor, "caloriesBurned");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final ExerciseEntity _result;
      if (_cursor.moveToFirst()) {
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
        final String _tmpSecondaryMuscle;
        _tmpSecondaryMuscle = _cursor.getString(_cursorIndexOfSecondaryMuscle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final String _tmpInstructions;
        _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
        final String _tmpTips;
        _tmpTips = _cursor.getString(_cursorIndexOfTips);
        final String _tmpCommonMistakes;
        _tmpCommonMistakes = _cursor.getString(_cursorIndexOfCommonMistakes);
        final String _tmpImageUrl;
        _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        final String _tmpVideoUrl;
        _tmpVideoUrl = _cursor.getString(_cursorIndexOfVideoUrl);
        final int _tmpCaloriesBurned;
        _tmpCaloriesBurned = _cursor.getInt(_cursorIndexOfCaloriesBurned);
        final boolean _tmpIsFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp != 0;
        _result = new ExerciseEntity(_tmpId,_tmpName,_tmpCategoryId,_tmpDifficulty,_tmpEquipment,_tmpPrimaryMuscle,_tmpSecondaryMuscle,_tmpDescription,_tmpInstructions,_tmpTips,_tmpCommonMistakes,_tmpImageUrl,_tmpVideoUrl,_tmpCaloriesBurned,_tmpIsFavorite);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<List<ExerciseEntity>> getFavoriteExercises() {
    final String _sql = "SELECT * FROM exercises WHERE isFavorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercises"}, new Callable<List<ExerciseEntity>>() {
      @Override
      @NonNull
      public List<ExerciseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfEquipment = CursorUtil.getColumnIndexOrThrow(_cursor, "equipment");
          final int _cursorIndexOfPrimaryMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryMuscle");
          final int _cursorIndexOfSecondaryMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "secondaryMuscle");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTips = CursorUtil.getColumnIndexOrThrow(_cursor, "tips");
          final int _cursorIndexOfCommonMistakes = CursorUtil.getColumnIndexOrThrow(_cursor, "commonMistakes");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfVideoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "videoUrl");
          final int _cursorIndexOfCaloriesBurned = CursorUtil.getColumnIndexOrThrow(_cursor, "caloriesBurned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<ExerciseEntity> _result = new ArrayList<ExerciseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExerciseEntity _item;
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
            final String _tmpSecondaryMuscle;
            _tmpSecondaryMuscle = _cursor.getString(_cursorIndexOfSecondaryMuscle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpInstructions;
            _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            final String _tmpTips;
            _tmpTips = _cursor.getString(_cursorIndexOfTips);
            final String _tmpCommonMistakes;
            _tmpCommonMistakes = _cursor.getString(_cursorIndexOfCommonMistakes);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final String _tmpVideoUrl;
            _tmpVideoUrl = _cursor.getString(_cursorIndexOfVideoUrl);
            final int _tmpCaloriesBurned;
            _tmpCaloriesBurned = _cursor.getInt(_cursorIndexOfCaloriesBurned);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new ExerciseEntity(_tmpId,_tmpName,_tmpCategoryId,_tmpDifficulty,_tmpEquipment,_tmpPrimaryMuscle,_tmpSecondaryMuscle,_tmpDescription,_tmpInstructions,_tmpTips,_tmpCommonMistakes,_tmpImageUrl,_tmpVideoUrl,_tmpCaloriesBurned,_tmpIsFavorite);
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
