package com.uberfusion.repository.object.xml.reward;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = Constant.Key.CATEGORY, strict = false)
public class PrivilegeCategory extends BaseObject {
    @Element(name = Constant.Key.CATEGORYNAME)
    private String catName;

    @Element(name = Constant.Key.CATEGORYVALUE)
    private String catValue;

    @Element(name = Constant.Key.MERCHANTLIST, required = false)
    private String merchantList;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatValue() {
        return catValue;
    }

    public void setCatValue(String catValue) {
        this.catValue = catValue;
    }

    public String getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(String merchantList) {
        this.merchantList = merchantList;
    }
}
