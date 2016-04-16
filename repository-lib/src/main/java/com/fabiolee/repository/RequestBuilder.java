package com.fabiolee.repository;

import android.os.Handler;

/**
 * @author fabio.lee
 */
public class RequestBuilder {
    private final Repository mRepository;
    private final Request.Builder mRequestBuilder;

    /**
     * Do not allow outside package to access this method.
     *
     * @param mRepository the {@link Repository} instance
     * @param mUrl        the url to download xml
     * @param mObject     the class of the object for xml conversion
     * @param <X>         the object for xml conversion
     */
    <X> RequestBuilder(Repository mRepository, String mUrl, Class<X> mObject) {
        this.mRepository = mRepository;
        this.mRequestBuilder = new Request.Builder(mUrl, mObject);
    }

    public RequestBuilder defaultCache(String mFileName) {
        mRequestBuilder.fileName(mFileName);
        return this;
    }

    public void handle(Handler mLocalHandler, Handler mRemoteHandler) {
        Request mRequest = mRequestBuilder.localHandler(mLocalHandler)
                .remoteHandler(mRemoteHandler)
                .build();
        mRepository.handleXml(mRequest);
    }
}
