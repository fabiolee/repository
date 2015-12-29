package com.uberfusion.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;

import com.uberfusion.repository.adapter.cache.CacheLoader;
import com.uberfusion.repository.adapter.cache.MemoryCache;
import com.uberfusion.repository.adapter.db.DbAdapter;
import com.uberfusion.repository.adapter.network.NetworkAdapter;
import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.task.DownloadXmlTask;
import com.uberfusion.repository.task.GetCacheTask;
import com.uberfusion.repository.task.SetCacheTask;

/**
 * @author fabio.lee
 */
public final class Repository {
    private static volatile Repository singleton = null;

    private final Context mContext;
    private final DbAdapter mDb;
    private final NetworkAdapter mNetwork;
    private final CacheLoader mCache;

    private Repository(Context context) {
        this.mContext = context;
        this.mDb = new DbAdapter(context);
        this.mNetwork = new NetworkAdapter(context);
        this.mCache = new CacheLoader(context, mDb, mNetwork);
    }

    public MemoryCache getCache() {
        return mCache.getMemoryCache();
    }

    public void setCache(MemoryCache cache) {
        if (cache != null) {
            mCache.setMemoryCache(cache);
        }
    }

    public void loadImage(String url, ImageView imageView) {
        mCache.setImage(url, imageView);
    }

    public <X> void loadXml(String url, Class<X> object, String fileName, Handler localHandler, Handler remoteHandler) {
        if (mCache.isEmptyXml()) {
            this.onDownloadXml(url, remoteHandler);
        } else {
            this.onPreRefreshXml(url, object.getName(), fileName, localHandler);
        }
    }

    public <X> X loadXmlLocalHandler(Message msg) {
        return (X) msg.obj;
    }

    public <X> void loadXmlRemoteHandler(String url, Class<X> object, String fileName, final Handler localHandler, Message msg) {
        String resXml = ((String[]) msg.obj)[0];
        if (TextUtils.isEmpty(resXml)) {
            onPreRefreshXml(url, object.getName(), fileName, localHandler);
        } else {
            String[][] mCacheTaskParam = new String[][] { { url, resXml, object.getClass().getName() }};
            Handler mCacheHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    BaseObject[] resObject = (BaseObject[]) msg.obj;
                    onPostRefreshXml(localHandler, resObject[0]);
                }
            };
            SetCacheTask mCacheTask = new SetCacheTask(mContext, mCache, mCacheHandler);
            mCacheTask.execute(mCacheTaskParam);
        }
    }

    private void onDownloadXml(String url, Handler remoteHandler) {
        DownloadXmlTask mDownloadTask = new DownloadXmlTask(remoteHandler, mNetwork);
        mDownloadTask.execute(url);
    }

    private <X> void onPreRefreshXml(String url, String className, String fileName, final Handler localHandler) {
        if (mCache.containsXml(url)) {
            this.onPostRefreshXml(localHandler, mCache.getXml(url, className, fileName));
        } else {
            String[][] mCacheTaskParam = new String[][] { { url, className, fileName } };
            Handler mCacheHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    BaseObject[] resObject = (BaseObject[]) msg.obj;
                    onPostRefreshXml(localHandler, resObject[0]);
                }
            };
            GetCacheTask mCacheTask = new GetCacheTask(mCache, mCacheHandler);
            mCacheTask.execute(mCacheTaskParam);
        }
    }

    private <X> void onPostRefreshXml(Handler localHandler, X object) {
        if (object != null) {
            Message mMessage = new Message();
            mMessage.obj = object;
            localHandler.sendMessage(mMessage);
        }
    }

    /**
     * This instance is equal to all instances of {@link Repository} that have equal attribute values.
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        return (this == another) ||
                (another instanceof Repository && this.equalTo((Repository) another));
    }

    private boolean equalTo(Repository another) {
        return mContext.equals(another.mContext);
    }

    /**
     * Computes a hash code from attributes: {@link Context}.
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
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "Repository{"
                + "context=" + mContext
                + "}";
    }

    /**
     * The global default {@link Repository} instance.
     */
    public static Repository with(Context context) {
        if (singleton == null) {
            synchronized (Repository.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    /**
     * Builds instances of type {@link Repository}.
     * Initialize attributes and then invoke the {@link #build()} method to create an immutable instance.
     */
    private static final class Builder {
        private final Context context;

        /**
         * Start building a new {@link Repository} instance.
         */
        private Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        /**
         * Create the {@link Repository} instance.
         * @return An immutable instance of {@link Repository}
         */
        private Repository build() {
            Context context = this.context;
            return new Repository(context);
        }
    }
}
