package com.fabiolee.repository.sample.object.xml;

import com.fabiolee.repository.sample.util.Constant;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = Constant.Key.APP, strict = false)
public class App {
    @Attribute(name = Constant.Key.LABEL)
    private String label;

    @Attribute(name = Constant.Key.DESC)
    private String desc;

    @Attribute(name = Constant.Key.HREF, required = false)
    private String href;

    @Attribute(name = Constant.Key.HREFANDROID, required = false)
    private String hrefAndroid;

    @Element(name = Constant.Key.LOGO)
    private Logo logo;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHrefAndroid() {
        return hrefAndroid;
    }

    public void setHrefAndroid(String hrefAndroid) {
        this.hrefAndroid = hrefAndroid;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }
}
