package com.uberfusion.repository.object.xml.reward;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.CATEGORYLIST, strict = false)
public class PrivilegeCategoryList extends BaseObject {
    @ElementList(name = Constant.Key.CATEGORY, inline = true)
    private List<PrivilegeCategory> category;

    public List<PrivilegeCategory> getCategory() {
        return category;
    }

    public void setCategory(List<PrivilegeCategory> category) {
        this.category = category;
    }
}
