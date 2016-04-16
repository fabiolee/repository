package com.fabiolee.repository.object.xml.reward;

import com.fabiolee.repository.object.xml.BaseObject;
import com.fabiolee.repository.util.Constant;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = Constant.Key.IMAGE, strict = false)
public class PrivilegeImage extends BaseObject {
    @Attribute(name = Constant.Key.WIDTH)
    private String width;

    @Attribute(name = Constant.Key.HEIGHT)
    private String height;

    @Text
    private String text;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
