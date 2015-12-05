package com.uberfusion.repository.object.xml;

import java.io.Serializable;

public class BaseObject implements Serializable {
    private String lastUpdateDate;

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
