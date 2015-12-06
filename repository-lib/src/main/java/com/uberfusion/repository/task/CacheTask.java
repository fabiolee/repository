package com.uberfusion.repository.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.uberfusion.repository.adapter.cache.CacheLoader;
import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Util;

public class CacheTask extends AsyncTask<String[], Void, BaseObject[]> {
    private final String LOG_TAG = getClass().getSimpleName();

    private Context mContext;
    private CacheLoader mCache;
    private Handler mHandler;

    public CacheTask(Context context, CacheLoader cache, Handler handler) {
        super();
        mContext = context;
        mCache = cache;
        mHandler = handler;
    }

    @Override
    protected BaseObject[] doInBackground(String[]... param) {
        BaseObject[] result = new BaseObject[param.length];
        for (int i = 0; i < param.length; i++) {
            if (param[i] == null) {
                result[i] = null;
            } else if (param[i].length == 3) {
                // Set Xml, paramLength = 3
                try {
                    result[i] = mCache.setXml(param[i][0], param[i][1], (Class<? extends BaseObject>) Class.forName(param[i][2]));
                } catch (ClassNotFoundException e) {
                    String errorMessage = e.getMessage();
                    Log.e(LOG_TAG, errorMessage);
                    Util.showErrorNotification(mContext, errorMessage);
                    throw new AssertionError(e);
                }
            } else {
                // Get Xml, paramLength = 1
                result[i] = mCache.getXml(param[i][0]);
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(BaseObject[] result) {
        Message mMessage = new Message();
        mMessage.obj = result;
        mHandler.sendMessage(mMessage);
    }

    @Override
    protected void onPreExecute() {
    }
}
