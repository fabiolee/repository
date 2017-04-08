package com.fabiolee.repository.object.db;

import android.content.ContentValues;

import com.fabiolee.repository.util.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BaseDbObject {
    private String id;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ContentValues toParams() {
        Date d = new Date();

        // Display local date
        SimpleDateFormat formatter12 = new SimpleDateFormat(Constant.Format.DATETIME12, Locale.ENGLISH);
        formatter12.setTimeZone(TimeZone.getDefault());
        String localDate = formatter12.format(d);
        this.setDate(localDate);

        // Save UTC date
        SimpleDateFormat formatter24 = new SimpleDateFormat(Constant.Format.DATETIME24, Locale.ENGLISH);
        formatter24.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcDate = formatter24.format(d);

        ContentValues params = new ContentValues();
        params.put(Constant.Key.DATE, utcDate);

        return params;
    }
}
