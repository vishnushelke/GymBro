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
import com.pamu.gymbro.data.local.converter.DateConverter;
import com.pamu.gymbro.data.local.entity.ProgressEntryEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProgressDao_Impl implements ProgressDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProgressEntryEntity> __insertionAdapterOfProgressEntryEntity;

  private final DateConverter __dateConverter = new DateConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteProgressEntry;

  public ProgressDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProgressEntryEntity = new EntityInsertionAdapter<ProgressEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `progress_entries` (`id`,`date`,`weight`,`chest`,`waist`,`hips`,`arms`,`thighs`,`bodyFat`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProgressEntryEntity entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __dateConverter.dateToTimestamp(entity.getDate());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        statement.bindDouble(3, entity.getWeight());
        statement.bindDouble(4, entity.getChest());
        statement.bindDouble(5, entity.getWaist());
        statement.bindDouble(6, entity.getHips());
        statement.bindDouble(7, entity.getArms());
        statement.bindDouble(8, entity.getThighs());
        statement.bindDouble(9, entity.getBodyFat());
        statement.bindString(10, entity.getNotes());
      }
    };
    this.__preparedStmtOfDeleteProgressEntry = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM progress_entries WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertProgressEntry(final ProgressEntryEntity entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfProgressEntryEntity.insert(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteProgressEntry(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteProgressEntry.acquire();
    int _argIndex = 1;
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
      __preparedStmtOfDeleteProgressEntry.release(_stmt);
    }
  }

  @Override
  public Flow<List<ProgressEntryEntity>> getAllProgressEntries() {
    final String _sql = "SELECT * FROM progress_entries ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"progress_entries"}, new Callable<List<ProgressEntryEntity>>() {
      @Override
      @NonNull
      public List<ProgressEntryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
          final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
          final int _cursorIndexOfHips = CursorUtil.getColumnIndexOrThrow(_cursor, "hips");
          final int _cursorIndexOfArms = CursorUtil.getColumnIndexOrThrow(_cursor, "arms");
          final int _cursorIndexOfThighs = CursorUtil.getColumnIndexOrThrow(_cursor, "thighs");
          final int _cursorIndexOfBodyFat = CursorUtil.getColumnIndexOrThrow(_cursor, "bodyFat");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<ProgressEntryEntity> _result = new ArrayList<ProgressEntryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProgressEntryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            final Date _tmp_1 = __dateConverter.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpDate = _tmp_1;
            }
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            final float _tmpChest;
            _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
            final float _tmpWaist;
            _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
            final float _tmpHips;
            _tmpHips = _cursor.getFloat(_cursorIndexOfHips);
            final float _tmpArms;
            _tmpArms = _cursor.getFloat(_cursorIndexOfArms);
            final float _tmpThighs;
            _tmpThighs = _cursor.getFloat(_cursorIndexOfThighs);
            final float _tmpBodyFat;
            _tmpBodyFat = _cursor.getFloat(_cursorIndexOfBodyFat);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new ProgressEntryEntity(_tmpId,_tmpDate,_tmpWeight,_tmpChest,_tmpWaist,_tmpHips,_tmpArms,_tmpThighs,_tmpBodyFat,_tmpNotes);
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
