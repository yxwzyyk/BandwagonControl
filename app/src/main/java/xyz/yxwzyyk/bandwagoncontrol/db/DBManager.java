package xyz.yxwzyyk.bandwagoncontrol.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyk on 12/2/15.
 */
public class DBManager {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    private static final String TB_NAME = "list";

    public DBManager(Context context) {
        mDBHelper = new DBHelper(context);
        mDB = mDBHelper.getWritableDatabase();
    }

    /**
     * 关闭数据库
     */
    public void closeDB() {
        mDB.close();
    }

    /**
     * 插入数据
     *
     * @param host
     */
    public void insert(Host host) {
        ContentValues c = new ContentValues();
        c.put("title", host.title);
        c.put("veid", host.veid);
        c.put("key", host.key);
        mDB.insert(TB_NAME, null, c);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void delete(int id) {
        mDB.delete(TB_NAME, "_id=" + id, null);
    }

    /**
     * 更新数据
     *
     * @param host
     */
    public void update(Host host) {
        if (host._id == -1) return;

        ContentValues c = new ContentValues();

        c.put("title", host.title);
        c.put("veid", host.veid);
        c.put("key", host.key);

        mDB.update(TB_NAME, c, "_id=" + host._id, null);
    }

    /**
     * 获取记录总数
     *
     * @return
     */
    public int queryCount() {
        int count;
        Cursor c = mDB.rawQuery("SELECT * FROM " + TB_NAME, null);
        count = c.getCount();
        return count;
    }

    /**
     * 获取数据返回list
     *
     * @return 数据list
     */
    public List<Host> query() {
        ArrayList<Host> hosts = new ArrayList<>();
        Cursor c;
        c = mDB.rawQuery("SELECT * FROM list ORDER BY _id DESC", null);
        while (c.moveToNext()) {
            Host host = new Host();
            host._id = c.getInt(c.getColumnIndex("_id"));
            host.title = c.getString(c.getColumnIndex("title"));
            host.veid = c.getString(c.getColumnIndex("veid"));
            host.key = c.getString(c.getColumnIndex("key"));
            hosts.add(host);
        }
        c.close();
        return hosts;
    }
}
