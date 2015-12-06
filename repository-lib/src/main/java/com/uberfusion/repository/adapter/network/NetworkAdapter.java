package com.uberfusion.repository.adapter.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.uberfusion.repository.util.Constant;
import com.uberfusion.repository.util.Util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Provides utility methods for communicating with the server.
 */
public class NetworkAdapter {
    private final String LOG_TAG = getClass().getSimpleName();

    private Context mContext;
    private DefaultHttpClient mHttpClient;

    public NetworkAdapter(Context context) {
        mContext = context;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    /**
     * Configures the httpClient to connect to the URL provided.
     */
    private void maybeCreateHttpClient() {
        if (mHttpClient == null) {
            HttpParams params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params, Constant.Time.THIRTY_SECONDS);
            HttpConnectionParams.setSoTimeout(params, Constant.Time.THIRTY_SECONDS);
            ConnManagerParams.setTimeout(params, Constant.Time.THIRTY_SECONDS);
            mHttpClient = new MyHttpClient(params, mContext.getApplicationContext());
        }
    }

    /**
     * Executes the network requests on a separate thread.
     * 
     * @param runnable
     *            The runnable instance containing network mOperations to be
     *            executed.
     * @return Thread
     */
    private Thread performOnBackgroundThread(final Runnable runnable) {
        final Thread t = new Thread() {
            @Override
            public void run() {
                runnable.run();
            }
        };
        t.start();
        return t;
    }

    public Bitmap httpGetBitmap(String url, File file) {
        if (this.isNetworkAvailable()) {
            final HttpGet get = new HttpGet(url);
            maybeCreateHttpClient();

            try {
                HttpResponse response = mHttpClient.execute(get);
                final int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    Log.e(LOG_TAG, "Error [" + statusCode + "] while retrieving HttpGet Bitmap from " + url);
                    return null;
                }

                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream is = null;
                    try {
                        is = entity.getContent();
                        OutputStream os = null;
                        try {
                            os = new FileOutputStream(file);
                            Util.copyStream(is, os);
                        } finally {
                            if (os != null) {
                                os.close();
                            }
                        }
                        return Util.decodeFile(file);
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                        entity.consumeContent();
                    }
                }
            } catch (Exception e) {
                get.abort();
                Log.e(LOG_TAG, "Error [" + e.getMessage() + "] while retrieving HttpGet Bitmap from " + url);
            }
        }

        return null;
    }

    public String httpGetString(String url) {
        return httpGetString(url, "");
    }

    public String httpGetString(String url, String reqString) {
        String resXml = null;

        if (this.isNetworkAvailable()) {
            HttpGet get = new HttpGet(url + reqString);
            maybeCreateHttpClient();

            try {
                HttpResponse resp = mHttpClient.execute(get);
                int statusCode = resp.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    resXml = EntityUtils.toString(resp.getEntity());
                } else {
                    Log.e(LOG_TAG, "Error [" + statusCode + "] while retrieving HttpGet String from " + url + ", reqString = " + reqString);
                }
            } catch (Exception e) {
                get.abort();
                Log.e(LOG_TAG, "Error [" + e.getMessage() + "] while retrieving HttpGet String from " + url + ", reqString = " + reqString);
            }
        }

        return resXml;
    }

    public String httpPostString(String url, String reqXml) throws UnsupportedEncodingException {
        String resXml = null;

        if (this.isNetworkAvailable()) {
            HttpPost post = new HttpPost(url);

            StringEntity se = new StringEntity(reqXml, HTTP.UTF_8);
            se.setContentType("text/xml");
            post.setEntity(se);

            maybeCreateHttpClient();

            try {
                HttpResponse resp = mHttpClient.execute(post);
                int statusCode = resp.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    resXml = EntityUtils.toString(resp.getEntity());
                } else {
                    Log.e(LOG_TAG, "Error [" + statusCode + "] while retrieving HttpPost String from " + url + ", reqXml = " + reqXml);
                }
            } catch (Exception e) {
                post.abort();
                Log.e(LOG_TAG, "Error [" + e.getMessage() + "] while retrieving HttpPost String from " + url + ", reqXml = " + reqXml);
            }
        }

        return resXml;
    }
}
