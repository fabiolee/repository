package com.fabiolee.repository.util;

public final class Constant {
    /**
     * Database
     */
    public static final class Database {
        public static final String TABLE_CACHE = "cache";
    }

    /**
     * Format
     */
    public static final class Format {
        public static final String DATE = "yyyy-MM-dd";
        public static final String DATETIME24 = DATE + " HH:mm:ss";
        public static final String DATETIME12 = DATE + " h:mm:ss a";
    }

    /**
     * Key
     */
    public static final class Key {
        public static final String DATA = "data";
        public static final String CLASS = "att_class";
        public static final String ROWID = "_id";
        public static final String MODULE = "module";
        public static final String DATE = "date";
    }

    /**
     * Time
     */
    public static final class Time {
        public static final int ONE_SECOND = 1000;
        public static final int THIRTY_SECONDS = 30 * ONE_SECOND;
    }

    /**
     * Value
     */
    public static final class Value {
        public static final int STREAM_BUFFERSIZE = 1024;
    }
}