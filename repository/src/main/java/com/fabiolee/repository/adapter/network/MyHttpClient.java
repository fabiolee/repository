package com.fabiolee.repository.adapter.network;

import android.content.Context;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

public class MyHttpClient extends DefaultHttpClient {
    final Context context;

    public MyHttpClient(HttpParams hparms, Context context) {
        super(hparms);
        this.context = context;
    }

    @Override
    protected ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

        // For Live
        registry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

        // http://blog.synyx.de/2010/06/android-and-self-signed-ssl-certificates/
        // http://stackoverflow.com/a/5605151
        return new ThreadSafeClientConnManager(getParams(), registry);
    }
}
