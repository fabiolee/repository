package com.fabiolee.repository.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.fabiolee.repository.adapter.cache.CacheLoader;

public class SetCacheTask<X> extends AsyncTask<String[], Void, X[]> {
    private final String LOG_TAG = getClass().getSimpleName();

    private Context mContext;
    private CacheLoader mCache;
    private Handler mHandler;

    public SetCacheTask(Context context, CacheLoader cache, Handler handler) {
        super();
        mContext = context;
        mCache = cache;
        mHandler = handler;
    }

    @Override
    protected X[] doInBackground(String[]... param) {
        X[] result = (X[]) new Object[param.length];
        for (int i = 0; i < param.length; i++) {
            if ((param[i] == null) || (param[i].length != 3)) {
                result[i] = null;
            } else {
                // Set Xml, paramLength = 3
                try {
                    result[i] = mCache.setXml(param[i][0], param[i][1], (Class<X>) Class.forName(param[i][2]));
                } catch (ClassNotFoundException e) {
                    String errorMessage = e.getMessage();
                    Log.e(LOG_TAG, errorMessage);
                    throw new AssertionError(e);
                }
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(X[] result) {
        Message mMessage = new Message();
        mMessage.obj = result;
        mHandler.sendMessage(mMessage);
    }

    @Override
    protected void onPreExecute() {
    }
}
