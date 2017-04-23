package com.fabiolee.repository.sample.object.xml;

import com.fabiolee.repository.sample.util.Constant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.APPS, strict = false)
public class Apps {
    @ElementList(name = Constant.Key.APP, inline = true, required = false)
    private List<App> apps;

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }
}
