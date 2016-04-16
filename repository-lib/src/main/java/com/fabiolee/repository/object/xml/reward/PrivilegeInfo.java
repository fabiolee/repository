package com.fabiolee.repository.object.xml.reward;

import com.fabiolee.repository.object.xml.BaseObject;
import com.fabiolee.repository.util.Constant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.INFO, strict = false)
public class PrivilegeInfo extends BaseObject {
    @ElementList(name = Constant.Key.DETAIL, inline = true)
    private List<PrivilegeDetail> detail;

    public List<PrivilegeDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<PrivilegeDetail> detail) {
        this.detail = detail;
    }
}
