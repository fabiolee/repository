package com.fabiolee.repository;

import android.text.TextUtils;

import com.fabiolee.repository.object.xml.Callback;

/**
 * @author fabio.lee
 */
public final class Request<X> {
    public final String mUrl;
    public final Class<X> mObject;
    public final String mFileName;
    public final Callback<X> mLocalCallback;

    private Request(String mUrl, Class<X> mObject, String mFileName, Callback<X> mLocalCallback) {
        this.mUrl = mUrl;
        this.mObject = mObject;
        this.mFileName = mFileName;
        this.mLocalCallback = mLocalCallback;
    }

    /**
     * This instance is equal to all instances of {@link Request} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code mAnother} instance
     */
    @Override
    public boolean equals(Object mAnother) {
        return (this == mAnother) ||
                (mAnother instanceof Request && this.equalsTo((Request) mAnother));
    }

    private boolean equalsTo(Request mAnother) {
        return mUrl.equals(mAnother.mUrl)
                && mObject.equals(mAnother.mObject)
                && mFileName.equals(mAnother.mFileName)
                && mLocalCallback.equals(mAnother.mLocalCallback);
    }

    /**
     * Computes a hash code from attributes: {@code mUrl}, {@code mObject}, {@code mFileName}, {@code mLocalCallback}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        int h = 31;
        h = h * 17 + mUrl.hashCode();
        h = h * 17 + mObject.hashCode();
        h = h * 17 + mFileName.hashCode();
        h = h * 17 + mLocalCallback.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@link Request} with all non-generated and non-auxiliary attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "Request{"
                + "url=" + mUrl
                + ", object=" + mObject
                + ", fileName=" + mFileName
                + ", localCallback=" + mLocalCallback
                + "}";
    }

    public static final class Builder<X> {
        private String mUrl;
        private Class<X> mObject;
        private String mFileName;
        private Callback<X> mLocalCallback;

        /**
         * Do not allow outside package to access this method.
         *
         * @param mUrl    the url to download xml
         * @param mObject the class of the object for xml conversion
         */
        Builder(String mUrl, Class<X> mObject) {
            this.url(mUrl);
            this.object(mObject);
        }

        public Builder<X> url(String mUrl) {
            if (TextUtils.isEmpty(mUrl)) {
                throw new IllegalArgumentException("url may not be empty.");
            }
            this.mUrl = mUrl;
            return this;
        }

        public Builder<X> object(Class<X> mObject) {
            if (mObject == null) {
                throw new IllegalArgumentException("object may not be null.");
            }
            this.mObject = mObject;
            return this;
        }

        public Builder<X> fileName(String mFileName) {
            if (TextUtils.isEmpty(mFileName)) {
                throw new IllegalArgumentException("filename may not be empty.");
            }
            this.mFileName = mFileName;
            return this;
        }

        public Builder<X> localCallback(Callback<X> mLocalCallback) {
            if (mLocalCallback == null) {
                throw new IllegalArgumentException("local callback may not be null.");
            }
            this.mLocalCallback = mLocalCallback;
            return this;
        }

        public Request build() {
            return new Request<>(mUrl, mObject, mFileName, mLocalCallback);
        }
    }
}
