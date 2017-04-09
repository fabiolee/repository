package com.fabiolee.repository.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

public class Util {
    private static final String LOG_TAG = "Util";

    public static <X> X convertXmlToObject(String xml, Class<X> c) {
        // Long process, use carefully.
        try {
            Serializer serializer = new Persister();
            StringReader sr = new StringReader(xml);
            return serializer.read(c, sr);
        } catch (Exception e) {
            Log.e(LOG_TAG, String.format("convertXmlToObject(xml=[%s], c=[%s])", xml, c), e);
            return null;
        }
    }

    public static void copyStream(InputStream is, OutputStream os) {
        try {
            byte[] bytes = new byte[Constant.Value.STREAM_BUFFERSIZE];
            for (; ; ) {
                int count = is.read(bytes, 0, Constant.Value.STREAM_BUFFERSIZE);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.format("copyStream(is=[%s], os=[%s])", is, os), e);
        }
    }

    // decodes image and scales it to reduce memory consumption
    public static Bitmap decodeFile(File f) {
        try {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 70;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            Log.e(LOG_TAG, String.format("decodeFile(f=[%s])", f), e);
        }
        return null;
    }

    public static File getExternalStorageAppCacheDirectory(String packageName) {
        return new File(getExternalStorageAppDataDirectory(packageName), "cache");
    }

    private static File getExternalStorageAppDataDirectory(String packageName) {
        return new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), packageName);
    }

    public static String onPreXmlStringReplace(String id, String resXml) {
        if (Constant.Url.XML_MERCHANT.equalsIgnoreCase(id)) {
            resXml = resXml.replaceAll(" class", " " + Constant.Key.CLASS);
            resXml = resXml.replaceAll("<strong>", "");
            resXml = resXml.replaceAll("</strong>", "");
        }
        return resXml;
    }

    public static String outputStream(InputStream is) {
        final char[] buffer = new char[Constant.Value.STREAM_BUFFERSIZE];
        final StringBuilder out = new StringBuilder();
        Reader in = null;
        try {
            in = new InputStreamReader(is, "UTF-8");
            for (; ; ) {
                int count = in.read(buffer, 0, Constant.Value.STREAM_BUFFERSIZE);
                if (count == -1)
                    break;
                out.append(buffer, 0, count);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.format("outputStream(is=[%s])", is), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "InputStream#close()", e);
            }
        }
        return out.toString();
    }
}