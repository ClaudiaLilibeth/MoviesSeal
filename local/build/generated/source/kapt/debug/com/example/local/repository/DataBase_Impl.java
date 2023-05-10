package com.example.local.repository;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DataBase_Impl extends DataBase {
  private volatile GenresDao _genresDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `GenreEntity` (`idGenre` INTEGER NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`idGenre`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MovieEntity` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `GenresAndMoviesCross` (`id` INTEGER NOT NULL, `idGenre` INTEGER NOT NULL, PRIMARY KEY(`id`, `idGenre`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8eff98a7a4601b4906168486df57ea70')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `GenreEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `MovieEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `GenresAndMoviesCross`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsGenreEntity = new HashMap<String, TableInfo.Column>(2);
        _columnsGenreEntity.put("idGenre", new TableInfo.Column("idGenre", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGenreEntity.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGenreEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGenreEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGenreEntity = new TableInfo("GenreEntity", _columnsGenreEntity, _foreignKeysGenreEntity, _indicesGenreEntity);
        final TableInfo _existingGenreEntity = TableInfo.read(_db, "GenreEntity");
        if (! _infoGenreEntity.equals(_existingGenreEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "GenreEntity(com.example.local.entities.GenreEntity).\n"
                  + " Expected:\n" + _infoGenreEntity + "\n"
                  + " Found:\n" + _existingGenreEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsMovieEntity = new HashMap<String, TableInfo.Column>(2);
        _columnsMovieEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieEntity.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovieEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovieEntity = new TableInfo("MovieEntity", _columnsMovieEntity, _foreignKeysMovieEntity, _indicesMovieEntity);
        final TableInfo _existingMovieEntity = TableInfo.read(_db, "MovieEntity");
        if (! _infoMovieEntity.equals(_existingMovieEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "MovieEntity(com.example.local.entities.MovieEntity).\n"
                  + " Expected:\n" + _infoMovieEntity + "\n"
                  + " Found:\n" + _existingMovieEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsGenresAndMoviesCross = new HashMap<String, TableInfo.Column>(2);
        _columnsGenresAndMoviesCross.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGenresAndMoviesCross.put("idGenre", new TableInfo.Column("idGenre", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGenresAndMoviesCross = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGenresAndMoviesCross = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGenresAndMoviesCross = new TableInfo("GenresAndMoviesCross", _columnsGenresAndMoviesCross, _foreignKeysGenresAndMoviesCross, _indicesGenresAndMoviesCross);
        final TableInfo _existingGenresAndMoviesCross = TableInfo.read(_db, "GenresAndMoviesCross");
        if (! _infoGenresAndMoviesCross.equals(_existingGenresAndMoviesCross)) {
          return new RoomOpenHelper.ValidationResult(false, "GenresAndMoviesCross(com.example.local.entities.GenresAndMoviesCross).\n"
                  + " Expected:\n" + _infoGenresAndMoviesCross + "\n"
                  + " Found:\n" + _existingGenresAndMoviesCross);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8eff98a7a4601b4906168486df57ea70", "f3daa1e6fb19979220337f03107521d9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "GenreEntity","MovieEntity","GenresAndMoviesCross");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `GenreEntity`");
      _db.execSQL("DELETE FROM `MovieEntity`");
      _db.execSQL("DELETE FROM `GenresAndMoviesCross`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(GenresDao.class, GenresDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public GenresDao genresDao() {
    if (_genresDao != null) {
      return _genresDao;
    } else {
      synchronized(this) {
        if(_genresDao == null) {
          _genresDao = new GenresDao_Impl(this);
        }
        return _genresDao;
      }
    }
  }
}
