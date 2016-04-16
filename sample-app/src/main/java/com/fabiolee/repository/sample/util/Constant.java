package com.fabiolee.repository.sample.util;

import com.fabiolee.repository.sample.object.xml.app.Apps;

/**
 * @author fabio.lee
 */
public final class Constant {
    /**
     * File Name
     */
    public static final class FileName {
        public static final String XML_APP = "app.xml";
    }

    /**
     * Key
     */
    public static final class Key {
        public static final String APP = "app";
        public static final String APPS = "apps";
        public static final String DESC = "desc";
        public static final String HEIGHT = "height";
        public static final String HREF = "href";
        public static final String HREFANDROID = "href_android";
        public static final String LABEL = "label";
        public static final String LOGO = "logo";
        public static final String SRC = "src";
        public static final String WIDTH = "width";
    }

    /**
     * Url
     */
    public static final class Url {
        public static final String XML_APP = "https://raw.githubusercontent.com/fabiolee/repository/master/sample-app/src/main/assets/app.xml";
    }

    /**
     * Xml
     */
    public enum Xml {
        APP(Url.XML_APP, Apps.class, FileName.XML_APP);

        private final String url;
        private final Class<?> object;
        private final String fileName;

        Xml(String url, Class<?> object, String fileName) {
            this.url = url;
            this.object = object;
            this.fileName = fileName;
        }

        public String getUrl() {
            return url;
        }

        public Class<?> getObject() {
            return object;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
