package com.uberfusion.repository.object.xml.reward;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = Constant.Key.MERCHANT, strict = false)
public class PrivilegeMerchant extends BaseObject {
    @Attribute(name = Constant.Key.LATEST)
    private String latest;

    @Element(name = Constant.Key.NAME)
    private String name;

    @Element(name = Constant.Key.ID)
    private String id;

    @Element(name = Constant.Key.LOGO)
    private PrivilegeLogo logo;

    @Element(name = Constant.Key.IMAGE)
    private PrivilegeImage image;

    @Element(name = Constant.Key.INFO)
    private PrivilegeInfo info;

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PrivilegeLogo getLogo() {
        return logo;
    }

    public void setLogo(PrivilegeLogo logo) {
        this.logo = logo;
    }

    public PrivilegeImage getImage() {
        return image;
    }

    public void setImage(PrivilegeImage image) {
        this.image = image;
    }

    public PrivilegeInfo getInfo() {
        return info;
    }

    public void setInfo(PrivilegeInfo info) {
        this.info = info;
    }
}
