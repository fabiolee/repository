package com.fabiolee.repository.adapter.cache;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.fabiolee.repository.adapter.db.DbAdapter;
import com.fabiolee.repository.object.db.DbCache;
import com.fabiolee.repository.util.Constant;
import com.fabiolee.repository.util.Util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class FileCache {
    private final String LOG_TAG = getClass().getSimpleName();

    private Context mContext;
    private DbAdapter mDb;
    private File cacheDir;

    public FileCache(Context context, DbAdapter db) {
        mContext = context;
        mDb = db;
        // Find the dir to save cached images
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = Util.getExternalStorageAppCacheDirectory(Constant.Type.REPOSITORY_PACKAGE);
        } else {
            cacheDir = context.getCacheDir();
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
    }

    public File getFile(String url) {
        // I identify images by hashcode. Not a perfect solution, good for the
        // demo.
        String filename = String.valueOf(url.hashCode());
        // Another possible solution (thanks to grantland)
        // String filename = URLEncoder.encode(url);
        return new File(cacheDir, filename);
    }

    public <X> X getXml(String id) {
        String whereClause = Constant.Key.MODULE + "=?";
        String[] whereArgs = new String[]{id};
        X xmlObject = null;

        Cursor cursor = null;
        String data = null;
        String className = null;
        String date = null;
        try {
            mDb.open();
            cursor = mDb.select(Constant.Database.TABLE_CACHE, whereClause, whereArgs);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    data = cursor.getString(cursor.getColumnIndex(Constant.Key.DATA));
                    className = cursor.getString(cursor.getColumnIndex(Constant.Key.CLASS));
                    date = cursor.getString(cursor.getColumnIndex(Constant.Key.DATE));
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (mDb != null) {
                mDb.close();
            }
        }

        if (!TextUtils.isEmpty(data) && !TextUtils.isEmpty(className) && !TextUtils.isEmpty(date)) {
            try {
                xmlObject = Util.convertXmlToObject(data, (Class<X>) Class.forName(className));
            } catch (ClassNotFoundException e) {
                String errorMessage = e.getMessage();
                Log.e(LOG_TAG, errorMessage);
                throw new AssertionError(e);
            }

            if (xmlObject != null) {
                // Convert UTC date to local date
                SimpleDateFormat formatter24 = new SimpleDateFormat(Constant.Format.DATETIME24, Locale.ENGLISH);
                formatter24.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date d;
                try {
                    d = formatter24.parse(date);
                } catch (ParseException e) {
                    Log.e(LOG_TAG, e.getMessage());
                    d = new Date();
                }

                SimpleDateFormat formatter12 = new SimpleDateFormat(Constant.Format.DATETIME12, Locale.ENGLISH);
                formatter12.setTimeZone(TimeZone.getDefault());
                String localDate = formatter12.format(d);
                //xmlObject.setLastUpdateDate(localDate);
            }
        }

        return xmlObject;
    }

    public void setDefaultXml(String module, String className, String fileName) {
        try {
            AssetManager mAssetManager = mContext.getAssets();
            String xmlData = Util.outputStream(mAssetManager.open(fileName));

            DbCache dbObject = new DbCache(module, xmlData, className);
            mDb.open();
            mDb.insert(Constant.Database.TABLE_CACHE, dbObject.toParams());

            Log.d(LOG_TAG, "setDefaultXml(module=[" + module + "], className=[" + className + "], fileName=[" + fileName + "])");
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
        } finally {
            if (mDb != null) {
                mDb.close();
            }
        }
    }

    public void setXml(DbCache dbObject) {
        String whereClause = Constant.Key.MODULE + "=?";
        String[] whereArgs = new String[]{dbObject.getModule()};

        try {
            mDb.open();
            int count = mDb.selectCount(Constant.Database.TABLE_CACHE, whereClause, whereArgs);
            if (count == 0) {
                mDb.insert(Constant.Database.TABLE_CACHE, dbObject.toParams());
            } else {
                mDb.update(Constant.Database.TABLE_CACHE, dbObject.toParams(), whereClause, whereArgs);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "" + e);
        } finally {
            if (mDb != null) {
                mDb.close();
            }
        }
    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        for (File f : files) {
            f.delete();
        }
    }
}