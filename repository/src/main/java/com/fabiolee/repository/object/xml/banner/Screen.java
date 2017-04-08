package com.fabiolee.repository.object.xml.banner;

import com.fabiolee.repository.object.xml.BaseObject;
import com.fabiolee.repository.util.Constant;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = Constant.Key.SCREEN, strict = false)
public class Screen extends BaseObject {
    @Attribute(name = Constant.Key.NAME)
    private String name;

    @Attribute(name = Constant.Key.HREF, required = false)
    private String href;

    @Element(name = Constant.Key.HORIZONTALIMAGE)
    private HorizontalImage horizontalImage;

    @Element(name = Constant.Key.VERTICALIMAGE)
    private VerticalImage verticalImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public HorizontalImage getHorizontalImage() {
        return horizontalImage;
    }

    public void setHorizontalImage(HorizontalImage horizontalImage) {
        this.horizontalImage = horizontalImage;
    }

    public VerticalImage getVerticalImage() {
        return verticalImage;
    }

    public void setVerticalImage(VerticalImage verticalImage) {
        this.verticalImage = verticalImage;
    }
}
