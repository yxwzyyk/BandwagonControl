package xyz.yxwzyyk.bandwagoncontrol.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import xyz.yxwzyyk.bandwagoncontrol.app.MainApplication

/**
 * Created by yyk on 3/4/16.
 */
class DBHelper(content: Context = MainApplication.instance) : ManagedSQLiteOpenHelper(content, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "host.db"
        val DATABASE_VERSION = 1
        val instance: DBHelper by lazy { DBHelper() }
    }

    override fun onCreate(p0: SQLiteDatabase) {
        p0.createTable(HostTable.NAME, true,
                HostTable.ID to INTEGER + PRIMARY_KEY,
                HostTable.TITLE to TEXT,
                HostTable.VEID to TEXT,
                HostTable.KEY to TEXT)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.dropTable(HostTable.NAME, true)
        onCreate(p0)
    }

}

val Context.database: DBHelper
    get() = DBHelper.instance