package com.fabiolee.repository.object.xml.reward;

import com.fabiolee.repository.object.xml.BaseObject;
import com.fabiolee.repository.util.Constant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.COL, strict = false)
public class PrivilegeCol extends BaseObject {
    @ElementList(name = Constant.Key.ENTRY, inline = true)
    private List<PrivilegeEntry> entry;

    public List<PrivilegeEntry> getEntry() {
        return entry;
    }

    public void setEntry(List<PrivilegeEntry> entry) {
        this.entry = entry;
    }
}
