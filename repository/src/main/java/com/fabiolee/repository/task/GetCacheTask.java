package com.fabiolee.repository.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.fabiolee.repository.adapter.cache.CacheLoader;
import com.fabiolee.repository.object.xml.BaseObject;

public class GetCacheTask extends AsyncTask<String[], Void, BaseObject[]> {
    private CacheLoader mCache;
    private Handler mHandler;

    public GetCacheTask(CacheLoader cache, Handler handler) {
        super();
        mCache = cache;
        mHandler = handler;
    }

    @Override
    protected BaseObject[] doInBackground(String[]... param) {
        BaseObject[] result = new BaseObject[param.length];
        for (int i = 0; i < param.length; i++) {
            if ((param[i] == null) || (param[i].length != 3)) {
                result[i] = null;
            } else {
                // Get Xml, paramLength = 3
                result[i] = mCache.getXml(param[i][0], param[i][1], param[i][2]);
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
