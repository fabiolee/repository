package com.fabiolee.repository.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.fabiolee.repository.object.xml.BaseObject;

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
import java.io.UnsupportedEncodingException;

public class Util {
    private static final String LOG_TAG = "Util";

    public static BaseObject convertXmlToObject(String xml, Class<? extends BaseObject> c) {
        // Long process, use carefully.
        try {
            Serializer serializer = new Persister();
            StringReader sr = new StringReader(xml);
            return serializer.read(c, sr);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
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
            Log.e(LOG_TAG, e.getMessage());
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
            Log.e(LOG_TAG, e.getMessage());
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
        try {
            final Reader in = new InputStreamReader(is, "UTF-8");
            try {
                for (; ; ) {
                    int count = in.read(buffer, 0, Constant.Value.STREAM_BUFFERSIZE);
                    if (count == -1)
                        break;
                    out.append(buffer, 0, count);
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return out.toString();
    }

    public static void showErrorNotification(Context context, String text) {
        // Disable error debug message when go LIVE
        // PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
        // new Intent(), 0);
        //
        // Notification notification = new
        // Notification(R.drawable.fabiolee_logo_24px, null,
        // System.currentTimeMillis());
        // notification.setLatestEventInfo(context,
        // context.getText(R.string.lbl_error), text, contentIntent);
        // notification.flags = Notification.FLAG_AUTO_CANCEL;
        //
        // NotificationManager mNM = (NotificationManager)
        // context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mNM.notify(Constant.Id.NOTIFICATION_SYNC, notification);
    }
}