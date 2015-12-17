package xyz.yxwzyyk.bandwagoncontrol.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yyk on 12/2/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "list.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 数据结构
         * _id : id
         * title : 标题
         * veid : 主机ID
         * key : 主机密钥
         */
        String sql = "CREATE TABLE IF NOT EXISTS list (_id INTEGER PRIMARY KEY ,title VARCHAR, veid VARCHAR, key VARCHAR)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
