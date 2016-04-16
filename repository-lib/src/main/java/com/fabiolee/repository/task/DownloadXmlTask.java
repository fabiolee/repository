package com.fabiolee.repository.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.fabiolee.repository.Request;
import com.fabiolee.repository.Response;
import com.fabiolee.repository.adapter.network.NetworkAdapter;

public class DownloadXmlTask extends AsyncTask<Request, Void, Response[]> {
    private Handler mHandler;
    private NetworkAdapter mNetwork;

    public DownloadXmlTask(Handler mHandler, NetworkAdapter mNetwork) {
        super();
        this.mHandler = mHandler;
        this.mNetwork = mNetwork;
    }

    @Override
    protected Response[] doInBackground(Request... mRequests) {
        Response[] mResult = new Response[mRequests.length];
        for (int i = 0; i < mRequests.length; i++) {
            mResult[i] = new Response.Builder()
                    .request(mRequests[i])
                    .resXml(mNetwork.httpGetString(mRequests[i].mUrl))
                    .build();
        }
        return mResult;
    }

    @Override
    protected void onPostExecute(Response[] mResult) {
        Message mMessage = new Message();
        mMessage.obj = mResult;
        mHandler.sendMessage(mMessage);
    }

    @Override
    protected void onPreExecute() {
    }
}
