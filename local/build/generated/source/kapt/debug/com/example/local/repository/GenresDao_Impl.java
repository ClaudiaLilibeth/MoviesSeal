package com.example.local.repository;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.local.entities.GenreEntity;
import com.example.local.entities.GenresAndMoviesCross;
import com.example.local.entities.MovieEntity;
import com.example.local.entities.MoviesWithGenre;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GenresDao_Impl implements GenresDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GenreEntity> __insertionAdapterOfGenreEntity;

  private final EntityInsertionAdapter<MovieEntity> __insertionAdapterOfMovieEntity;

  private final EntityInsertionAdapter<GenresAndMoviesCross> __insertionAdapterOfGenresAndMoviesCross;

  public GenresDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGenreEntity = new EntityInsertionAdapter<GenreEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `GenreEntity` (`idGenre`,`name`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GenreEntity value) {
        stmt.bindLong(1, value.getIdGenre());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__insertionAdapterOfMovieEntity = new EntityInsertionAdapter<MovieEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `MovieEntity` (`id`,`name`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__insertionAdapterOfGenresAndMoviesCross = new EntityInsertionAdapter<GenresAndMoviesCross>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `GenresAndMoviesCross` (`id`,`idGenre`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GenresAndMoviesCross value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getIdGenre());
      }
    };
  }

  @Override
  public Object insertGenre(final GenreEntity genre,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGenreEntity.insert(genre);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public void insertMovie(final List<MovieEntity> movies) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMovieEntity.insert(movies);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Object insertGenreToMovie(final List<GenresAndMoviesCross> moviesWithGenre,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGenresAndMoviesCross.insert(moviesWithGenre);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getMoviesByGenre(final int id,
      final Continuation<? super MoviesWithGenre> continuation) {
    final String _sql = "SELECT *FROM MovieEntity  WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<MoviesWithGenre>() {
      @Override
      public MoviesWithGenre call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final LongSparseArray<ArrayList<GenreEntity>> _collectionGenres = new LongSparseArray<ArrayList<GenreEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey = _cursor.getLong(_cursorIndexOfId);
              ArrayList<GenreEntity> _tmpGenresCollection = _collectionGenres.get(_tmpKey);
              if (_tmpGenresCollection == null) {
                _tmpGenresCollection = new ArrayList<GenreEntity>();
                _collectionGenres.put(_tmpKey, _tmpGenresCollection);
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipGenreEntityAscomExampleLocalEntitiesGenreEntity(_collectionGenres);
            final MoviesWithGenre _result;
            if(_cursor.moveToFirst()) {
              final MovieEntity _tmpMovie;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              _tmpMovie = new MovieEntity(_tmpId,_tmpName);
              ArrayList<GenreEntity> _tmpGenresCollection_1 = null;
              final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpGenresCollection_1 = _collectionGenres.get(_tmpKey_1);
              if (_tmpGenresCollection_1 == null) {
                _tmpGenresCollection_1 = new ArrayList<GenreEntity>();
              }
              _result = new MoviesWithGenre(_tmpMovie,_tmpGenresCollection_1);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipGenreEntityAscomExampleLocalEntitiesGenreEntity(
      final LongSparseArray<ArrayList<GenreEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<GenreEntity>> _tmpInnerMap = new LongSparseArray<ArrayList<GenreEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipGenreEntityAscomExampleLocalEntitiesGenreEntity(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<GenreEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipGenreEntityAscomExampleLocalEntitiesGenreEntity(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `GenreEntity`.`idGenre` AS `idGenre`,`GenreEntity`.`name` AS `name`,_junction.`id` FROM `GenresAndMoviesCross` AS _junction INNER JOIN `GenreEntity` ON (_junction.`idGenre` = `GenreEntity`.`idGenre`) WHERE _junction.`id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = 2; // _junction.id;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfIdGenre = 0;
      final int _cursorIndexOfName = 1;
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        ArrayList<GenreEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final GenreEntity _item_1;
          final int _tmpIdGenre;
          _tmpIdGenre = _cursor.getInt(_cursorIndexOfIdGenre);
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          _item_1 = new GenreEntity(_tmpIdGenre,_tmpName);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
