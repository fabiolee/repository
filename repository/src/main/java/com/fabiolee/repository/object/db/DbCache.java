package com.fabiolee.repository.object.db;

import android.content.ContentValues;

import com.fabiolee.repository.util.Constant;

public class DbCache extends BaseDbObject {
    private String module;
    private String data;
    private String className;

    public DbCache(String module, String data, String className) {
        this.setModule(module);
        this.setData(data);
        this.setClassName(className);
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public ContentValues toParams() {
        ContentValues params = super.toParams();
        params.put(Constant.Key.MODULE, this.getModule());
        params.put(Constant.Key.DATA, this.getData());
        params.put(Constant.Key.CLASS, this.getClassName());

        return params;
    }
}
