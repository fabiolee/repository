package com.uberfusion.repository;

import android.os.Handler;
import android.text.TextUtils;

/**
 * @author fabio.lee
 */
public final class Request {
    public final String mUrl;
    public final Class<?> mObject;
    public final String mFileName;
    public final Handler mLocalHandler;
    public final Handler mRemoteHandler;

    private <X> Request(String mUrl, Class<X> mObject, String mFileName, Handler mLocalHandler, Handler mRemoteHandler) {
        this.mUrl = mUrl;
        this.mObject = mObject;
        this.mFileName = mFileName;
        this.mLocalHandler = mLocalHandler;
        this.mRemoteHandler = mRemoteHandler;
    }

    /**
     * This instance is equal to all instances of {@link Request} that have equal attribute values.
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
                && mLocalHandler.equals(mAnother.mLocalHandler)
                && mRemoteHandler.equals(mAnother.mRemoteHandler);
    }

    /**
     * Computes a hash code from attributes: {@code mUrl}, {@code mObject}, {@code mFileName}, {@code mLocalHandler}, {@code mRemoteHandler}.
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        int h = 31;
        h = h * 17 + mUrl.hashCode();
        h = h * 17 + mObject.hashCode();
        h = h * 17 + mFileName.hashCode();
        h = h * 17 + mLocalHandler.hashCode();
        h = h * 17 + mRemoteHandler.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@link Request} with all non-generated and non-auxiliary attribute values.
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "Request{"
                + "url=" + mUrl
                + ", object=" + mObject
                + ", fileName=" + mFileName
                + ", localHandler=" + mLocalHandler
                + ", remoteHandler=" + mRemoteHandler
                + "}";
    }

    public static final class Builder {
        private String mUrl;
        private Class<?> mObject;
        private String mFileName;
        private Handler mLocalHandler;
        private Handler mRemoteHandler;

        /**
         * Do not allow outside package to access this method.
         * @param mUrl the url to download xml
         * @param mObject the class of the object for xml conversion
         * @param <X> the object for xml conversion
         */
        <X> Builder(String mUrl, Class<X> mObject) {
            this.url(mUrl);
            this.object(mObject);
        }

        public Builder url(String mUrl) {
            if (TextUtils.isEmpty(mUrl)) {
                throw new IllegalArgumentException("url may not be empty.");
            }
            this.mUrl = mUrl;
            return this;
        }

        public <X> Builder object(Class<X> mObject) {
            if (mObject == null) {
                throw new IllegalArgumentException("object may not be null.");
            }
            this.mObject = mObject;
            return this;
        }

        public Builder fileName(String mFileName) {
            if (TextUtils.isEmpty(mFileName)) {
                throw new IllegalArgumentException("filename may not be empty.");
            }
            this.mFileName = mFileName;
            return this;
        }

        public Builder localHandler(Handler mLocalHandler) {
            if (mLocalHandler == null) {
                throw new IllegalArgumentException("local handler may not be null.");
            }
            this.mLocalHandler = mLocalHandler;
            return this;
        }

        public Builder remoteHandler(Handler mRemoteHandler) {
            if (mRemoteHandler == null) {
                throw new IllegalArgumentException("remote handler may not be null.");
            }
            this.mRemoteHandler = mRemoteHandler;
            return this;
        }

        public Request build() {
            return new Request(mUrl, mObject, mFileName, mLocalHandler, mRemoteHandler);
        }
    }
}
