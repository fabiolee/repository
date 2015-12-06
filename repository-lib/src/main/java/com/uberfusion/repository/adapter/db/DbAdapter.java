package com.uberfusion.repository.adapter.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.uberfusion.repository.object.xml.app.Apps;
import com.uberfusion.repository.object.xml.banner.Banner;
import com.uberfusion.repository.object.xml.reward.PrivilegeLocation;
import com.uberfusion.repository.object.xml.reward.PrivilegePartner;
import com.uberfusion.repository.object.xml.store.StoreLocator;
import com.uberfusion.repository.util.Constant;
import com.uberfusion.repository.util.Util;

import java.io.IOException;

public class DbAdapter {
    private final int DATABASE_VERSION = 1;
    private final String DATABASE_NAME = "data";

    private Context mCtx;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private class DatabaseHelper extends SQLiteOpenHelper {
        private final String LOG_TAG = getClass().getSimpleName();
        private Context mContext;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            StringBuffer sb = new StringBuffer();
            sb.append("create table " + Constant.Database.TABLE_CACHE + " (");
            sb.append(Constant.Key.ROWID + " integer primary key autoincrement");
            sb.append(", " + Constant.Key.MODULE + " text not null");
            sb.append(", " + Constant.Key.DATA + " text not null");
            sb.append(", " + Constant.Key.CLASS + " text not null");
            sb.append(", " + Constant.Key.DATE + " text not null");
            sb.append(");");
            db.execSQL(sb.toString());

            this.insertCache(db, Constant.Url.XML_BANNER, "banner.xml", Banner.class.getName());
            this.insertCache(db, Constant.Url.XML_REGION, "regions.xml", PrivilegeLocation.class.getName());
            this.insertCache(db, Constant.Url.XML_MERCHANT, "merchants.xml", PrivilegePartner.class.getName());
            this.insertCache(db, Constant.Url.XML_LOCATOR, "locator.xml", StoreLocator.class.getName());
            this.insertCache(db, Constant.Url.XML_APP, "app.xml", Apps.class.getName());

            sb = new StringBuffer();
            sb.append("create table " + Constant.Database.TABLE_ERRORLOG + " (");
            sb.append(Constant.Key.ROWID + " integer primary key autoincrement");
            sb.append(", " + Constant.Key.VERSION + " text");
            sb.append(", " + Constant.Key.TRANSACTIONID + " text not null");
            sb.append(", " + Constant.Key.REFERENCEID + " text");
            sb.append(", " + Constant.Key.ERRORCODE + " integer not null");
            sb.append(", " + Constant.Key.ERRORMESSAGE + " text");
            sb.append(", " + Constant.Key.ERRORCATEGORY + " text");
            sb.append(", " + Constant.Key.DATE + " text not null");
            sb.append(");");
            db.execSQL(sb.toString());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + Constant.Database.TABLE_CACHE);
            db.execSQL("DROP TABLE IF EXISTS " + Constant.Database.TABLE_ERRORLOG);
            onCreate(db);
        }

        private void insertCache(SQLiteDatabase db, String module, String xmlFileName, String className) {
            try {
                AssetManager mAssetManager = mContext.getAssets();
                String xmlData = Util.onPreXmlStringReplace(module, Util.outputStream(mAssetManager.open(xmlFileName)));
                String date = Constant.Value.XML_CACHE_DATE;

                Object[] bindArgs = new Object[] { module, xmlData, className, date };

                StringBuilder sb = new StringBuilder();
                sb.append("INSERT INTO " + Constant.Database.TABLE_CACHE + " (");
                sb.append(Constant.Key.ROWID);
                sb.append(", " + Constant.Key.MODULE);
                sb.append(", " + Constant.Key.DATA);
                sb.append(", " + Constant.Key.CLASS);
                sb.append(", " + Constant.Key.DATE);
                sb.append(") VALUES (NULL, ?, ?, ?, ?);");
                db.execSQL(sb.toString(), bindArgs);

                Log.d(LOG_TAG, "insertCache(db=[" + db + "], module=[" + module + "], xmlFileName=[" + xmlFileName + "], className=[" + className + "])");
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage());
            }
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     */
    @SuppressWarnings("javadoc")
    public DbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the database. If it cannot be opened, try to create a new instance
     * of the database. If it cannot be created, throw an exception to signal
     * the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException
     *             if the database could be neither opened or created
     */
    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public Cursor select(String table, String whereClause, String[] whereArgs) throws SQLException {
        Cursor mCursor = mDb.query(table, null, whereClause, whereArgs, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public int selectCount(String table, String whereClause, String[] whereArgs) {
        Cursor mCursor = this.select(table, whereClause, whereArgs);
        int count = mCursor.getCount();
        mCursor.close();
        return count;
    }

    public long insert(String table, ContentValues params) {
        return mDb.insert(table, null, params);
    }

    public boolean update(String table, ContentValues params, String whereClause, String[] whereArgs) {
        return mDb.update(table, params, whereClause, whereArgs) > 0;
    }

    public boolean delete(String table, String whereClause, String[] whereArgs) {
        return mDb.delete(table, whereClause, whereArgs) > 0;
    }
}
