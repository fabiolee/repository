package com.uberfusion.repository.adapter.cache;

import android.graphics.Bitmap;

import com.uberfusion.repository.object.xml.BaseObject;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class MemoryCache {
    private HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
    private HashMap<String, BaseObject> xmlCache = new HashMap<String, BaseObject>();

    public boolean containsXml(String id) {
        return xmlCache.containsKey(id);
    }

    public Bitmap getImage(String id) {
        if (!imageCache.containsKey(id))
            return null;
        SoftReference<Bitmap> ref = imageCache.get(id);
        return ref.get();
    }

    public BaseObject getXml(String id) {
        return xmlCache.get(id);
    }

    public void setImage(String id, Bitmap bitmap) {
        imageCache.put(id, new SoftReference<Bitmap>(bitmap));
    }

    public void setXml(String id, BaseObject xmlObject) {
        xmlCache.put(id, xmlObject);
    }

    public void clear() {
        imageCache.clear();
        xmlCache.clear();
    }
}