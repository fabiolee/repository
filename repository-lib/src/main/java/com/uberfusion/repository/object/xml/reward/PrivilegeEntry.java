package com.uberfusion.repository.object.xml.reward;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = Constant.Key.ENTRY, strict = false)
public class PrivilegeEntry extends BaseObject {
    @Attribute(name = Constant.Key.CLASS, required = false)
    private String attClass;

    @Text(required = false)
    private String text;

    public String getAttClass() {
        return attClass;
    }

    public void setAttClass(String attClass) {
        this.attClass = attClass;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
