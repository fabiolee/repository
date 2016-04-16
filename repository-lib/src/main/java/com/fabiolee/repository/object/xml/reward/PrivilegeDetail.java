package com.fabiolee.repository.object.xml.reward;

import com.fabiolee.repository.object.xml.BaseObject;
import com.fabiolee.repository.util.Constant;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.DETAIL, strict = false)
public class PrivilegeDetail extends BaseObject {
    @Attribute(name = Constant.Key.TITLE)
    private String title;

    @Attribute(name = Constant.Key.TYPE, required = false)
    private String type;

    @ElementList(entry = Constant.Key.DESCRIPTION, required = false, inline = true)
    private List<String> description;

    @ElementList(entry = Constant.Key.CAPTION, required = false, inline = true)
    private List<String> caption;

    @ElementList(name = Constant.Key.ROW, required = false, inline = true)
    private List<PrivilegeRow> row;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getCaption() {
        return caption;
    }

    public void setCaption(List<String> caption) {
        this.caption = caption;
    }

    public List<PrivilegeRow> getRow() {
        return row;
    }

    public void setRow(List<PrivilegeRow> row) {
        this.row = row;
    }
}
