package com.fabiolee.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;

import com.fabiolee.repository.adapter.cache.CacheLoader;
import com.fabiolee.repository.adapter.cache.MemoryCache;
import com.fabiolee.repository.adapter.db.DbAdapter;
import com.fabiolee.repository.adapter.network.NetworkAdapter;
import com.fabiolee.repository.object.xml.Callback;
import com.fabiolee.repository.task.DownloadXmlTask;
import com.fabiolee.repository.task.GetCacheTask;
import com.fabiolee.repository.task.SetCacheTask;

/**
 * @author fabio.lee
 */
public final class Repository {
    private static volatile Repository sSingleton = null;

    private final Context mContext;
    private final DbAdapter mDb;
    private final NetworkAdapter mNetwork;
    private final CacheLoader mCache;

    private Repository(final Context mContext) {
        this.mContext = mContext;
        this.mDb = new DbAdapter(mContext);
        this.mNetwork = new NetworkAdapter(mContext);
        this.mCache = new CacheLoader(mContext, mDb, mNetwork);
    }

    public MemoryCache cache() {
        return mCache.getMemoryCache();
    }

    public void cache(MemoryCache mMemoryCache) {
        if (mMemoryCache != null) {
            mCache.setMemoryCache(mMemoryCache);
        }
    }

    public void loadImage(String mUrl, ImageView mImageView) {
        mCache.setImage(mUrl, mImageView);
    }

    public <X> RequestBuilder<X> loadXml(String mUrl, Class<X> mObject) {
        return new RequestBuilder<>(this, mUrl, mObject);
    }

    /**
     * The global default {@link Repository} instance.
     */
    public static Repository with(Context mContext) {
        if (sSingleton == null) {
            synchronized (Repository.class) {
                if (sSingleton == null) {
                    sSingleton = new Builder(mContext).build();
                }
            }
        }
        return sSingleton;
    }

    /**
     * Do not allow outside package to access this method.
     *
     * @param mRequest the {@link Request} instance
     */
    void handleXml(Request mRequest) {
        if (mCache.isEmptyXml()) {
            this.onDownloadXml(mRequest);
        } else {
            this.onPreRefreshXml(mRequest);
        }
    }

    private <X> void onDownloadXml(Request mRequest) {
        DownloadXmlTask mDownloadTask = new DownloadXmlTask(mNetwork, new Callback<Response[]>() {
            @Override
            public void onResponse(Response[] mObject) {
                final Response mResponse = mObject[0];
                if (TextUtils.isEmpty(mResponse.mResXml)) {
                    onPreRefreshXml(mResponse.mRequest);
                } else {
                    String[][] mCacheTaskParam = new String[][]{{mResponse.mRequest.mUrl, mResponse.mResXml, mResponse.mRequest.mObject.getName()}};
                    Handler mCacheHandler = new Handler() {
                        @Override
                        public void handleMessage(Message mMessage) {
                            X[] resObject = (X[]) mMessage.obj;
                            if (resObject[0] == null) {
                                // If fail to convert resXml to resObject from remote version, try to use local version.
                                onPreRefreshXml(mResponse.mRequest);
                            } else {
                                onPostRefreshXml(mResponse.mRequest.mLocalCallback, resObject[0]);
                            }
                        }
                    };
                    SetCacheTask<X> mCacheTask = new SetCacheTask<>(mContext, mCache, mCacheHandler);
                    mCacheTask.execute(mCacheTaskParam);
                }
            }
        });
        mDownloadTask.execute(mRequest);
    }

    private <X> void onPostRefreshXml(Callback<X> mLocalCallback, X mObject) {
        if (mObject != null) {
            mLocalCallback.onResponse(mObject);
        }
    }

    private <X> void onPreRefreshXml(final Request mRequest) {
        if (mCache.containsXml(mRequest.mUrl)) {
            this.onPostRefreshXml(mRequest.mLocalCallback, mCache.getXml(mRequest.mUrl, mRequest.mObject.getName(), mRequest.mFileName));
        } else {
            String[][] mCacheTaskParam = new String[][]{{mRequest.mUrl, mRequest.mObject.getName(), mRequest.mFileName}};
            Handler mCacheHandler = new Handler() {
                @Override
                public void handleMessage(Message mMessage) {
                    X[] resObject = (X[]) mMessage.obj;
                    onPostRefreshXml(mRequest.mLocalCallback, resObject[0]);
                }
            };
            GetCacheTask<X> mCacheTask = new GetCacheTask<>(mCache, mCacheHandler);
            mCacheTask.execute(mCacheTaskParam);
        }
    }

    /**
     * This instance is equal to all instances of {@link Repository} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code mAnother} instance
     */
    @Override
    public boolean equals(Object mAnother) {
        return (this == mAnother) ||
                (mAnother instanceof Repository && this.equalsTo((Repository) mAnother));
    }

    private boolean equalsTo(Repository mAnother) {
        return mContext.equals(mAnother.mContext);
    }

    /**
     * Computes a hash code from attributes: {@code mContext}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        int h = 31;
        h = h * 17 + mContext.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@link Repository} with all non-generated and non-auxiliary attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "Repository{"
                + "context=" + mContext
                + "}";
    }

    /**
     * Builds instances of type {@link Repository}.
     * Initialize attributes and then invoke the {@link #build()} method to create an immutable instance.
     */
    private static final class Builder {
        private final Context mContext;

        /**
         * Start building a new {@link Repository} instance.
         */
        private Builder(Context mContext) {
            if (mContext == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.mContext = mContext.getApplicationContext();
        }

        /**
         * Create the {@link Repository} instance.
         *
         * @return An immutable instance of {@link Repository}
         */
        private Repository build() {
            Context mContext = this.mContext;
            return new Repository(mContext);
        }
    }
}
