package com.fabiolee.repository.sample.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabiolee.repository.sample.R;

/**
 * @author fabio.lee
 */
public class Util {
    private static final String LOG_TAG = "Util";

    public static View getLayout(Activity activity, int layoutResID) {
        return getLayout(activity, activity.getLayoutInflater(), layoutResID, null, false);
    }

    private static View getLayout(Context context, LayoutInflater inflater, int layoutResID, ViewGroup root, boolean attachToRoot) {
        try {
            if (root == null) {
                return inflater.inflate(layoutResID, null);
            } else {
                return inflater.inflate(layoutResID, root, attachToRoot);
            }
        } catch (Exception e) {
            String errorMessage = context.getString(R.string.msg_layout_notfound);
            Log.e(LOG_TAG, errorMessage + " " + e.getMessage());
            throw new AssertionError(errorMessage);
        }
    }

    public static void openWebsite(Context context, String url) {
        Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(website);
    }
}
