package com.uberfusion.repository.object.xml.app;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = Constant.Key.LOGO, strict = false)
public class Logo extends BaseObject {
    @Attribute(name = Constant.Key.SRC)
    private String src;

    @Attribute(name = Constant.Key.WIDTH)
    private String width;

    @Attribute(name = Constant.Key.HEIGHT)
    private String height;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
