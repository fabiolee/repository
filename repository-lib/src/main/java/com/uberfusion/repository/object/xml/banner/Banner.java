package com.uberfusion.repository.object.xml.banner;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.BANNER, strict = false)
public class Banner extends BaseObject {
    @ElementList(name = Constant.Key.SCREEN, inline = true, required = false)
    private List<Screen> screens;

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }
}
