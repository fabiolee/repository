package com.uberfusion.repository.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.uberfusion.repository.adapter.network.NetworkAdapter;

public class DownloadXmlTask extends AsyncTask<String, Void, String[]> {
	private Handler mHandler;
	private NetworkAdapter mNetwork;

	public DownloadXmlTask(Handler handler, NetworkAdapter network) {
		super();
		mHandler = handler;
		mNetwork = network;
	}

	@Override
	protected String[] doInBackground(String... urls) {
		String[] result = new String[urls.length];
		for (int i = 0; i < urls.length; i++) {
			result[i] = mNetwork.httpGetString(urls[i]);
		}
		return result;
	}

	@Override
	protected void onPostExecute(String[] result) {
		Message mMessage = new Message();
		mMessage.obj = result;
		mHandler.sendMessage(mMessage);
	}

	@Override
	protected void onPreExecute() {
	}
}
