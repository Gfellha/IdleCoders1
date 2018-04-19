package edu.psu.pop5137.idlecoders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

/**
 * Created by ian on 3/24/18.
 */

public class IdleDB extends SQLiteOpenHelper {
    interface onDBReadyListener {
        void onReady(SQLiteDatabase db);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "idle.db";

    private static IdleDB db;

    private IdleDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized IdleDB getInstance(Context context) {
        if(db == null) {
            db = new IdleDB(context);
        }
        return db;
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE game (" +
                    "totalClicks INTEGER, " +
                    "totalEarned INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS game";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void asyncWritableDatabase(onDBReadyListener listener) {
        new OpenDbAsyncTask().execute(listener);
    }

    private static class OpenDbAsyncTask extends AsyncTask<onDBReadyListener, Void, SQLiteDatabase>{
        onDBReadyListener listener;

        @Override
        protected SQLiteDatabase doInBackground(onDBReadyListener... params) {
            listener = params[0];
            return IdleDB.db.getWritableDatabase();
        }

        @Override
        protected void onPostExecute(SQLiteDatabase db) {
            listener.onReady(db);
        }
    }
}
