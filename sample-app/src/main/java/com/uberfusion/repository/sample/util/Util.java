package com.uberfusion.repository.sample.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uberfusion.repository.sample.R;

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
            Util.showErrorNotification(context, errorMessage);
            throw new AssertionError(errorMessage);
        }
    }

    public static void openWebsite(Context context, String url) {
        Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(website);
    }

    public static void showErrorNotification(Context context, String text) {
        // Disable error debug message when go LIVE
        // PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
        // new Intent(), 0);
        //
        // Notification notification = new
        // Notification(R.drawable.uberfusion_logo_24px, null,
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
