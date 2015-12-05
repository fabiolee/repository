package com.uberfusion.repository.object.xml.store;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.ElementList;

import java.util.List;

public class StoreState extends BaseObject {
    @ElementList(name = Constant.Key.ITEM, inline = true)
    private List<StoreItem> items;

    public List<StoreItem> getItems() {
        return items;
    }

    public void setItems(List<StoreItem> items) {
        this.items = items;
    }
}
