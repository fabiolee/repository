package com.fabiolee.repository.object.xml.reward;

import com.fabiolee.repository.object.xml.BaseObject;
import com.fabiolee.repository.util.Constant;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = Constant.Key.REGION, strict = false)
public class PrivilegeRegion extends BaseObject {
    @Element(name = Constant.Key.NAME)
    private String name;

    @Element(name = Constant.Key.VALUE)
    private String value;

    @Element(name = Constant.Key.CATEGORYLIST)
    private PrivilegeCategoryList categoryList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PrivilegeCategoryList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(PrivilegeCategoryList categoryList) {
        this.categoryList = categoryList;
    }
}
