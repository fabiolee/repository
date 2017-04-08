package com.fabiolee.repository.adapter.cache;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.fabiolee.repository.R;
import com.fabiolee.repository.adapter.db.DbAdapter;
import com.fabiolee.repository.adapter.network.NetworkAdapter;
import com.fabiolee.repository.object.db.DbCache;
import com.fabiolee.repository.object.xml.BaseObject;
import com.fabiolee.repository.util.Util;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheLoader {
    private final int defaultId = R.color.transparent;

    private ExecutorService executorService;
    private FileCache fileCache;
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    private MemoryCache memoryCache = new MemoryCache();
    private NetworkAdapter mNetwork;

    public CacheLoader(Context context, DbAdapter db, NetworkAdapter network) {
        executorService = Executors.newFixedThreadPool(5);
        fileCache = new FileCache(context, db);
        mNetwork = network;
    }

    public MemoryCache getMemoryCache() {
        return memoryCache;
    }

    public void setMemoryCache(MemoryCache memoryCache) {
        this.memoryCache = memoryCache;
    }

    public boolean containsXml(String id) {
        return memoryCache.containsXml(id);
    }

    public BaseObject getXml(String id, String className, String fileName) {
        BaseObject xmlObject = memoryCache.getXml(id);
        if (xmlObject == null) {
            xmlObject = fileCache.getXml(id);

            if (xmlObject == null) {
                // Set default xml.
                fileCache.setDefaultXml(id, className, fileName);
                xmlObject = fileCache.getXml(id);
            }

            if (xmlObject != null) {
                memoryCache.setXml(id, xmlObject);
            }
        }
        return xmlObject;
    }

    public boolean isEmptyImage() {
        return memoryCache.isEmptyImage();
    }

    public boolean isEmptyXml() {
        return memoryCache.isEmptyXml();
    }

    public void setImage(String url, ImageView imageView) {
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.getImage(url);
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
        else {
            queuePhoto(url, imageView);
            imageView.setImageResource(defaultId);
        }
    }

    public BaseObject setXml(final String id, final String xmlString, final Class<? extends BaseObject> c) {
        BaseObject xmlObject = Util.convertXmlToObject(xmlString, c);
        if (xmlObject != null) {
            DbCache dbObject = new DbCache(id, xmlString, c.getName());
            fileCache.setXml(dbObject);
            xmlObject.setLastUpdateDate(dbObject.getDate());
            memoryCache.setXml(id, xmlObject);
        }
        return xmlObject;
    }

    private void queuePhoto(String url, ImageView imageView) {
        PhotoToLoad p = new PhotoToLoad(url, imageView);
        executorService.submit(new PhotosLoader(p));
    }

    private Bitmap getBitmap(String url) {
        File f = fileCache.getFile(url);

        // from SD cache
        Bitmap b = Util.decodeFile(f);
        if (b != null)
            return b;

        // from web
        return mNetwork.httpGetBitmap(url, f);
    }

    // Task for the queue
    private class PhotoToLoad {
        public String url;
        public ImageView imageView;

        public PhotoToLoad(String u, ImageView i) {
            url = u;
            imageView = i;
        }
    }

    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;

        PhotosLoader(PhotoToLoad photoToLoad) {
            this.photoToLoad = photoToLoad;
        }

        @Override
        public void run() {
            if (imageViewReused(photoToLoad))
                return;
            Bitmap bmp = getBitmap(photoToLoad.url);
            memoryCache.setImage(photoToLoad.url, bmp);
            if (imageViewReused(photoToLoad))
                return;
            BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
            Activity a = (Activity) photoToLoad.imageView.getContext();
            a.runOnUiThread(bd);
        }
    }

    boolean imageViewReused(PhotoToLoad photoToLoad) {
        String tag = imageViews.get(photoToLoad.imageView);
        return (tag == null || !tag.equals(photoToLoad.url));
    }

    // Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;

        public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
            bitmap = b;
            photoToLoad = p;
        }

        @Override
        public void run() {
            if (imageViewReused(photoToLoad))
                return;
            if (bitmap != null)
                photoToLoad.imageView.setImageBitmap(bitmap);
            else
                photoToLoad.imageView.setImageResource(defaultId);
        }
    }

    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }
}
