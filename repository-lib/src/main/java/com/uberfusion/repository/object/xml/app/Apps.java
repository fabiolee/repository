package com.uberfusion.repository.object.xml.app;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.APPS, strict = false)
public class Apps extends BaseObject {
    @ElementList(name = Constant.Key.APP, inline = true, required = false)
    private List<App> apps;

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }
}
