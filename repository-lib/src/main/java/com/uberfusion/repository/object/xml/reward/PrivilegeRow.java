package com.uberfusion.repository.object.xml.reward;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.ROW, strict = false)
public class PrivilegeRow extends BaseObject {
    @ElementList(name = Constant.Key.COL, inline = true)
    private List<PrivilegeCol> col;

    public List<PrivilegeCol> getCol() {
        return col;
    }

    public void setCol(List<PrivilegeCol> col) {
        this.col = col;
    }
}
