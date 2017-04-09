package com.fabiolee.repository.adapter.cache;

import android.graphics.Bitmap;
import android.support.v4.util.ArrayMap;

import java.lang.ref.SoftReference;
import java.util.Map;

public class MemoryCache {
    private Map<String, SoftReference<Bitmap>> imageCache = new ArrayMap<>();
    private Map<String, Object> xmlCache = new ArrayMap<>();

    public boolean containsXml(String id) {
        return xmlCache.containsKey(id);
    }

    public Bitmap getImage(String id) {
        if (!imageCache.containsKey(id))
            return null;
        SoftReference<Bitmap> ref = imageCache.get(id);
        return ref.get();
    }

    public <X> X getXml(String id) {
        return (X) xmlCache.get(id);
    }

    public boolean isEmptyImage() {
        return imageCache.isEmpty();
    }

    public boolean isEmptyXml() {
        return xmlCache.isEmpty();
    }

    public void setImage(String id, Bitmap bitmap) {
        imageCache.put(id, new SoftReference<>(bitmap));
    }

    public <X> void setXml(String id, X xmlObject) {
        xmlCache.put(id, xmlObject);
    }

    public void clear() {
        imageCache.clear();
        xmlCache.clear();
    }
}