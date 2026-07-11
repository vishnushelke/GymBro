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
import com.pamu.gymbro.data.local.entity.DietPlanEntity;
import com.pamu.gymbro.data.local.entity.MealEntity;
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
public final class DietDao_Impl implements DietDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DietPlanEntity> __insertionAdapterOfDietPlanEntity;

  private final EntityInsertionAdapter<MealEntity> __insertionAdapterOfMealEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDietPlan;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFavoriteStatus;

  public DietDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDietPlanEntity = new EntityInsertionAdapter<DietPlanEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `diet_plans` (`id`,`name`,`goal`,`calories`,`protein`,`carbs`,`fats`,`isFavorite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DietPlanEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getGoal());
        statement.bindLong(4, entity.getCalories());
        statement.bindLong(5, entity.getProtein());
        statement.bindLong(6, entity.getCarbs());
        statement.bindLong(7, entity.getFats());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(8, _tmp);
      }
    };
    this.__insertionAdapterOfMealEntity = new EntityInsertionAdapter<MealEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `meals` (`id`,`dietPlanId`,`mealType`,`name`,`calories`,`protein`,`carbs`,`fats`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MealEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDietPlanId());
        statement.bindString(3, entity.getMealType());
        statement.bindString(4, entity.getName());
        statement.bindLong(5, entity.getCalories());
        statement.bindLong(6, entity.getProtein());
        statement.bindLong(7, entity.getCarbs());
        statement.bindLong(8, entity.getFats());
      }
    };
    this.__preparedStmtOfDeleteDietPlan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM diet_plans WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateFavoriteStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE diet_plans SET isFavorite = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insertDietPlan(final DietPlanEntity plan) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfDietPlanEntity.insertAndReturnId(plan);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMeals(final List<MealEntity> meals) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMealEntity.insert(meals);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteDietPlan(final long planId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDietPlan.acquire();
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
      __preparedStmtOfDeleteDietPlan.release(_stmt);
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
  public Flow<List<DietPlanEntity>> getAllDietPlans() {
    final String _sql = "SELECT * FROM diet_plans";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"diet_plans"}, new Callable<List<DietPlanEntity>>() {
      @Override
      @NonNull
      public List<DietPlanEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "goal");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfProtein = CursorUtil.getColumnIndexOrThrow(_cursor, "protein");
          final int _cursorIndexOfCarbs = CursorUtil.getColumnIndexOrThrow(_cursor, "carbs");
          final int _cursorIndexOfFats = CursorUtil.getColumnIndexOrThrow(_cursor, "fats");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<DietPlanEntity> _result = new ArrayList<DietPlanEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DietPlanEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpGoal;
            _tmpGoal = _cursor.getString(_cursorIndexOfGoal);
            final int _tmpCalories;
            _tmpCalories = _cursor.getInt(_cursorIndexOfCalories);
            final int _tmpProtein;
            _tmpProtein = _cursor.getInt(_cursorIndexOfProtein);
            final int _tmpCarbs;
            _tmpCarbs = _cursor.getInt(_cursorIndexOfCarbs);
            final int _tmpFats;
            _tmpFats = _cursor.getInt(_cursorIndexOfFats);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new DietPlanEntity(_tmpId,_tmpName,_tmpGoal,_tmpCalories,_tmpProtein,_tmpCarbs,_tmpFats,_tmpIsFavorite);
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
  public Flow<DietPlanEntity> getDietPlanById(final long id) {
    final String _sql = "SELECT * FROM diet_plans WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"diet_plans"}, new Callable<DietPlanEntity>() {
      @Override
      @Nullable
      public DietPlanEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "goal");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfProtein = CursorUtil.getColumnIndexOrThrow(_cursor, "protein");
          final int _cursorIndexOfCarbs = CursorUtil.getColumnIndexOrThrow(_cursor, "carbs");
          final int _cursorIndexOfFats = CursorUtil.getColumnIndexOrThrow(_cursor, "fats");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final DietPlanEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpGoal;
            _tmpGoal = _cursor.getString(_cursorIndexOfGoal);
            final int _tmpCalories;
            _tmpCalories = _cursor.getInt(_cursorIndexOfCalories);
            final int _tmpProtein;
            _tmpProtein = _cursor.getInt(_cursorIndexOfProtein);
            final int _tmpCarbs;
            _tmpCarbs = _cursor.getInt(_cursorIndexOfCarbs);
            final int _tmpFats;
            _tmpFats = _cursor.getInt(_cursorIndexOfFats);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _result = new DietPlanEntity(_tmpId,_tmpName,_tmpGoal,_tmpCalories,_tmpProtein,_tmpCarbs,_tmpFats,_tmpIsFavorite);
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
  public Flow<List<MealEntity>> getMealsForDietPlan(final long planId) {
    final String _sql = "SELECT * FROM meals WHERE dietPlanId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, planId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"meals"}, new Callable<List<MealEntity>>() {
      @Override
      @NonNull
      public List<MealEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDietPlanId = CursorUtil.getColumnIndexOrThrow(_cursor, "dietPlanId");
          final int _cursorIndexOfMealType = CursorUtil.getColumnIndexOrThrow(_cursor, "mealType");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfProtein = CursorUtil.getColumnIndexOrThrow(_cursor, "protein");
          final int _cursorIndexOfCarbs = CursorUtil.getColumnIndexOrThrow(_cursor, "carbs");
          final int _cursorIndexOfFats = CursorUtil.getColumnIndexOrThrow(_cursor, "fats");
          final List<MealEntity> _result = new ArrayList<MealEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MealEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDietPlanId;
            _tmpDietPlanId = _cursor.getLong(_cursorIndexOfDietPlanId);
            final String _tmpMealType;
            _tmpMealType = _cursor.getString(_cursorIndexOfMealType);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpCalories;
            _tmpCalories = _cursor.getInt(_cursorIndexOfCalories);
            final int _tmpProtein;
            _tmpProtein = _cursor.getInt(_cursorIndexOfProtein);
            final int _tmpCarbs;
            _tmpCarbs = _cursor.getInt(_cursorIndexOfCarbs);
            final int _tmpFats;
            _tmpFats = _cursor.getInt(_cursorIndexOfFats);
            _item = new MealEntity(_tmpId,_tmpDietPlanId,_tmpMealType,_tmpName,_tmpCalories,_tmpProtein,_tmpCarbs,_tmpFats);
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
  public Flow<List<DietPlanEntity>> getFavoriteDietPlans() {
    final String _sql = "SELECT * FROM diet_plans WHERE isFavorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"diet_plans"}, new Callable<List<DietPlanEntity>>() {
      @Override
      @NonNull
      public List<DietPlanEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "goal");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfProtein = CursorUtil.getColumnIndexOrThrow(_cursor, "protein");
          final int _cursorIndexOfCarbs = CursorUtil.getColumnIndexOrThrow(_cursor, "carbs");
          final int _cursorIndexOfFats = CursorUtil.getColumnIndexOrThrow(_cursor, "fats");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<DietPlanEntity> _result = new ArrayList<DietPlanEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DietPlanEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpGoal;
            _tmpGoal = _cursor.getString(_cursorIndexOfGoal);
            final int _tmpCalories;
            _tmpCalories = _cursor.getInt(_cursorIndexOfCalories);
            final int _tmpProtein;
            _tmpProtein = _cursor.getInt(_cursorIndexOfProtein);
            final int _tmpCarbs;
            _tmpCarbs = _cursor.getInt(_cursorIndexOfCarbs);
            final int _tmpFats;
            _tmpFats = _cursor.getInt(_cursorIndexOfFats);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new DietPlanEntity(_tmpId,_tmpName,_tmpGoal,_tmpCalories,_tmpProtein,_tmpCarbs,_tmpFats,_tmpIsFavorite);
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
