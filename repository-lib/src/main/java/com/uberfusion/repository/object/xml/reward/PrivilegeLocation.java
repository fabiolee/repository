package com.uberfusion.repository.object.xml.reward;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.LOCATION, strict = false)
public class PrivilegeLocation extends BaseObject {
    @ElementList(name = Constant.Key.REGION, inline = true)
    private List<PrivilegeRegion> region;

    public List<PrivilegeRegion> getRegion() {
        return region;
    }

    public void setRegion(List<PrivilegeRegion> region) {
        this.region = region;
    }
}
