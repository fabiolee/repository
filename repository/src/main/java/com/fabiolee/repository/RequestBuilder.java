package com.fabiolee.repository;

import com.fabiolee.repository.object.xml.Callback;

/**
 * @author fabio.lee
 */
public class RequestBuilder<X> {
    private final Repository mRepository;
    private final Request.Builder<X> mRequestBuilder;

    /**
     * Do not allow outside package to access this method.
     *
     * @param mRepository the {@link Repository} instance
     * @param mUrl        the url to download xml
     * @param mObject     the class of the object for xml conversion
     */
    RequestBuilder(Repository mRepository, String mUrl, Class<X> mObject) {
        this.mRepository = mRepository;
        this.mRequestBuilder = new Request.Builder<>(mUrl, mObject);
    }

    public RequestBuilder<X> defaultAsset(String mFileName) {
        mRequestBuilder.fileName(mFileName);
        return this;
    }

    public void callback(Callback<X> mLocalCallback) {
        Request mRequest = mRequestBuilder.localCallback(mLocalCallback)
                .build();
        mRepository.handleXml(mRequest);
    }
}
