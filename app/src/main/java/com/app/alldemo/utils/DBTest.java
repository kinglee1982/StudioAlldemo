package com.app.alldemo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.List;

public class DBTest extends SQLiteOpenHelper {
    private Context context;
    private static final int VERSION = 1;
    private static final String SQLite_Name = "MusicwaveDB";
    private static final String music_table = "music_table";

    /**
     * 表的id
     */
    private static final String id = "id";
    private static final String music_id = "music_id";
    private static final String music_source = "music_source";

    public DBTest(Context context) {
        super(context, SQLite_Name, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String musicSql = "create table " + music_table
                + "("
                + id + " integer primary key autoincrement,"
                + music_id + " TEXT,"
                + music_source + " integer"
                + ")";
        db.execSQL(musicSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * 根据两个字段查询
     *
     * @param music_idValue
     * @param source
     * @return
     */
    public boolean isMusicexit(String music_idValue, int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = music_id + "='" + music_idValue + "'" + " and " + music_source + "=" + source;
        Cursor nCursor = null;
        nCursor = db.query(music_table, null, where,
                null, null, null, null);
        if (nCursor != null && nCursor.getCount() > 0) {
            nCursor.close();
            db.close();
            return true;
        } else {
            if (nCursor != null) {
                nCursor.close();
            }
            db.close();
            return false;
        }
    }

    /**
     * 根据一个字段查询
     *
     * @param music_idValue
     * @return
     */
    public boolean isexit(String music_idValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = music_id + "='" + music_idValue + "'";
        Cursor nCursor = null;
        nCursor = db.query(music_table, null, where, null, null, null, null);
        if (nCursor != null && nCursor.getCount() > 0) {
            nCursor.close();
            db.close();
            return true;
        } else {
            if (nCursor != null) {
                nCursor.close();
            }
            db.close();
            return false;
        }
    }

    public void addMusicTable(String musicId, int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(music_id, musicId);
            cv.put(music_source, source);
            db.insert(music_table, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }

    public void addMusicTable2(String musicId, int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db.isOpen()) {
                Object[] obj = {musicId, source};
                String sql = "insert into " + music_table + " (" + music_id + "," + music_source + "," + ") ";
                db.execSQL(sql, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }

    public void updataMusicTable(int idValue, String musicId, int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String where = id + " = ?";
            String[] whereValue = {idValue + ""};
            ContentValues cv = new ContentValues();
            cv.put(music_id, musicId);
            cv.put(music_source, source);
            db.update(music_table, cv, where, whereValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
    }

    public void getMusilocalList(int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "";
        sql = "select * from " + music_table + " where " + music_source + "=" + source;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String musicIdValue = cursor.getString(cursor.getColumnIndex(music_id));
        }
        cursor.close();
        db.close();
    }

    /**
     * 获取表的id
     *
     * @param musicIdValue
     * @param source
     * @return
     */
    public int getMusicTableId(String musicIdValue, int source) {
        Cursor cursor = null;
        int key = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String where = music_id + "='" + musicIdValue + "'" + " and " + music_source + "=" + source;
        cursor = db.query(music_table, null, where,
                null, null, null, null);
        if (cursor.moveToNext()) {
            key = cursor.getInt(cursor.getColumnIndex(id));
        }
        cursor.close();
        db.close();
        return key;
    }

    /**
     * 根据两个字段删除
     *
     * @param music_idValue
     * @param source
     */
    public void deleteMusic(String music_idValue, int source) {
        if (isMusicexit(music_idValue, source)) {
            SQLiteDatabase db = this.getWritableDatabase();
            String where = music_id + " = ?" + " and " + music_source + "=?";
            String[] whereValue = new String[]{music_idValue, source + ""};
            db.delete(music_table, where, whereValue);
            db.close();
        }
    }

    /**
     * 根据一个字段删除
     *
     * @param source
     */
    public void deleteMusic(int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = music_source + "=?";
        String[] whereValue = new String[]{source + ""};
        db.delete(music_table, where, whereValue);
        db.close();
    }

    /**
     * 删除表
     */
    public void deleteMusicTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from  " + music_table;
        db.execSQL(sql);
        db.close();
    }

    /**
     * @return 音乐总条数
     */
    public long getMusicCount(int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select count(*) from " + music_table + " where " + music_source + "=" + source;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }

    /**
     * 模糊搜索,歌手名
     */
    public void getkeyList_music_author(String searchValue, int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + music_table + " where " + music_id + " like'%" + searchValue + "%'" + " and " + music_source + "=" + source;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String musicIdValue = cursor.getString(cursor.getColumnIndex(music_id));
        }
        cursor.close();
        db.close();
    }

    /**
     * 排序-降序
     *
     * @param source
     */
    public void getMusicHistory(int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "";
        sql = "select * from " + music_table + " where " + music_source + "=" + source + " order by id DESC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        db.close();
    }

    /**
     * 排序-升序
     *
     * @param source
     */
    public void getMusicPaixu(int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "";
        sql = "select * from " + music_table + " where " + music_source + "=" + source + " order by id ASC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        db.close();
    }

    /**
     * 事务的设置
     *
     * @param musicId
     * @param source
     */
    public void addMusicTabletanc(String musicId, int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        //开启事务
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(music_id, musicId);
            cv.put(music_source, source);
            db.insert(music_table, null, cv);
            //设置事务标志为成功，当结束事务时就会提交事务
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            if (null != db) {
                db.close();
                //结束事务
            }
        }
    }

    /**
     * 查询某个字段的一部分值
     *
     * @param src
     * @param source
     */
    public void getNeedAddMusicLists(List<String> src, int source) {
        SQLiteDatabase db = this.getWritableDatabase();
        String ids = "";
        for (int i = 0; i < src.size(); i++) {
            String id = src.get(i);
            if (i == 0) {
                ids += id;
            } else {
                ids += "," + id;
            }
        }
        String sql = "select * from " + music_table
                + " where " + music_id + " in(" + ids + ")"
                + " and " + music_source + "=" + source;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

        }
    }

    /**
     * 增加多条数据
     *
     * @param playlistIdValue
     * @param netMusicModels
     * @param source
     * @param auth_idValue
     */
    public void addPlaylistMusicTable(String playlistIdValue, List<String> netMusicModels,
                                      int source, String auth_idValue) {
        synchronized (DBTest.class) {//同步锁
            SQLiteDatabase db = this.getWritableDatabase();
            try {
                db.beginTransaction();
                for (String netMusicModel : netMusicModels) {
                    ContentValues cv = new ContentValues();
                    cv.put(music_source, source);
                    db.insert(music_table, null, cv);
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
                if (null != db) {
                    db.close();
                }
            }
        }
    }

    /**
     * 更改表名
     *
     * @return
     */
    public boolean updataTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db.isOpen()) {
                String sql = "ALTER TABLE TB_SHENTI_QINGBAO RENAME TO BODY_INFO";
                db.execSQL(sql);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    /**
     * 增加字段
     *
     * @return
     */
    public boolean addTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db.isOpen()) {
                String sql = "ALTER TABLE 表名  ADD COLUMN 新的字段名 BLOB(类型)";
                db.execSQL(sql);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    /**
     * 删除数据库
     */
    public void deleteDB() {
        boolean flag = context.deleteDatabase(SQLite_Name);
    }

    public void isexitDB() {
        File file = new File(SQLite_Name);
    }

    /**
     * 方法2：检查表中某列是否存在
     *
     * @param db
     * @param tableName  表名
     * @param columnName 列名
     * @return
     */
    private boolean checkColumnExists2(SQLiteDatabase db, String tableName
            , String columnName) {
        boolean result = false;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("select * from sqlite_master where name = ? and sql like ?"
                    , new String[]{tableName, "%" + columnName + "%"});
            result = null != cursor && cursor.moveToFirst();
        } catch (Exception e) {
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return result;
    }

    /**
     * 方法1：检查某表列是否存在
     *
     * @param db
     * @param tableName  表名
     * @param columnName 列名
     * @return
     */
    private boolean checkColumnExist1(SQLiteDatabase db, String tableName
            , String columnName) {
        boolean result = false;
        Cursor cursor = null;
        try {
            //查询一行
            cursor = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 0"
                    , null);
            result = cursor != null && cursor.getColumnIndex(columnName) != -1;
        } catch (Exception e) {
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return result;
    }
}
